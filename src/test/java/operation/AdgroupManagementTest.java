package operation;

import exception.NapiException;
import exception.ParameterException;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import model.Adgroup;
import model.Target;
import org.junit.Test;

import util.AppSetting;
import util.Schedules;

import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AdGroupTypes.CATALOG;
import static common.AdGroupTypes.WEB_SITE;
import static common.AdRollingTypes.PERFORMANCE;
import static org.assertj.core.api.Assertions.assertThat;
import static util.AppSetting.restClient;

/**
 * AdGroupManagement 메소드 테스트 클래스
 */
@Slf4j
public class AdgroupManagementTest extends TestCase {

    AdGroupManagement adGroupManagement = new AdGroupManagement();
    public AdgroupManagementTest() throws GeneralSecurityException {
    }
    // RestClient restClient = RestClient.of("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==");

    /**
     * 그룹 등록 테스트
     * @throws Exception 예외
     */
    @Test
    public void testCreateAdGroup() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<Target> targets = new ArrayList<>();
        Target mobilePctarget = new Target();
        // PC_MOBILE_TARGET

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("pc", true);
        map.put("mobile", false);

        mobilePctarget.setTarget(map);
        mobilePctarget.setTargetTp("PC_MOBILE_TARGET");
        targets.add(mobilePctarget);


        Target timeWeekyTarget = new Target();
        // TIME_WEEKLY_TARGET
        Map<String, Object> schedule = Schedules.builder().friday(18, 19, 20).saturday(18, 19, 20).build();

        timeWeekyTarget.setTarget(schedule);
        timeWeekyTarget.setTargetTp("TIME_WEEKLY_TARGET");
        targets.add(timeWeekyTarget);
        System.out.println(targets.toString());

        Adgroup adgroupShopping = Adgroup.builder()
                .name("쇼핑쇼핑1")
                .adgroupType(CATALOG)
                .nccCampaignId("cmp-a001-02-000000004213611")
                .mobileChannelId("bsn-a001-00-000000005273485")
                .pcChannelId("bsn-a001-00-000000005273485")
                //.bidAmt(70L)
                .contentsNetworkBidAmt(120L)
                .useCntsNetworkBidAmt(false)
                .dailyBudget(0L)
                .build();

        Adgroup adgroupPower = Adgroup.builder()
                .name("1234파워파워")
                .adgroupType(WEB_SITE)
                .nccCampaignId("cmp-a001-01-000000004215330")
                .mobileChannelId("bsn-a001-00-000000005255878")
                .pcChannelId("bsn-a001-00-000000005255878")
                .bidAmt(80L)
                .contentsNetworkBidAmt(120L)
                .useCntsNetworkBidAmt(false)
                .dailyBudget(0L)
                .targets(targets)
                .build();

        Adgroup result = adGroupManagement.createAdGroup(restClient, adgroupPower);
        assertThat(result.getNccAdgroupId()).isNotEmpty();
    }

    /**
     * 단일 그룹 조회 테스트
     * @throws GeneralSecurityException 인증예외
     * @throws NapiException 네이버 API 예외
     */
    @Test
    public void testGetAdGroup() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.getAdGroup(restClient, adGroupId);
        assertThat(result.getNccAdgroupId()).isNotEmpty();
    }

    /**
     * 특정 캠페인에 속한 그룹 목록 조회 테스트
     * @throws GeneralSecurityException 인증예외
     * @throws NapiException 네이버 API 예외
     */
    @Test
    public void testGetAdGroupListByCampaignId() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String campaignId = "cmp-a001-02-000000004213611";//cmp-a001-01-000000004215330
        List<Adgroup> result = adGroupManagement.getAdGroupListByCampaignId(restClient, campaignId);
        // assertThat(result.size()).isEqualTo(0);
        assertThat(result.get(0).getNccAdgroupId()).isNotEmpty();
    }

    /**
     * 그룹 ID 들에 해당하는 그룹 목록 조회 테스트
     * @throws GeneralSecurityException 인증예외
     * @throws NapiException 네이버 API 예외
     */
    public void testGetAdGroupList() throws NapiException, GeneralSecurityException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> adGroupIdList = new ArrayList<String>();
