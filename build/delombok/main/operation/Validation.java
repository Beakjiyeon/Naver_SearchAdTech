package operation;

import common.*;
import exception.ParameterException;
import lombok.NonNull;
import model.*;
import util.AppSetting;
import util.RestClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import static common.AdGroupTypes.CATALOG;
import static util.AppSetting.*;

/**
 * 파라미터 유효성 검사 클래스
 */
public class Validation {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Validation.class);

    /**
     * 캠페인 등록 파라미터 유효성 검사 메소드
     * @param campaign 캠페인 정보
     * @throws ParameterException 유효성 검사 시 발생
     * @throws ParseException 날짜 포맷 메소드 예외 발생
     */
    public static void validateCampaignCreate(Campaign campaign) throws ParameterException, ParseException {
        log.debug("캠페인 등록 파라미터 유효성 검사");
        if (campaign.getName() == null) {
            throw new ParameterException(ParameterException.NULL_NAME_PARAMETER_EXCEPTION);
        }
        if (campaign.getCampaignTp() == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_TP_PARAMETER_EXCEPTION);
        }
        if (campaign.getCustomerId() == null) {
            throw new ParameterException(ParameterException.NULL_CUSTOMER_ID_PARAMETER_EXCEPTION);
        }
        validateName(campaign.getName());
        if (campaign.getUseDailyBudget() != null) {
            throw new ParameterException(ParameterException.NULL_USE_DAILY_BUDGET_PARAMETER_EXCEPTION);
        }
        if (campaign.getDailyBudget() != null) {
            campaign.setUseDailyBudget(validateDailyBudget(campaign.getCampaignTp(), campaign.getDailyBudget()));
        }
        if (campaign.getTrackingMode() != null) {
            validateTrackingMode(campaign.getTrackingMode(), campaign.getTrackingUrl());
        }
        validatePeriods(campaign);
    }

    /**
     * 캠페인 수정 파라미터 유효성 검사 메소드
     * @param campaign 캠페인 정보
     * @throws ParameterException 유효성 검사 시 발생
     * @throws ParseException 날짜 포맷 메소드 예외 발생
     */
    public static void validateCampaignUpdate(Campaign campaign) throws ParameterException, ParseException {
        log.debug("캠페인 수정 파라미터 유효성 검사");
        // 캠페인 id, 캠페인 활성화 여부 필수
        if (campaign.getNccCampaignId() == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
        }
        if (campaign.getUserLock() == null) {
            throw new ParameterException(ParameterException.NULL_USERLOCK_PARAMETER_EXCEPTION);
        }
        // 하루 예산
        if (campaign.getDailyBudget() != null) {
            campaign.setUseDailyBudget(validateDailyBudget(campaign.getCampaignTp(), campaign.getDailyBudget()));
        }
        // 이름
        if (campaign.getName() != null) {
            validateName(campaign.getName());
        }
        // 추적 모드
        if (campaign.getTrackingMode() != null) {
            validateTrackingMode(campaign.getTrackingMode(), campaign.getTrackingUrl());
        }
        // 노출 기간
        validatePeriods(campaign);
    }

    /**
     * 캠페인 노출기간 유효성 검사 메소드
     * @param campaign 캠페인 객체
     * @throws ParameterException 유효성 검사 시 발생
     * @throws ParseException 날짜 포맷 메소드에서 문제 시 발생
     */
    public static void validatePeriods(Campaign campaign) throws ParameterException, ParseException {
        String start = campaign.getPeriodStartDt();
        String end = campaign.getPeriodEndDt();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSSXXX");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date now = new Date();
        Date startDate = null;
        Date endDate = null;
        if (start != null) {
            startDate = new Date(format.parse(start).getTime());
        }
        if (end != null) {
            endDate = new Date(format.parse(end).getTime());
        }
        /*
        시작일 != null : 시작일(미래)-종료일(필수, 시작일보다 미래-setUsePeriod(true))
                        시작일(현재, 과거)-종료일(옵션), 종료일 != null 이면 종료일(시작일보다 미래) && setUsePeriod(true)
                                                     종료일 == null 이면 제한없음(setUsePeriod(false))
        시작일 == null : 종료일이 있으면 시작일=현재날짜 setUsePeriod(true) / 종료일이 없으면 제한없음(setUsePeriod(false))
         */
        if (startDate != null) {
            if (startDate.after(now)) {
                // 현재보다 미래시기라면
                if (end == null) {
                    throw new ParameterException(ParameterException.NULL_END_DATE_PARAMETER_EXCEPTION);
                } else 
                // 노출 시작일을 미래로 설정할 경우, 종료일 정보가 반드시 필요합니다.
                {
                    if (startDate.before(endDate)) {
                        if (campaign.getUsePeriod() != null) {
                            if (campaign.getUsePeriod() == false) {
                                throw new ParameterException(ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION);
                            }
                        }
                        // 시작 종료일을 입력하신 경우, 해당 기한으로 노출됩니다. 노출기한 '제한없음' 변수는 적용되지 않습니다.
                        campaign.setUsePeriod(true);
                    } else {
                        throw new ParameterException(ParameterException.ILLEGAL_START_DATE_PARAMETER_EXCEPTION);
                        // 시작일이 종료일보다 미래기간이면 안됩니다.
                    }
                }
            } else {
                // 시작일이 현재날짜와 같거나 현재보다 더 과거라면
                if (end == null) {
                    if (campaign.getUsePeriod() != null) {
                        if (campaign.getUsePeriod() == true) {
                            throw new ParameterException(ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION);
                        }
                    }
                    // 시작 종료일을 입력하신 경우, 해당 기한으로 노출됩니다. 노출기한 '제한없음' 변수는 적용되지 않습니다.
                    campaign.setUsePeriod(false);
                } else {
                    if (startDate.before(endDate)) {
                        if (campaign.getUsePeriod() != null) {
                            if (campaign.getUsePeriod() == false) {
                                throw new ParameterException(ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION);
                            }
                        }
                        // 시작 종료일을 입력하신 경우, 해당 기한으로 노출됩니다. 노출기한 '제한없음' 변수는 적용되지 않습니다.
                        campaign.setUsePeriod(true);
                    } else {
                        throw new ParameterException(ParameterException.ILLEGAL_START_DATE_PARAMETER_EXCEPTION);
                        // 시작일이 종료일보다 미래기간이면 안됩니다.
                    }
                }
            }
        } else {
            if (end != null) {
                if (campaign.getUsePeriod() != null) {
                    if (campaign.getUsePeriod() == false) {
                        throw new ParameterException(ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION);
                    }
                }
                // 종료일만 입력할 경우, 현재부터 종료일까지 노출됩니다. 노출기한 '제한없음' 변수는 적용되지 않습니다.
                campaign.setPeriodStartDt(format.format(new Date()));
                campaign.setUsePeriod(true);
            } else {
                if (campaign.getUsePeriod() != null) {
                    if (campaign.getUsePeriod() == true) {
                        throw new ParameterException(ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION);
                    }
                } else 
                //시작 종료일을 안쓰고, 노출기한 사용을 할 수 없습니다.
                {
                    campaign.setUsePeriod(false);
                }
            }
        }
    }

    /**
     * 캠페인 이름 유효성 검사 메소드 (글자수 및 허용 문자)
     * @param name 캠페인 이름 정보
     * @throws ParameterException 유효성 검사 시 발생
     */
    public static void validateName(String name) throws ParameterException {
        if (name.length() < MIN_CAMPAIGN_NAME_LENGTH || name.length() > MAX_CAMPAIGN_NAME_LENGTH) {
            throw new ParameterException(ParameterException.ILLEGAL_CAMPAIGN_NAME_PARAMETER_EXCEPTION);
        }
        if (!Pattern.matches("^[0-9a-zA-Z가-힣\\s\\,\\-\\#\\.]*$", name)) {
            throw new ParameterException(ParameterException.ILLEGAL_CAMPAIGN_NAME_PATTERN_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 캠페인 하루 예산 유효성 검사 메소드
     * @param dailyBudget 하루 예산 정보
     * @param  campaignTypes 캠페인 유형 정보
     * @return 하루예산 제한 없음(false)
     * @throws ParameterException 유효성 검사 시 발생
     */
    public static boolean validateDailyBudget(CampaignTypes campaignTypes, Long dailyBudget) throws ParameterException {
        log.debug("캠페인 하루 예산 유효성 검사");
        if (dailyBudget == 0) {
            // 하루예산을 사용하지 않음. Naver 내부적으로 useDailyBudget = false 처리
            return false;
        } else {
            // 하루예산을 사용함. 예산의 범위가 유효하다면, useDailyBudget = true 처리해서 네이버에 전송
            switch (campaignTypes) {
            case WEB_SITE: 
                if (dailyBudget % 10 != 0 || dailyBudget < MIN_POWER_LINK_DAILY_BUDGET || dailyBudget > MAX_POWER_LINK_DAILY_BUDGET) {
                    throw new ParameterException(ParameterException.ILLEGAL_POWERLINK_DAILY_BUDGET_PARAMETER_EXCEPTION);
                }
                break;
            case SHOPPING: 
                if (dailyBudget % 10 != 0 || dailyBudget < MIN_SHOPPING_DAILY_BUDGET || dailyBudget > MAX_SHOPPING_DAILY_BUDGET) {
                    throw new ParameterException(ParameterException.ILLEGAL_SHOPPING_DAILY_BUDGET_PARAMETER_EXCEPTION);
                }
                break;
            }
            return true; // true : 예산 사용
        }
    }

    /**
     * 캠페인 추적 모드, 추적 URL 유효성 검사 메소드
     * @param trackingMode 추적 모드
     * @param trackingUrl 추적 URL
     * @throws ParameterException 유효성 검사 시 발생
     */
    private static void validateTrackingMode(TrackingModeTypes trackingMode, String trackingUrl) throws ParameterException {
        if (trackingMode == TrackingModeTypes.PASS_THROUGH_SITE_MODE) {
            if (trackingUrl == null) {
                throw new ParameterException(ParameterException.NULL_TRACKING_URL_PARAMETER_EXCEPTION);
            } else {
                validateUrl(trackingUrl);
            }
        } else {
            if (trackingUrl != null) {
                throw new ParameterException(ParameterException.NULL_TRACKING_URL_PARAMETER_EXCEPTION);
            }
        }
    }

    /**
     * 그룹 등록 시 파라미터 유효성 검사 메소드
     * @param adGroup 그룹 정보
     * @throws ParameterException 유효성 검사 시 발생
     */
    public static void validateAdGroupCreate(Adgroup adGroup) throws Exception {
        log.debug("그룹 등록 파라미터 유효성 검사");
        if (adGroup.getName() == null) {
            throw new ParameterException(ParameterException.NULL_NAME_PARAMETER_EXCEPTION);
        }
//        if(adGroup.getAdGroupType() != null){
//            throw new ParameterException(ParameterException.NONNULL_ADGROUP_TYPE_PARAMETER_EXCEPTION);
//        }
        if (adGroup.getNccCampaignId() == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
        }
        if (adGroup.getPcChannelId() == null) {
            throw new ParameterException(ParameterException.NULL_PC_CHANNEL_ID_PARAMETER_EXCEPTION);
        }
        // 그룹 이름 유효성 검사
        validateAdGroupName(adGroup.getName());
        // PC, Mobile 플랫폼 입찰 가중치 유효성 검사
        if (!(adGroup.getPcNetworkBidWeight() == null && adGroup.getMobileNetworkBidWeight() == null)) {
            validateNetworkBidWeight(adGroup.getPcNetworkBidWeight(), adGroup.getMobileNetworkBidWeight());
        }
        // 기본 입찰가 유효성 검사
        if (adGroup.getBidAmt() != null) {
            validateBidAmt(adGroup.getBidAmt());
        }
        // 컨텐츠 매체 전용 입찰가 유효성 검사
        if (adGroup.getContentsNetworkBidAmt() != null) {
            adGroup.setUseCntsNetworkBidAmt(validateContentsNetworkBidAmt(adGroup.getContentsNetworkBidAmt()));
        }
        // 하루 예산 유효성 검사
        if (adGroup.getDailyBudget() != null) {
            CampaignManagement campaignMangement = new CampaignManagement();
            Campaign campaign = campaignMangement.getCampaign(restClient, adGroup.getNccCampaignId());
            if (campaign.getCampaignTp() == CampaignTypes.WEB_SITE) {
                adGroup.setAdgroupType(AdGroupTypes.WEB_SITE);
            } else if (campaign.getCampaignTp() == CampaignTypes.SHOPPING) {
                adGroup.setAdgroupType(CATALOG);
            }
            adGroup.setUseDailyBudget(validateAdGroupDailyBudget(adGroup.getAdgroupType(), adGroup.getDailyBudget()));
        }
    }

    /**
     * 그룹 콘텐츠 매체 입찰가 범위 유효성 검사
     * @param contentsNetworkBidAmt 그룹 콘텐츠 매체 입찰가
     * @return 유효성 여부
     */
    public static boolean validateContentsNetworkBidAmt(Long contentsNetworkBidAmt) {
        log.debug("그룹 콘텐츠 매체 입찰가 범위 유효성 검사");
        if (contentsNetworkBidAmt < MIN_CONTENTS_NETWORK_BIDAMT || contentsNetworkBidAmt > MAX_CONTENTS_NETWORK_BIDAMT) {
            throw new IllegalArgumentException("기본 입찰가 70~10억");
        }
        return true;
    }

    /**
     * 입찰가 범위 유효성 검사
     * @param bidAmt 입찰가
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public static void validateBidAmt(Long bidAmt) throws ParameterException {
        log.debug("기본 입찰가 범위 유효성 검사");
        if (bidAmt < MIN_GROUP_BIDAMT || bidAmt > MAX_GROUP_BIDAMT) {
            throw new ParameterException(ParameterException.ILLEGAL_BID_AMT_PARAMETER_EXCEPTION);
        }
    }

    /**
     * PC, Mobile 입찰 가중치 범위 유효성 검사
     * @param pcNetworkBidWeight PC 입찰 가중치
     * @param mobileNetworkBidWeight Mobile 입찰가중치
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public static void validateNetworkBidWeight(Long pcNetworkBidWeight, Long mobileNetworkBidWeight) throws ParameterException {
        log.debug("PC, Mobile 입찰 가중치 범위 유효성 검사");
        if (pcNetworkBidWeight == null) {
            throw new ParameterException(ParameterException.NULL_PC_NETWORK_BID_AMT_PARAMETER_EXCEPTION);
        }
        if (mobileNetworkBidWeight == null) {
            throw new ParameterException(ParameterException.NULL_MOBILE_NETWORK_BID_AMT_PARAMETER_EXCEPTION);
        }
        if (pcNetworkBidWeight < MIN_NETWORK_BID_WEIGHT || pcNetworkBidWeight > MAX_NETWORK_BID_WEIGHT) {
            throw new ParameterException(ParameterException.ILLEGAL_PC_BID_WEIGHT_PARAMETER_EXCEPTION);
        }
        if (mobileNetworkBidWeight < MIN_NETWORK_BID_WEIGHT || mobileNetworkBidWeight > MAX_NETWORK_BID_WEIGHT) {
            throw new ParameterException(ParameterException.ILLEGAL_MOBILE_BID_WEIGHT_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 그룹 이름 유효성 검사
     * @param name 그룹 이름
     * @throws ParameterException  파라미터 예외
     */
    public static void validateAdGroupName(String name) throws ParameterException {
        log.debug("그룹 이름 유효성 검사");
        if (name.length() < MIN_GROUP_NAME_LENGTH || name.length() > MAX_GROUP_NAME_LENGTH) {
            throw new ParameterException(ParameterException.ILLEGAL_GROUP_NAME_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 하루 예산 유효성 검사
     * @param adGroupTypes 그룹 유형
     * @param dailyBudget 하루 예산
     * @return 유효성 여부
     * @throws ParameterException 파라미터 예외
     */
    public static boolean validateAdGroupDailyBudget(@NonNull AdGroupTypes adGroupTypes, @NonNull Long dailyBudget) throws ParameterException {
        if (adGroupTypes == null) {
            throw new NullPointerException("adGroupTypes is marked non-null but is null");
        }
        if (dailyBudget == null) {
            throw new NullPointerException("dailyBudget is marked non-null but is null");
        }
        log.debug("하루 예산 유효성 검사");
        if (dailyBudget % 10 != 0) {
            throw new ParameterException(ParameterException.ILLEGAL_DAILY_BUDGET_PARAMETER_EXCEPTION);
        }
        if (dailyBudget == 0) {
            return false;
        } else {
            switch (adGroupTypes) {
            case WEB_SITE: 
                if (dailyBudget < MIN_POWER_LINK_DAILY_BUDGET || dailyBudget > MAX_POWER_LINK_DAILY_BUDGET) {
                    throw new ParameterException(ParameterException.ILLEGAL_POWERLINK_DAILY_BUDGET_PARAMETER_EXCEPTION);
                }
                break;
            case CATALOG: 
                if (dailyBudget < MIN_SHOPPING_DAILY_BUDGET || dailyBudget > MAX_SHOPPING_DAILY_BUDGET) {
                    throw new ParameterException(ParameterException.ILLEGAL_SHOPPING_DAILY_BUDGET_PARAMETER_EXCEPTION);
                }
                break;
            }
            return true;
        }
    }

    /**
     * 키워드 등록 시 유효성 검사
     * @param keywordList 유효성 검사할 키워드 목록
     * @throws ParameterException 파라미터 예외
     */
    public static void validateKeywordCreate(List<AdKeyword> keywordList) throws ParameterException {
        log.debug("키워드 등록 시 유효성 검사");
        validateAdKeywordListName(keywordList); // 캠페인 이름
        validateAdKeywordListBidAmt(keywordList); // 개별 입찰가 : 범위 검사
        validateAdKeywordListLink(keywordList); // 링크 도메인 검사
    }

    /**
     * 키워드 목록의 랜딩 링크 도메인 유효성 검사
     * @param keywordList 키워드 목록
     * @throws ParameterException 파라미터 예외
     */
    public static void validateAdKeywordListLink(List<AdKeyword> keywordList) throws ParameterException {
        log.debug("키워드 목록의 랜딩 링크 도메인 유효성 검사");
        Map<String, Object> map;
        Map<String, Object> pcMap;
        Map<String, Object> mobileMap;
        String pcUrl;
        String mobileUrl;
        for (AdKeyword keyword : keywordList) {
            if (keyword.getLinks() != null) {
                map = keyword.getLinks();
                pcMap = (Map) map.get("pc");
                mobileMap = (Map) map.get("mobile");
                if (pcMap != null) {
                    pcUrl = (String) pcMap.get("final");
                    if (pcUrl != null) {
                        validateUrl(pcUrl);
                    }
                }
                if (mobileMap != null) {
                    mobileUrl = (String) mobileMap.get("final");
                    if (mobileUrl != null) {
                        validateUrl(mobileUrl);
                    }
                }
            }
        }
    }

    /**
     * 키워드 목록의 입찰가 유효성 검사
     * @param keywordList 키워드 목록
     * @throws ParameterException 파라미터 예외
     */
    public static void validateAdKeywordListBidAmt(List<AdKeyword> keywordList) throws ParameterException {
        log.debug("키워드 목록의 입찰가 유효성 검사");
        for (AdKeyword keyword : keywordList) {
            if (keyword.getBidAmt() != null) {
                if (keyword.getBidAmt() == 0) {
                    keyword.setUseGroupBidAmt(true);
                    keyword.setBidAmt(70L);
                } else if (keyword.getBidAmt() < MIN_GROUP_BIDAMT || keyword.getBidAmt() > MAX_GROUP_BIDAMT) {
                    throw new ParameterException(ParameterException.ILLEGAL_BID_AMT_PARAMETER_EXCEPTION);
                }
            }
        }
    }

    /**
     * 키워드 목록의 이름 유효성 검사
     * @param keywordList 키워드 목록
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public static void validateAdKeywordListName(List<AdKeyword> keywordList) throws ParameterException {
        log.debug("키워드 목록의 이름 유효성 검사");
        for (AdKeyword keyword : keywordList) {
            // name(keyword)를 가지고 있는지 검사
            if (keyword.getKeyword() == null) {
                throw new ParameterException(ParameterException.NULL_NAME_PARAMETER_EXCEPTION);
            }
            // 공백 제거
            keyword.setKeyword(keyword.getKeyword().replaceAll(" ", ""));
            // name 글자수 유효한가
            if (keyword.getKeyword().length() < MIN_KEYWORD_NAME_LENGTH || keyword.getKeyword().length() > MAX_KEYWORD_NAME_LENGTH) {
                throw new ParameterException(ParameterException.ILLEGAL_KEYWORD_NAME_PARAMETER_EXCEPTION);
            }
        }
    }

    /**
     * 파워링크 소재 등록 시 유효성 검사하는 메소드
     * @param ad 파워링크 소재
     * @throws ParameterException 파라미터 예외시 발생
     */
    public static void validateAdCreate(Ad ad) throws Exception {
        log.debug("소재 등록 파라미터 유효성 검사 - 파워링크");
        if (ad.getNccAdgroupId() == null) {
            throw new ParameterException(ParameterException.NULL_ADGROUP_ID_PARAMETER_EXCEPTION);
        }
        if (ad.getType() == null) {
            throw new ParameterException(ParameterException.NULL_AD_TYPE_PARAMETER_EXCEPTION);
        }
        validatePowerLinkAd((PowerLinkAd) ad);
    }

    /**
     * 쇼핑 검색 소재 등록 시 유효성 검사하는 메소드
     * @param adList 쇼핑검색 소재 목록
     * @throws ParameterException 파라미터 예외시 발생
     */
    public static void validateAdCreate(List<Ad> adList) throws Exception {
        log.debug("소재 등록 파라미터 유효성 검사 - 쇼핑검색");
        ShoppingAd temp;
        for (Ad ad : adList) {
            if (ad.getNccAdgroupId() == null) {
                throw new ParameterException(ParameterException.NULL_ADGROUP_ID_PARAMETER_EXCEPTION);
            }
            if (ad.getType() == null) {
                throw new ParameterException(ParameterException.NULL_AD_TYPE_PARAMETER_EXCEPTION);
            }
            temp = (ShoppingAd) ad;
            if (temp.getReferenceKey() == null) {
                throw new ParameterException(ParameterException.NULL_REFERENCE_KEY_PARAMETER_EXCEPTION);
            }
            validateShoppingAd(temp);
        }
    }

    /**
     * 쇼핑 검색 소재의 개별입찰가, 그룹 입찰가 사용여부 유효성을 검사하는 메소드
     * @param ad 쇼핑검색 소재 객체
     * @throws ParameterException 파라미터 예외
     */
    private static void validateShoppingAd(ShoppingAd ad) throws ParameterException {
        Map adOfAd = (Map) ad.getAdAttr();
        if (adOfAd == null) {
            throw new ParameterException(ParameterException.NULL_SHOPPING_SEARCH_PARAMETER_EXCEPTION);
        } else {
            Long bidAmt = (Long) adOfAd.get("bidAmt");
            Boolean useGroupBidAmt = (Boolean) adOfAd.get("useGroupBidAmt");
            if (bidAmt == null || useGroupBidAmt == null) {
                throw new ParameterException(ParameterException.NULL_SHOPPING_AD_PARAMETER_EXCEPTION);
            } else {
                validateShoppingBidAmt(bidAmt);
            }
        }
    }

    /**
     * 쇼핑 소재 기본 입찰가 범위 유효성 검사
     * @param bidAmt 기본 입찰가
     * @throws ParameterException 파라미터 예외
     */
    private static void validateShoppingBidAmt(Long bidAmt) throws ParameterException {
        log.debug("기본 입찰가 범위 유효성 검사");
        if (bidAmt < MIN_SHOPPING_AD_BIDAMT || bidAmt > MAX_SHOPPING_AD_BIDAMT) {
            throw new ParameterException(ParameterException.ILLEGAL_SHOPPING_BID_AMT_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 파워링크 소재의 TND 유효성을 검사하는 메소드
     * @param ad 파워링크 소재 객체
     * @throws ParameterException 파라미터 예외
     */
    public static void validatePowerLinkAd(PowerLinkAd ad) throws Exception {
        Map<String, Object> adOfAd = ad.getAd();
        if (adOfAd.containsKey("pc") == false || adOfAd.containsKey("mobile") == false || adOfAd.containsKey("description") == false || adOfAd.containsKey("headline") == false) {
            throw new ParameterException(ParameterException.NULL_TND_PARAMETER_EXCEPTION);
        } else {
            Map pcMap = (Map) adOfAd.get("pc");
            String pcUrl = (String) pcMap.get("final");
            validateUrl((pcUrl));
            Map mobileMap = (Map) adOfAd.get("mobile");
            String mobileUrl = (String) mobileMap.get("final");
            validateUrl(mobileUrl);
            String description = (String) adOfAd.get("description");
            validateDescription(description);
            String headline = (String) adOfAd.get("headline");
            validateHeadline(headline);
        }
    }

    /**
     * 파워링크 소재 헤드라인 유효성 검사
     * @param headline 소재 헤드라인
     * @throws Exception 파라미터 예외
     */
    public static void validateHeadline(String headline) throws Exception {
        if (headline.length() < MIN_HEADLINE_LENGTH || headline.length() > MAX_HEADLINE_LENGTH) {
            throw new ParameterException(ParameterException.ILLEGAL_HEADLINE_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 파워링크 소재 설명(디스크립션) 유효성 검사
     * @param description 소재 설명(디스크립션)
     * @throws Exception 파라미터 예외
     */
    public static void validateDescription(String description) throws Exception {
        if (description.length() < MIN_DESCRIPTION_LENGTH || description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new ParameterException(ParameterException.ILLEGAL_DESCRIPTION_PARAMETER_EXCEPTION);
        }
    }

    /**
     * URL 도메인 유효성 검사
     * @param url 링크(URL)
     * @throws ParameterException 파라미터 예외
     */
    public static void validateUrl(String url) throws ParameterException {
        if (!Pattern.matches("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$", url)) {
            throw new ParameterException(ParameterException.ILLEGAL_URL_PARAMETER_EXCEPTION);
        }
    }
}
