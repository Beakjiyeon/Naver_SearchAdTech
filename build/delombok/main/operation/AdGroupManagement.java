package operation;

import common.*;
import exception.NapiException;
import exception.ParameterException;
import kong.unirest.HttpResponse;
import lombok.NonNull;
import model.*;
import util.*;
import java.util.*;
import kong.unirest.GenericType;
import java.security.SignatureException;
import static operation.Validation.*;
import static util.ResponseUtil.getResultFromRes;

/**
 * 그룹 등록, 수정, 조회, 삭제 메소드를 가진 클래스
 */
public class AdGroupManagement {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdGroupManagement.class);

    /**
     * 그룹 등록 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adgroup 등록할 그룹 정보
     * @return 등록된 그룹 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * // 타겟 생성
     * List<Target> targets = new ArrayList<>();
     * Target mobilePctarget = new Target();// PC_MOBILE_TARGET
     * Target timeWeekyTarget = new Target(); // TIME_WEEKLY_TARGET
     *
     * Map<String, Object> map = new HashMap<String,Object>();
     * map.put("pc", true);
     * map.put("mobile", false);
     * mobilePctarget.setTarget(map);
     * mobilePctarget.setTargetTp("PC_MOBILE_TARGET");
     * targets.add(mobilePctarget);
     *
     * Map<String, Object> schedule = Schedules.builder().friday(18, 19, 20).saturday(18, 19, 20).build();
     * timeWeekyTarget.setTarget(schedule);
     * timeWeekyTarget.setTargetTp("TIME_WEEKLY_TARGET");
     * targets.add(timeWeekyTarget);
     *
     * // 그룹 생성
     * Adgroup adgroupShopping = Adgroup.builder()
     *                 .name("쇼핑쇼핑")
     *                 .adgroupType(CATALOG)
     *                 .nccCampaignId("cmp-a001-02-000000004213611")
     *                 .mobileChannelId("bsn-a001-00-000000005273485")
     *                 .pcChannelId("bsn-a001-00-000000005273485")
     *                 .bidAmt(80L)
     *                 .contentsNetworkBidAmt(120L)
     *                 .useCntsNetworkBidAmt(false)
     *                 .dailyBudget(0L)
     *                 .targets(targets)
     *                 .build();
     * }
     * </pre>
     */
    public Adgroup createAdGroup(RestClient restClient, Adgroup adgroup) throws Exception {
        log.debug("그룹 등록 요청");
        validateAdGroupCreate(adgroup);
        log.debug("그룹 등록 API 요청");
        HttpResponse<Adgroup> r = restClient.post("/ncc/adgroups", AppSetting.customerId).body(adgroup).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 단일 그룹 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 조회할 그룹 ID
     * @return 그룹
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Adgroup getAdGroup(RestClient restClient, String adGroupId) throws SignatureException, NapiException {
        log.debug("단일 그룹 조회 요청");
        log.debug("단일 그룹 조회 API 요청");
        HttpResponse<Adgroup> r = restClient.get("/ncc/adgroups/" + adGroupId, AppSetting.customerId).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 특정 캠페인에 속한 그룹 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param campaignId 캠페인 ID
     * @return 그룹
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public List<Adgroup> getAdGroupListByCampaignId(RestClient restClient, String campaignId) throws SignatureException, NapiException {
        log.debug("캠페인에 속한 그룹 목록 조회 요청");
        log.debug("캠페인에 속한 그룹 목록 조회 API 요청");
        HttpResponse<List<Adgroup>> r = restClient.get("/ncc/adgroups/", AppSetting.customerId).queryString("nccCampaignId", campaignId).asObject(new GenericType<List<Adgroup>>() {
        });
        return (List<Adgroup>) getResultFromRes(r, "group");
    }

    /**
     * 그룹 ID 들에 해당하는 그룹 목록 조회 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupIdList 그룹 ID 목록
     * @return 그룹 목록
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public List<Adgroup> getAdGroupList(RestClient restClient, List<String> adGroupIdList) throws SignatureException, NapiException {
        log.debug("그룹 ID 들에 해당하는 속한 그룹 목록 조회");
        log.debug("그룹 ID 들에 해당하는 속한 그룹 목록 조회");
        HttpResponse<List<Adgroup>> r = restClient.get("/ncc/adgroups", AppSetting.customerId).queryString("ids", adGroupIdList).asObject(new GenericType<List<Adgroup>>() {
        });
        return (List<Adgroup>) getResultFromRes(r, "group");
    }

    /**
     * 그룹 입찰가 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param bidAmt 수정할 기본 입찰가
     * @return 수정된 그룹 정보
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Adgroup updateAdGroupBidAmt(RestClient restClient, String adGroupId, Long bidAmt) throws SignatureException, ParameterException, NapiException {
        log.debug("그룹 기본 입찰가 수정 요청");
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("bidAmt", bidAmt);
        validateBidAmt(bidAmt);
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "bidAmt").body(params).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 PC 플랫폼 입찰 가중치, Mobile 플랫폼 입찰 가중치 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param pcNetworkBidWeight 수정할 PC 입찰가 가중치
     * @param mobileNetworkBidWeight 수정할 Mobile 입찰가 가중치
     * @return 수정된 그룹
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Adgroup updateAdGroupNetworkBidWeight(RestClient restClient, String adGroupId, Long pcNetworkBidWeight, Long mobileNetworkBidWeight) throws SignatureException, ParameterException, NapiException {
        log.debug("그룹 PC, Mobile 플랫폼 입찰가 가중치 수정 요청");
        validateNetworkBidWeight(pcNetworkBidWeight, mobileNetworkBidWeight);
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("pcNetworkBidWeight", pcNetworkBidWeight);
        params.put("mobileNetworkBidWeight", mobileNetworkBidWeight);
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "networkBidWeight").body(params).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 하루예산 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param dailyBudget 수정할 하루예산
     * @return 수정된 그룹
     * @throws exception.ParameterException 유효하지 않은 파라미터가 존재할 경우 발생
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     */
    public Adgroup updateAdGroupDailyBudget(RestClient restClient, String adGroupId, Long dailyBudget) throws SignatureException, ParameterException, NapiException {
        log.debug("그룹 하루 예산 수정 요청");
        if (adGroupId == null || dailyBudget == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        log.debug("그룹 ID로 부터 유형 조회 API 요청");
        AdGroupTypes adGroupTp = getAdGroup(restClient, adGroupId).getAdgroupType();
        validateAdGroupDailyBudget(adGroupTp, dailyBudget);
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("dailyBudget", dailyBudget);
        Boolean useDailyBudget = true;
        if (dailyBudget == 0) {
            useDailyBudget = false;
        }
        params.put("useDailyBudget", useDailyBudget);
        log.debug("그룹 하루 예산 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "budget").body(params).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 타겟 요일-시간 정보 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param target 수정할 타겟 요일-시간
     * @return 수정된 그룹
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * Map<String, Object> schedule = Schedules.builder().friday(18, 19, 20).saturday(18, 19, 20).build();
     * Target target = Target.builder()
     *                 .nccTargetId("tgt-a001-01-000000230145652")
     *                 .ownerId("grp-a001-01-000000021721754")
     *                 .targetTp("TIME_WEEKLY_TARGET")
     *                 .target(schedule)
     *                 .build();
     *
     * Adgroup result = adGroupManagement.updateAdGroupTargetTimeWeek(restClient, adGroupId, target);
     * List<Map<String, Object>> targets = (List<Map<String, Object>>) result.getTargets();
     *
     * // 수정한 타겟 확인 방법
     * for(Map t : targets){
     *     if(t.containsKey("targetTp")){
     *          if(t.get("targetTp") == "TIME_WEEKLY_TARGET"){
     *              Target x = (Target) t;
     *           }
     *     }
     * }
     * }
     * </pre>
     */
    public Adgroup updateAdGroupTargetTimeWeek(RestClient restClient, String adGroupId, Target target) throws SignatureException, NapiException, ParameterException {
        log.debug("그룹 타겟 요일-시간 정보 수정 요청");
        if (adGroupId == null || target == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        List<Target> targetList = new ArrayList<Target>();
        targetList.add(target);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("targets", targetList);
        System.out.println(map.toString());
        log.debug("그룹 타겟 요일-시간 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "targetTime").body(map).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 타겟 플랫폼 정보 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param target 수정할 타겟 플랫폼
     * @return 수정된 그룹
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     *
     * <br>
     * <p><em>Example</em></p>
     * <pre>
     * {@code
     * String adGroupId = "grp-a001-01-000000021721754";
     * Map<String, Object> map = new HashMap<String,Object>();
     * map.put("pc", true);
     * map.put("mobile", true);
     * Target target = Target.builder()
     *                 .nccTargetId("tgt-a001-01-000000230145655")
     *                 .ownerId("grp-a001-01-000000021721754")
     *                 .targetTp("PC_MOBILE_TARGET")
     *                 .target(map)
     *                 .build();
     *
     * Adgroup result = adGroupManagement.updateAdGroupTargetPcMobile(restClient, adGroupId, target);
     * }
     * </pre>
     */
    public Adgroup updateAdGroupTargetPcMobile(RestClient restClient, String adGroupId, Target target) throws SignatureException, NapiException, ParameterException {
        log.debug("그룹 타겟 플랫폼 정보 수정 요청");
        if (adGroupId == null || target == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        List<Target> targetList = new ArrayList<Target>();
        targetList.add(target);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("targets", targetList);
        log.debug("그룹 타겟 플랫폼 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "targetTime").body(map).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 활성화 여부 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID
     * @param userLock 그룹 활성화 여부
     * @return 수정된 그룹
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Adgroup updateAdGroupUserLock(RestClient restClient, String adGroupId, Boolean userLock) throws SignatureException, NapiException, ParameterException {
        log.debug("그룹 활성화 여부 정보 수정 요청");
        if (adGroupId == null || userLock == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("userLock", userLock);
        log.debug("그룹 활성화 여부 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "userLock").body(params).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 소재노출방식 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID 정보
     * @param adRollingType 소재 노출방식 정보
     * @return 수정된 그룹 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Adgroup updateAdGroupRollingType(RestClient restClient, String adGroupId, AdRollingTypes adRollingType) throws SignatureException, NapiException, ParameterException {
        log.debug("그룹 소재노출방식 정보 수정 요청");
        if (adGroupId == null || adRollingType == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("adRollingType", adRollingType);
        log.debug("그룹 소재노출방식 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "adRollingType").body(map).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 콘텐츠 매체 입찰가 수정 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID 정보
     * @param useCntsNetworkBidAmt 콘텐츠 매체 입찰가 사용 여부 정보
     * @param contentsNetworkBidAmt 콘텐츠 매체 입찰가
     * @return 수정된 그룹 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Adgroup updateAdGroupContentsNetworkBidAmt(RestClient restClient, String adGroupId, Boolean useCntsNetworkBidAmt, Long contentsNetworkBidAmt) throws SignatureException, NapiException, ParameterException {
        log.debug("그룹 콘텐츠 매체 입찰가 정보 수정 요청");
        if (adGroupId == null || useCntsNetworkBidAmt == null || contentsNetworkBidAmt == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        validateContentsNetworkBidAmt(contentsNetworkBidAmt);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contentsNetworkBidAmt", contentsNetworkBidAmt);
        map.put("useCntsNetworkBidAmt", false);
        log.debug("그룹 콘텐츠 매체 입찰가 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "contentsNetwork").body(map).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 이름 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID 정보
     * @param name 그룹 이름 정보
     * @return 수정된 그룹 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    public Adgroup updateAdGroupName(RestClient restClient, @NonNull String adGroupId, @NonNull String name) throws SignatureException, ParameterException, NapiException {
        if (adGroupId == null) {
            throw new NullPointerException("adGroupId is marked non-null but is null");
        }
        if (name == null) {
            throw new NullPointerException("name is marked non-null but is null");
        }
        log.debug("그룹 이름 정보 수정 요청");
        if (adGroupId == null || name == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        validateAdGroupName(name);
        Map<String, Object> params = new HashMap<>();
        params.put("nccAdGroupId", adGroupId);
        params.put("name", name);
        log.debug("그룹 이름 정보 수정 API 요청");
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).queryString("fields", "name").body(params).asObject(Adgroup.class);
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 입찰가, PC-Mobile 플랫폼 입찰 가중치, 하루예산, 타겟정보, 소재 노출방식, 콘텐츠 매체 입찰가, 이름 수정 (활성화 여부는 불가)
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroup 수정할 그룹 정보
     * @return 수정된 그룹
     * @throws SignatureException 인증 예외
     * @throws NapiException 네이버 API 예외
     */
    public Adgroup updateAdGroup(RestClient restClient, Adgroup adGroup) throws SignatureException, NapiException {
        String adGroupId = adGroup.getNccAdgroupId();
        HttpResponse<Adgroup> r = restClient.put("/ncc/adgroups/" + adGroupId, AppSetting.customerId).body(adGroup).asObject(Adgroup.class);
        // origin.getTargets().get()
        return (Adgroup) getResultFromRes(r, "group");
    }

    /**
     * 그룹 삭제 메소드
     * @param restClient 통신 클라이언트 RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey)
     * @param adGroupId 그룹 ID 정보
     * @return 삭제된 그룹 ID 정보
     * @throws exception.NapiException 네이버 검색광고 라이브러리 요청 중 문제 시 발생
     * @throws SignatureException 인증 예외
     * @throws ParameterException 파라리터 유효성 예외
     */
    public String deleteAdGroup(RestClient restClient, @NonNull String adGroupId) throws SignatureException, NapiException, ParameterException {
        if (adGroupId == null) {
            throw new ParameterException(ParameterException.NULL_PARAMETER_EXCEPTION);
        }
        HttpResponse<Adgroup> r = restClient.delete("/ncc/adgroups/" + adGroupId, AppSetting.customerId).asObject(Adgroup.class);
        getResultFromRes(r, "group");
        return adGroupId;
    }
}
