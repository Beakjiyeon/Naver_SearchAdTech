package operation;

import exception.NapiException;
import exception.ParameterException;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import model.*;
import common.*;
import util.AppSetting;
import util.RestClient;
import java.text.ParseException;
import java.util.List;
import static operation.Validation.*;
import static util.ResponseUtil.*;
import java.security.SignatureException;

/**
 * 캠페인 등록, 수정, 조회, 중지 기능을 관리하는 클래스
 */
public class CampaignManagement {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CampaignManagement.class);

    /**
     * 캠페인 등록 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaign 등록할 캠페인 정보
     * @return 등록된 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * Campaign campaign = Campaign.builder()
     *                 .name("캠페인 이름") // 1~30자, 한글/영어/숫자/공백/특수문자(_,#,-,.) 허용
     *                 .campaignTp(CampaignTypes.WEB_SITE)
     *                 .customerId(AppSetting.customerId)
     *                 .dailyBudget(0L) // 0L : 제한 없음 (useDailyBudget 필드 사용불가)
     *                 .deliveryMethod(DeliveryMethodTypes.STANDARD)
     *                 .build()
     * Campaign result = campaignMangement.createCampaign(restClient, campaign);
     * }
     * </pre>
     */
    public Campaign createCampaign(RestClient restClient, Campaign campaign) throws Exception {
        log.debug("캠페인 등록 요청");
        validateCampaignCreate(campaign);
        log.debug("캠페인 등록 API 요청");
        HttpResponse<Campaign> r = restClient.post("/ncc/campaigns", AppSetting.customerId).body(campaign).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }

    /**
     * 단일 캠페인 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaignId 조회할 캠페인의 ID
     * @return ID에 해당하는 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생 (EX. ID == NULL)
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생 (EX. 유효하지 않은 ID)
     */
    public Campaign getCampaign(RestClient restClient, String campaignId) throws Exception {
        log.debug("캠페인 조회 요청");
        log.debug("캠페인 조회 API 요청");
        if (campaignId == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
        }
        HttpResponse<Campaign> r = restClient.get("/ncc/campaigns/" + campaignId, AppSetting.customerId).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }

    /**
     * 전체 캠페인 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @return 전체 캠페인 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     */
    public List<Campaign> getCampaignList(RestClient restClient) throws Exception {
        log.debug("전체 캠페인 목록 조회 요청");
        log.debug("전체 캠페인 목록 조회 API 요청");
        HttpResponse<List<Campaign>> r = restClient.get("/ncc/campaigns", AppSetting.customerId).asObject(new GenericType<List<Campaign>>() {
        });
        return (List<Campaign>) getResultFromRes(r, "campaign");
    }

    /**
     * 캠페인 ID 목록에 해당하는 캠페인 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaignIdList 조회할 캠페인의 ID 목록
     * @return ID에 해당하는 캠페인 목록
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생 (EX. ID == NULL)
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<String> campaignIdList = new ArrayList<>();
     * campaignIdList.add("cmp-a001-01-000000000000000");
     * campaignIdList.add("cmp-a001-01-000000000000001");
     * List<Campaign> result = campaignMangement.getCampaignListByIds(restClient, campaignIdList);
     * }
     * </pre>
     */
    public List<Campaign> getCampaignListByIds(RestClient restClient, List<String> campaignIdList) throws Exception {
        log.debug("캠페인 ID 들에 해당하는 캠페인 목록 조회 요청");
        log.debug("캠페인 ID 들에 해당하는 캠페인 목록 조회 API 요청");
        for (String id : campaignIdList) {
            log.debug("캠페인 ID >> " + id);
            if (id == null) {
                throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
            }
        }
        HttpResponse<List<Campaign>> r = restClient.get("/ncc/campaigns", AppSetting.customerId).queryString("ids", campaignIdList).asObject(new GenericType<List<Campaign>>() {
        });
        return (List<Campaign>) getResultFromRes(r, "campaign");
    }

    /**
     * 특정 유형에 해당하는 캠페인 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaignType 캠페인 유형
     * @return 해당 유형에 해당하는 캠페인 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     */
    public List<Campaign> getCampaignListByType(RestClient restClient, CampaignTypes campaignType) throws Exception {
        log.debug("해당 유형 캠페인 목록 조회 요청");
        if (campaignType == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_TP_PARAMETER_EXCEPTION);
        }
        log.debug("해당 유형  캠페인 목록 조회 API 요청");
        HttpResponse<List<Campaign>> r = restClient.get("/ncc/campaigns", AppSetting.customerId).queryString("campaignType", campaignType).asObject(new GenericType<List<Campaign>>() {
        });
        return (List<Campaign>) getResultFromRes(r, "campaign");
    }

    /**
     * 캠페인 하루 예산 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param budget {캠페인 ID, 활성화여부, 하루 예산} 객체
     * @return 수정된 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * Budget budget = Budget.builder()
     *                 .nccCampaignId("cmp-a001-01-000000004213399")
     *                 .userLock(false)
     *                 .dailyBudget(0L) // 0L : 제한 없음 (useDailyBudget 필드 사용불가)
     *                 // 하루예산 설정 시, useDailyBudget 필드를 설정할 수 없습니다.
     *                 // 하루예산 '제한없음'으로 설정을 원하는 경우 dailyBudget=0L 로 입력하세요.
     *                 .build();
     * Campaign result = campaignMangement.updateCampaignBudget(restClient, budget);
     * }
     * </pre>
     */
    public Campaign updateCampaignBudget(RestClient restClient, Budget budget) throws Exception {
        log.debug("캠페인 하루 예산 수정 요청");
        if (budget == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        } else {
            if (budget.getNccCampaignId() == null) {
                throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
            }
            if (budget.getDailyBudget() == null) {
                throw new ParameterException(ParameterException.NULL_DAILY_BUDGET_PARAMETER_EXCEPTION);
            }
            if (budget.getUserLock() == null) {
                throw new ParameterException(ParameterException.NULL_USERLOCK_PARAMETER_EXCEPTION);
            }
            if (budget.getUseDailyBudget() != null) {
                throw new ParameterException(ParameterException.NULL_USE_DAILY_BUDGET_PARAMETER_EXCEPTION);
            }
        }
        log.debug("캠페인 ID를 이용한 캠페인 조회 API 호출");
        Campaign campaign = getCampaign(restClient, budget.getNccCampaignId());
        CampaignTypes tp = campaign.getCampaignTp();
        /*
        [ 캠페인 유형을 Budget.class 에서 입력받지 않고, API 호출 로직으로 진행한 이유 ]
          캠페인 유형 정보를 직접 입력받을 경우, 해당 캠페인 ID와 유형정보가 일치하지 않을 예외가 있다.
         만약 파워링크 캠페인의 하루 예산을 수정하고자 하면서,
         사용자의 실수로 캠페인 유형으로 쇼핑광고를, 하루예산으로 유효하지 않은 범위를 입력한다면

         이 프로그램은 쇼핑광고의 하루예산 유효 범위를 알려주는 Exception 을 보내므로,
         사용자는 유효한 파워링크 캠페인 하루 예산 범위를 알 수 없다.
         따라서 DB 로부터 해당 캠페인 ID의 캠페인 유형을 조회 후 하루예산 값의 유효성 검사를 진행한다.
         */
        budget.setUseDailyBudget(validateDailyBudget(tp, budget.getDailyBudget()));
        log.debug("캠페인 하루 예산 수정 API 호출");
        HttpResponse<Campaign> r = restClient.put("/ncc/campaigns/" + budget.getNccCampaignId(), AppSetting.customerId).queryString("fields", "budget").body(budget).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }

    /**
     * 캠페인 노출기간 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param period {캠페인 ID, 활성화여부, 노출 시작일, 노출 종료일, 노출 기간 사용여부} 객체
     * @return 수정된 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws ParseException 날짜 포맷 메소드에서 문제 시 발생
     * @throws SignatureException 인증 예외
     *
     * <em>
     *     캠페인 노출 시작, 종료 변수  : util.DateFormat.getUTCDate(int year, int month, int day) 메소드 사용 가능
     * </em>
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * Period period = Period.builder()
     *                 .nccCampaignId("cmp-a001-01-000000004213399")
     *                 .usePeriod(true)
     *                 .periodStartDt(getUTCDate(2021,5,9))
     *                 .periodEndDt(getUTCDate(2021,9,3))
     *                 .userLock(false)
     *                 .build();
     * Campaign result = campaignMangement.updateCampaignPeriod(restClient, period);
     * }
     * </pre>
     */
    public Campaign updateCampaignPeriod(RestClient restClient, Period period) throws SignatureException, NapiException, ParameterException, ParseException {
        log.debug("캠페인 노출기간 수정 요청");
        Campaign campaign = Campaign.builder().nccCampaignId(period.getNccCampaignId()).usePeriod(period.getUsePeriod()).periodStartDt(period.getPeriodStartDt()).periodEndDt(period.getPeriodEndDt()).userLock(period.getUserLock()).build();
        validatePeriods(campaign); // 유효성 검사
        period.setUsePeriod(campaign.getUsePeriod());
        period.setPeriodStartDt(campaign.getPeriodStartDt());
        period.setPeriodEndDt(campaign.getPeriodEndDt());
        log.debug("캠페인 노출기간 수정 API 호출");
        HttpResponse<Campaign> r = restClient.put("/ncc/campaigns/" + period.getNccCampaignId(), AppSetting.customerId).queryString("fields", "period").body(period).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }

    /**
     * 캠페인 하루예산, 노출기간, 이름, 추적모드, 예산소진전략 중에서 원하는 필드를 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaign {[필수]캠페인 ID, [필수]캠페인 활성화여부,
     *                 [옵션] 하루 예산, 하루 예산 사용여부, 노출 시작일, 노출 종료일, 노출 기간 사용여부, 이름, 추적 모드, 예산 소진 전략)} 객체
     * @return 수정된 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * Campaign c = Campaign.builder()
     *                 // 필수
     *                 .nccCampaignId("cmp-a001-01-000000004213399")
     *                 .userLock(true)
     *                 // 선택
     *                 .name("이름 수정합니다.")
     *                 .periodStartDt(getUTCDate(2021,3,7))
     *                 .periodEndDt(getUTCDate(2021,9,3))
     *                 .customerId(2248092L)
     *                 .dailyBudget(0L)
     *                 .deliveryMethod(ACCELERATED)
     *                 .trackingMode(AUTO_TRACKING_MODE)
     *                 .build();
     *         Campaign result = campaignMangement.updateCampaign(restClient, c);
     * }
     * </pre>
     */
    public Campaign updateCampaign(RestClient restClient, Campaign campaign) throws Exception {
        log.debug("캠페인 수정 요청");
        log.debug("캠페인 ID에 해당하는 캠페인 유형 조회");
        campaign.setCampaignTp(getCampaign(restClient, campaign.getNccCampaignId()).getCampaignTp());
        validateCampaignUpdate(campaign);
        log.debug("캠페인 수정 API 요청");
        HttpResponse<Campaign> r = restClient.put("/ncc/campaigns/" + campaign.getNccCampaignId(), AppSetting.customerId).body(campaign).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }

    /**
     * 캠페인 중지 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param userLock {캠페인 ID, 활성화 여부} 객체
     * @return 중지된 캠페인 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * UserLock userLock = UserLock.builder()
     *                 .nccCampaignId("cmp-a001-01-000000004213399")
     *                 .userLock(false) // 중지 : .userLock(true)
     *                 .build();
     * campaignMangement.updateCampaignUserLock(restClient, userLock);
     * }
     * </pre>
     */
    public Campaign updateCampaignUserLock(RestClient restClient, UserLock userLock) throws SignatureException, NapiException, ParameterException {
        log.debug("캠페인 중지 요청");
        log.debug("캠페인 활성화 여부 수정 API 요청");
        if (userLock.getNccCampaignId() == null) {
            throw new ParameterException(ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION);
        }
        HttpResponse<Campaign> r = restClient.put("/ncc/campaigns/" + userLock.getNccCampaignId(), AppSetting.customerId).queryString("fields", "userLock").body(userLock).asObject(Campaign.class);
        return (Campaign) getResultFromRes(r, "campaign");
    }
}
