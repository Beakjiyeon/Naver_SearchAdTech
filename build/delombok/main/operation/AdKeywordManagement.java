package operation;

import exception.NapiException;
import exception.ParameterException;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import lombok.NonNull;
import model.*;
import util.AppSetting;
import util.AppSetting.*;
import util.RestClient;
import java.security.GeneralSecurityException;
import java.util.*;
import static operation.Validation.*;
import static util.ResponseUtil.getResultFromRes;

/**
 * 키워드 등록, 수정, 조회, 중지 메소드를 가진 클래스
 */
public class AdKeywordManagement {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdKeywordManagement.class);

    /**
     * 키워드 목록 등록 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 키워드를 등록할 그룹 ID
     * @param adKeywordList 등록할 키워드 목록 정보
     * @return 등록된 키워드 목록 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<AdKeyword> adKeywordIdList = new ArrayList<AdKeyword>();
     *
     * // PC, MOBILE 둘 다 넣어줘야 한다.
     * Link link = new Link("https://www.naver.com", "https://www.naver.com");
     * // ObjectMapper
     * ObjectMapper objectMapper = new ObjectMapper();
     * Map map = objectMapper.convertValue(link , Map.class);
     * AdKeyword k1 = AdKeyword.builder().keyword("카페").links(map).build();
     * AdKeyword k2 = AdKeyword.builder().keyword("모카").bidAmt(100L).useGroupBidAmt(false).build();
     * AdKeyword k3 = AdKeyword.builder().keyword("라떼").bidAmt(100L).build();
     * AdKeyword k4 = AdKeyword.builder().keyword("카공").bidAmt(0L).useGroupBidAmt(true).build(); //0이면 그룹 입찰가 사용
     * adKeywordIdList.add(k1);
     * adKeywordIdList.add(k2);
     * adKeywordIdList.add(k3);
     * adKeywordIdList.add(k4);
     * String adGroupId = "grp-a001-01-000000021714177";
     * List<AdKeyword> result = adKeywordManagement.createAdKeywordList(restClient, adGroupId, adKeywordIdList);
     *
     * // 일부만 성공되는 경우, 실패한 키워드(keyword) 목록을 예외 메세지로 반환
     * // ex. exception.NapiException: [{keyword : 라떼, code : 3916, message : No value is entered in the default bid or bid amount field.}]
     * }
     * </pre>
     */
    public List<AdKeyword> createAdKeywordList(@NonNull RestClient restClient, @NonNull String adGroupId, @NonNull List<AdKeyword> adKeywordList) throws GeneralSecurityException, ParameterException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adGroupId == null) {
            throw new NullPointerException("adGroupId is marked non-null but is null");
        }
        if (adKeywordList == null) {
            throw new NullPointerException("adKeywordList is marked non-null but is null");
        }
        log.debug("키워드 목록 등록 요청");
        validateKeywordCreate(adKeywordList);
        log.debug("키워드 목록 등록 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.post("/ncc/keywords", AppSetting.customerId).queryString("nccAdgroupId", adGroupId).body(adKeywordList).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 특정 그룹에 속한 키워드 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 키워드를 등록할 그룹 ID
     * @return 그룹에 속한 키워드 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public List<AdKeyword> getAdKeywordByAdGroupId(@NonNull RestClient restClient, @NonNull String adGroupId) throws GeneralSecurityException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adGroupId == null) {
            throw new NullPointerException("adGroupId is marked non-null but is null");
        }
        log.debug("특정 그룹에 속한 키워드 조회 요청");
        log.debug("키워드 목록 등록 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.get("/ncc/keywords", AppSetting.customerId).queryString("nccAdgroupId", adGroupId).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 단일 키워드 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordId 조회할 키워드 ID
     * @return 키워드 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public AdKeyword getAdKeyword(@NonNull RestClient restClient, @NonNull String adKeywordId) throws GeneralSecurityException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adKeywordId == null) {
            throw new NullPointerException("adKeywordId is marked non-null but is null");
        }
        log.debug("단일 키워드 조회 요청");
        log.debug("단일 키워드 조회 API 요청");
        HttpResponse<AdKeyword> r = restClient.get("/ncc/keywords/" + adKeywordId, AppSetting.customerId).asObject(AdKeyword.class);
        return (AdKeyword) getResultFromRes(r, "keyword");
    }

    /**
     * 키워드 ID 들에 해당하는 키워드 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordIdList 키워드 ID 목록
     * @return 키워드 목록 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public List<AdKeyword> getAdKeywordList(@NonNull RestClient restClient, @NonNull List<String> adKeywordIdList) throws GeneralSecurityException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adKeywordIdList == null) {
            throw new NullPointerException("adKeywordIdList is marked non-null but is null");
        }
        log.debug("키워드 ID 들에 해당하는 키워드 조회 요청");
        log.debug("키워드 ID 들에 해당하는 키워드 조회 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.get("/ncc/keywords", AppSetting.customerId).queryString("ids", adKeywordIdList).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 키워드 1개 기본 입찰가 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param adKeywordId 키워드 ID
     * @param bidAmt 기본 입찰가
     * @return 수정된 키워드 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public AdKeyword updateKeywordBidAmt(@NonNull RestClient restClient, @NonNull String adGroupId, @NonNull String adKeywordId, @NonNull Long bidAmt) throws GeneralSecurityException, ParameterException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adGroupId == null) {
            throw new NullPointerException("adGroupId is marked non-null but is null");
        }
        if (adKeywordId == null) {
            throw new NullPointerException("adKeywordId is marked non-null but is null");
        }
        if (bidAmt == null) {
            throw new NullPointerException("bidAmt is marked non-null but is null");
        }
        log.debug("키워드 기본 입찰가 수정 요청");
        boolean useGroupBidAmt = false;
        validateBidAmt(bidAmt);
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdgroupId", adGroupId);
        params.put("nccKeywordId", adKeywordId);
        params.put("bidAmt", bidAmt);
        params.put("useGroupBidAmt", useGroupBidAmt);
        log.debug("키워드 기본 입찰가 수정 API 요청");
        HttpResponse<AdKeyword> r = restClient.put("/ncc/keywords/" + adKeywordId, AppSetting.customerId).queryString("fields", "bidAmt").body(params).asObject(AdKeyword.class);
        return (AdKeyword) getResultFromRes(r, "keyword");
    }

    /**
     * 키워드 활성화여부 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param adKeywordId 키워드 ID
     * @param userLock 활성화 여부
     * @return 수정된 키워드 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public AdKeyword updateKeywordUserLock(@NonNull RestClient restClient, @NonNull String adGroupId, @NonNull String adKeywordId, @NonNull Boolean userLock) throws GeneralSecurityException, NapiException {
        if (restClient == null) {
            throw new NullPointerException("restClient is marked non-null but is null");
        }
        if (adGroupId == null) {
            throw new NullPointerException("adGroupId is marked non-null but is null");
        }
        if (adKeywordId == null) {
            throw new NullPointerException("adKeywordId is marked non-null but is null");
        }
        if (userLock == null) {
            throw new NullPointerException("userLock is marked non-null but is null");
        }
        log.debug("키워드 활성화여부 수정 요청");
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("nccKeywordId", adKeywordId);
        params.put("userLock", userLock);
        log.debug("키워드 활성화여부 수정 요청");
        HttpResponse<AdKeyword> r = restClient.put("/ncc/keywords/" + adKeywordId, AppSetting.customerId).queryString("fields", "userLock").body(params).asObject(AdKeyword.class);
        return (AdKeyword) getResultFromRes(r, "keyword");
    }


    /**
     * PC, Mobile 랜딩 URL 정보를 담은 클래스
     */
    class LinkInfo {
        Map<String, String> pc;
        Map<String, String> mobile;

        public LinkInfo(Map<String, String> pc, Map<String, String> mobile) {
            this.mobile = mobile;
            this.pc = pc;
        }

        public Map<String, String> getPc() {
            return this.pc;
        }

        public Map<String, String> getMobile() {
            return this.mobile;
        }
    }

    /**
     * 키워드 랜딩 링크 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param adKeywordId 키워드 ID
     * @param link PC, Mobile 랜딩 링크 정보
     * @return 수정된 키워드 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public AdKeyword updateKeywordLink(RestClient restClient, String adGroupId, String adKeywordId, Link link) throws GeneralSecurityException, ParameterException, NapiException {
        log.debug("키워드 랜딩 링크 수정 요청");
        if (adGroupId == null || link == null || adKeywordId == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        if (link.getPc() != null) {
            validateUrl(link.getPc().get("final"));
        }
        if (link.getMobile() != null) {
            validateUrl(link.getMobile().get("final"));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("nccKeywordId", adKeywordId);
        Map<String, String> pc = new HashMap<>();
        pc.put("final", link.getPc().get("final"));
        Map<String, String> mobile = new HashMap<>();
        mobile.put("final", link.getMobile().get("final"));
        LinkInfo linkInfo = new LinkInfo(pc, mobile);
        // LinkInfo linkInfo = new LinkInfo(pc);
        params.put("links", linkInfo);
        log.debug("키워드 랜딩 링크 수정 API 요청");
        HttpResponse<AdKeyword> r = restClient.put("/ncc/keywords/" + adKeywordId, AppSetting.customerId).queryString("fields", "links").body(params).asObject(AdKeyword.class);
        return (AdKeyword) getResultFromRes(r, "keyword");
    }

    /**
     * 키워드 목록 개별 입찰가 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordList 키워드 목록
     * @return 수정된 키워드 목록 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();
     * AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").useGroupBidAmt(false).bidAmt(380L).build();
     * AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").useGroupBidAmt(false).bidAmt(180L).build();
     * adKeywordList.add(adk1);
     * adKeywordList.add(adk2);
     * List<AdKeyword> result = adKeywordManagement.updateKeywordListBidAmt(restClient, adKeywordList);
     * }
     * </pre>
     */
    public List<AdKeyword> updateKeywordListBidAmt(RestClient restClient, List<AdKeyword> adKeywordList) throws GeneralSecurityException, ParameterException, NapiException {
        log.debug("키워드 목록 개별 입찰가 수정 요청");
        validateAdKeywordListBidAmt(adKeywordList);
        log.debug("키워드 목록 개별 입찰가 수정 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.put("/ncc/keywords", AppSetting.customerId).queryString("fields", "bidAmt").body(adKeywordList).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 키워드 목록 활성화여부 수정 메소드 : 각 키워드 별 개별 수정 가능
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordList 키워드 목록
     * @return 수정된 키워드 목록 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     *
     * 일부만 성공되는 경우 없음
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();
     * AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").userLock(false).build();
     * AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").userLock(true).build();
     * adKeywordList.add(adk1);
     * adKeywordList.add(adk2);
     * List<AdKeyword> result = adKeywordManagement.updateKeywordListUserLock(restClient, adKeywordList);
     * }
     * </pre>
     */
    public List<AdKeyword> updateKeywordListUserLock(RestClient restClient, List<AdKeyword> adKeywordList) throws GeneralSecurityException, NapiException {
        log.debug("키워드 목록 활성화여부 수정 메소드");
        log.debug("키워드 목록 활성화여부 수정 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.put("/ncc/keywords", AppSetting.customerId).queryString("fields", "userLock").body(adKeywordList).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 키워드 목록 링크 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordList 키워드 목록
     * @return 수정된 키워드 목록 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();
     *
     * Link link = new Link("https://www.naver.com", "https://www.naver.com");
     *
     * ObjectMapper objectMapper = new ObjectMapper();
     * Map map = objectMapper.convertValue(link , Map.class);
     *
     * AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").links(map).build();
     * AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").links(map).build();
     * adKeywordList.add(adk1);
     * adKeywordList.add(adk2);
     * List<AdKeyword> result = adKeywordManagement.updateKeywordListLink(restClient, adKeywordList);
     * }
     * </pre>
     */
    public List<AdKeyword> updateKeywordListLink(RestClient restClient, List<AdKeyword> adKeywordList) throws GeneralSecurityException, ParameterException, NapiException {
        log.debug("키워드 목록 랜딩 링크 수정 요청");
        validateAdKeywordListLink(adKeywordList);
        log.debug("키워드 목록 랜딩 링크 수정 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.put("/ncc/keywords", AppSetting.customerId).queryString("fields", "links").body(adKeywordList).asObject(new GenericType<List<AdKeyword>>() {
        });
        return (List<AdKeyword>) getResultFromRes(r, "keywords");
    }

    /**
     * 단일 키워드 삭제
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordId 키워드 ID
     * @return 삭제된 키워드 ID
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public String deleteAdKeyword(RestClient restClient, String adKeywordId) throws GeneralSecurityException, NapiException {
        log.debug("단일 키워드 삭제 요청");
        log.debug("단일 키워드 삭제 API 요청");
        HttpResponse<AdKeyword> r = restClient.delete("/ncc/keywords/" + adKeywordId, AppSetting.customerId).asObject(AdKeyword.class);
        getResultFromRes(r, "keyword");
        return adKeywordId;
    }

    /**
     * 키워드 목록 삭제
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adKeywordIdList 키워드 ID 목록
     * @return 삭제된 키워드 ID 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws GeneralSecurityException 인증 예외
     */
    public List<String> deleteAdKeywordList(RestClient restClient, List<String> adKeywordIdList) throws GeneralSecurityException, NapiException {
        log.debug("키워드 목록 삭제 요청");
        log.debug("키워드 목록 삭제 API 요청");
        HttpResponse<List<AdKeyword>> r = restClient.delete("/ncc/keywords", AppSetting.customerId).queryString("ids", adKeywordIdList).asObject(new GenericType<List<AdKeyword>>() {
        });
        getResultFromRes(r, "keywords");
        return adKeywordIdList;
    }
}