//        adGroupIdList.add("grp-a001-01-000000021721754");
//        adGroupIdList.add("grp-a001-01-000000021721725");
//        List<Adgroup> result = adGroupManagement.getAdGroupList(restClient, adGroupIdList);
//        assertThat(result.get(0).getNccAdgroupId()).isEqualTo("grp-a001-01-000000021721754");
        adGroupIdList.add("grp-a001-02-000000021721722");
        adGroupIdList.add("grp-a001-02-000000021722250");
        List<Adgroup> result = adGroupManagement.getAdGroupList(restClient, adGroupIdList);
        assertThat(result.get(0).getNccAdgroupId()).isEqualTo("grp-a001-02-000000021722250");
    }

    /**
     * 그룹 입찰가 수정 테스트
     * @throws GeneralSecurityException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    public void testUpdateAdGroupBidAmt() throws NapiException, ParameterException, GeneralSecurityException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.updateAdGroupBidAmt(restClient, adGroupId, 500L);
        assertThat(result.getBidAmt()).isEqualTo(500L);
    }

    /** 그룹 PC 플랫폼 입찰가 가중치 수정 테스트
     *
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupPcNetworkBidWeight() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.updateAdGroupNetworkBidWeight(restClient, adGroupId, 200L, 100L);
        assertThat(result.getPcNetworkBidWeight()).isEqualTo(200L);
    }

    /**
     * 그룹 하루 예산 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupDailyBudget() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.updateAdGroupDailyBudget(restClient, adGroupId, 60L);
        assertThat(result.getDailyBudget()).isEqualTo(0L);
    }

    /**
     * 그룹 타겟 [요일-시간] 정보 수정
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws GeneralSecurityException 인증 예외
     * @throws ParameterException 파라미터 유효성 검사 예외
     */
    @Test
    public void testUpdateAdTargetTimeWeek() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754

        Map<String, Object> schedule = Schedules.builder().friday(18, 19, 20).saturday(18, 19, 20).build();
        Target target = Target.builder()
                .nccTargetId("tgt-a001-02-000000230144620")
                .ownerId("grp-a001-02-000000021721722")
                .targetTp("TIME_WEEKLY_TARGET")
                .target(schedule)
                .build();

        Adgroup result = adGroupManagement.updateAdGroupTargetTimeWeek(restClient, adGroupId, target);
        List<Map<String, Object>> targets = (List<Map<String, Object>>) result.getTargets();
        for(Map t : targets){
            if(t.containsKey("targetTp")){
                if(t.get("targetTp") == "TIME_WEEKLY_TARGET"){
                    Target x = (Target) t;
                    assertThat(x).isEqualTo(target);
                }
            }
        }

    }

    /**
     *  그룹 타겟 PC, Mobile 플랫폼 수정 테스트
     * @throws GeneralSecurityException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdTargetPcMobile() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("pc", true);
        map.put("mobile", false);
        Target target = Target.builder()
                .nccTargetId("tgt-a001-02-000000230144622")
                .ownerId("grp-a001-02-000000021721722")
                .targetTp("PC_MOBILE_TARGET")
                .target(map)
                .build();

        Adgroup result = adGroupManagement.updateAdGroupTargetPcMobile(restClient, adGroupId, target);
        List<Map<String, Object>> targets = (List<Map<String, Object>>) result.getTargets();
        for(Map t : targets){
            if(t.containsKey("targetTp")){
                if(t.get("targetTp") == "PC_MOBILE_TARGET"){
                    Target x = (Target) t;
                    assertThat(x).isEqualTo(target);
                }
            }
        }

    }

    /**
     * 그룹 활성화 여부 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupUserLock() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.updateAdGroupUserLock(restClient, adGroupId, true);
        assertThat(result.getUserLock()).isEqualTo(true);
    }

    /**
     * 그룹 소재 노출 방식 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupRollingType() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Adgroup result = adGroupManagement.updateAdGroupRollingType(restClient, adGroupId, PERFORMANCE);
        assertThat(result.getAdRollingType()).isEqualTo(PERFORMANCE);
    }

    /**
     * 그룹 콘텐츠 네트워크 입찰 가중치 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupContentsNetworkBidAmt() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722";//grp-a001-01-000000021721754
        Boolean useCntsNetworkBidAmt = false;
        Long contentsNetworkBidAmt = 350L;
        Adgroup result = adGroupManagement.updateAdGroupContentsNetworkBidAmt(restClient, adGroupId, useCntsNetworkBidAmt, contentsNetworkBidAmt);
        assertThat(result.getContentsNetworkBidAmt()).isEqualTo(350L);
    }

    /**
     * 그룹 이름 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdGroupName() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021721722"; //grp-a001-01-000000021721754
        Adgroup result =adGroupManagement.updateAdGroupName(restClient, adGroupId, "sleepy");
        assertThat(result.getName()).isEqualTo("sleepy");
    }

    /**
     * 그룹 삭제 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testDeleteAdGroupName() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021721725";
        String result = adGroupManagement.deleteAdGroup(restClient, adGroupId);
        log.debug(result);

    }

    /**
     * 그룹 전체 수정 테스트
     * @throws SignatureException 인증예외
     * @throws NapiException 네이버 API 예외
     *
     */
    public void testUpdateAdGroup() throws NapiException, GeneralSecurityException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<Target> targets = new ArrayList<>();
        Target mobilePctarget = new Target();
        // PC_MOBILE_TARGET

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("pc", true);
        map.put("mobile", false);

        mobilePctarget.setTarget(map);
        mobilePctarget.setTargetTp("PC_MOBILE_TARGET");
        targets.add(mobilePctarget);


        Target timeWeekyTarget = new Target();
        // TIME_WEEKLY_TARGET
        Map<String, Object> schedule = Schedules.builder().friday(18, 19, 20).saturday(18, 19, 20).build();

        timeWeekyTarget.setTarget(schedule);
        timeWeekyTarget.setTargetTp("TIME_WEEKLY_TARGET");
        targets.add(timeWeekyTarget);
        System.out.println(targets.toString());

        Adgroup adgroupPower = Adgroup.builder()
                .name("1234파워파워to피카")
                .nccAdgroupId("grp-a001-01-000000021726622")
                .bidAmt(80L)
                .contentsNetworkBidAmt(120L)
                .dailyBudget(100L)
                //.keywordPlusWeight()
                //.pcNetworkBidWeight()
                //.mobileNetworkBidWeight()
                .useCntsNetworkBidAmt(false)
                .useDailyBudget(true)
                //.useKeywordPlus()
                //.userLock()
                .targets(targets)
                .build();

        Adgroup result = adGroupManagement.updateAdGroup(restClient, adgroupPower);

    }

}