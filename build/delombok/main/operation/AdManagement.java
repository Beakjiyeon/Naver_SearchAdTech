package operation;

import common.AdGroupTypes;
import common.AdTypes;
import exception.NapiException;
import exception.ParameterException;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import model.*;
import util.AppSetting;
import util.RestClient;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import static operation.Validation.validateAdCreate;
import static util.ResponseUtil.getResultFromRes;

/**
 * 소재 등록, 수정, 조회, 중지, 복사 메소드를 가진 클래스
 */
public class AdManagement {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdManagement.class);

    /**
     * 파워 링크 소재 등록 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param ad 소재 정보
     * @return 등록된 소재 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * 필수 정보 : PC 랜딩 URL, Mobile 랜딩 URL, 그룹 ID, 소재 유형
     * 옵션 정보 : 검수 요청 메세지
     * String pcUrl = "https://www.naver.com";
     * String mobileUrl = "https://www.naver.com";
     * String headline = "헤드라인2";
     * String description = "디베이스의 모든 것, 디베이스앤 공식 온라인스토어";
     * TextNdescription textNdescription = new TextNdescription(pcUrl, mobileUrl, headline, description);
     * PowerLinkAd powerLinkAd = new PowerLinkAd("grp-a001-01-000000021714177", TEXT_45, textNdescription);
     * }
     * </pre>
     */
    public PowerLinkAd createPowerLinkAd(RestClient restClient, Ad ad) throws Exception {
        log.debug("파워링크 소재 등록 요청");
        validateAdCreate(ad);
        log.debug("파워링크 소재 등록 API 요청");
        System.out.println(ad.toString());
        HttpResponse<Ad> r = restClient.post("/ncc/ads", AppSetting.customerId).body(ad).asObject(PowerLinkAd.class);
        return (PowerLinkAd) getResultFromRes(r, "ad");
    }

    /**
     * 쇼핑 검색 소재 등록 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adList 소재 목록 정보
     * @return 등록된 소재 목록 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<Ad> adList = new ArrayList<Ad>();
     * Ad shoppingAd= new ShoppingAd("grp-a001-02-000000021722250", CATALOG_AD, customerId,"27436193522", false, 90L, "MAKER");
     * adList.add(shoppingAd);
     * List<ShoppingAd> result = adManagement.createShoppingAd(restClient, adList);
     * }
     * </pre>
     */
    public List<ShoppingAd> createShoppingAd(RestClient restClient, List<Ad> adList) throws Exception {
        log.debug("쇼핑 검색 소재 등록 요청");
        validateAdCreate(adList);
        HttpResponse<List<ShoppingAd>> r = restClient.post("/ncc/ads", AppSetting.customerId).queryString("isList", true).body(adList).asObject(new GenericType<List<ShoppingAd>>() {
        });
        return (List<ShoppingAd>) getResultFromRes(r, "ad");
    }

    /**
     * 단일 소재 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adId 소재 ID
     * @param adType 소재 유형
     * @return 소재 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Object getAd(RestClient restClient, String adId, AdTypes adType) throws SignatureException, NapiException {
        // 소재 타입 확인
        if (adType == AdTypes.TEXT_45) {
            HttpResponse<PowerLinkAd> r1 = restClient.get("/ncc/ads/" + adId, AppSetting.customerId).asObject(PowerLinkAd.class);
            return (PowerLinkAd) getResultFromRes(r1, "adp");
        } else {
            HttpResponse<ShoppingAd> r2 = restClient.get("/ncc/ads/" + adId, AppSetting.customerId).asObject(ShoppingAd.class);
            return (ShoppingAd) getResultFromRes(r2, "ads");
        }
    }

    /**
     * 단일 소재 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adId 소재 ID
     * @return 소재 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Object getAd(RestClient restClient, String adId) throws SignatureException, NapiException {
        HttpResponse<AdDto> r = restClient.get("/ncc/ads/" + adId, AppSetting.customerId).asObject(AdDto.class);
        return (AdDto) getResultFromRes(r, "adDto");
    }

    /**
     * 소재 ID 목록 에 해당하는 소재 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adIdList 소재 ID
     * @return 소재 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public List<AdDto> getAdList(RestClient restClient, List<String> adIdList) throws SignatureException, NapiException {
        HttpResponse<List<AdDto>> r = restClient.get("/ncc/ads", AppSetting.customerId).queryString("ids", adIdList).asObject(new GenericType<List<AdDto>>() {
        });
        return (List<AdDto>) getResultFromRes(r, "adDto");
    }

    /**
     * 특정 그룹 ID (파워링크)에 해당하는 소재 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 소재 ID
     * @return 소재 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public List<PowerLinkAd> getAdByAdPowerLinkGroupId(RestClient restClient, String adGroupId) throws SignatureException, NapiException {
        HttpResponse<List<PowerLinkAd>> r = restClient.get("/ncc/ads", AppSetting.customerId).queryString("nccAdgroupId", adGroupId).asObject(new GenericType<List<PowerLinkAd>>() {
        });
        return (List<PowerLinkAd>) getResultFromRes(r, "adp");
    }

    /**
     * 특정 그룹 ID (쇼핑검색)에 해당하는 소재 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 소재 ID
     * @return 소재 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public List<ShoppingAd> getAdByAdShoppingGroupId(RestClient restClient, String adGroupId) throws SignatureException, NapiException {
        HttpResponse<List<ShoppingAd>> r = restClient.get("/ncc/ads", AppSetting.customerId).queryString("nccAdgroupId", adGroupId).asObject(new GenericType<List<ShoppingAd>>() {
        });
        return (List<ShoppingAd>) getResultFromRes(r, "ads");
    }

    /**
     * 소재 활성화 여부 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param ad 소재 정보 (소재 ID, 활성화 여부 정보 / 쇼핑검색에 경우, 개별 입찰가와 그룹입찰가 사용여부 정보가 추가적으로 필요)
     * @return 수정된 소재 ID, 활성화여부 정보가 포함된 객체
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Ad updateAdUserLock(RestClient restClient, Ad ad) throws GeneralSecurityException, NapiException, ParameterException {
        if (ad.getNccAdId() == null) {
            throw new ParameterException(ParameterException.NULL_AD_ID_PARAMETER_EXCEPTION);
        }
        if (ad.getUserLock() == null) {
            throw new ParameterException(ParameterException.NULL_USERLOCK_PARAMETER_EXCEPTION);
        }
        HttpResponse<Ad> r = restClient.put("/ncc/ads/" + ad.getNccAdId(), AppSetting.customerId).queryString("fields", "userLock").body(ad).asObject(Ad.class);
        return (Ad) getResultFromRes(r, "ad");
    }

    // 소재검토요청메세지 수정
    /**
     * 소재 검토요청 메세지 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param ad 소재 정보 (소재 ID, 검토요청 메세지 / 쇼핑검색에 경우, 개별 입찰가와 그룹입찰가 사용여부 정보가 추가적으로 필요)
     * @return 수정된 소재 ID, 검토요청 메세지 정보를 담은 객체
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Ad updateAdInspectRequestMsg(RestClient restClient, Ad ad) throws GeneralSecurityException, NapiException, ParameterException {
        if (ad.getNccAdId() == null) {
            throw new ParameterException(ParameterException.NULL_AD_ID_PARAMETER_EXCEPTION);
        }
        if (ad.getInspectRequestMsg() == null) {
            throw new ParameterException(ParameterException.NULL_INSPECT_MSG_PARAMETER_EXCEPTION);
        }
        // 검토 상태가 0 이면, 검토요청메세지를 수정할 수 없음. 이건 네이버 에러로 처리
        HttpResponse<Ad> r = restClient.put("/ncc/ads/" + ad.getNccAdId(), AppSetting.customerId).queryString("fields", "inspect").body(ad).asObject(Ad.class);
        return (Ad) getResultFromRes(r, "ad");
    }

    /**
     * 소재 삭제 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adId 소재 ID
     * @return 삭제된 소재 ID
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증예외
     */
    public String deleteAd(RestClient restClient, String adId) throws GeneralSecurityException, NapiException {
        HttpResponse<Ad> r = restClient.delete("/ncc/ads/" + adId, AppSetting.customerId).asObject(Ad.class);
        getResultFromRes(r, "ad");
        return adId;
    }

    /**
     * 소재 복사 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adIdList 복사하고자 하는 소재 ID 목록
     * @param targetAdGroupId 복사한 소재를 넣을 그룹 ID
     * @param userLock 활성화 여부 (옵션)
     * @return 복사된 소재 ID 정보가 담긴 객체 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증예외
     */
    public List<Ad> copyAd(RestClient restClient, List<String> adIdList, String targetAdGroupId, Boolean userLock) throws GeneralSecurityException, NapiException {
        HttpResponse<List<Ad>> r =  // 소재 아이디
        restClient.put("/ncc/ads", AppSetting.customerId).queryString("ids", adIdList).queryString("targetAdgroupId", targetAdGroupId).queryString("userLock", userLock).asObject(new GenericType<List<Ad>>() {
        });
        return (List<Ad>) getResultFromRes(r, "ad");
    }
}
