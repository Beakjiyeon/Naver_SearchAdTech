package operation;

import exception.*;
import junit.framework.TestCase;
import model.*;
import org.junit.Test;
import util.*;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static common.CampaignTypes.*;
import static common.DeliveryMethodTypes.*;
import static common.TrackingModeTypes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static util.AppSetting.restClient;
import static util.DateFormat.getUTCDate;

/**
 * CampaignManagements 메소드 테스트 클래스
 */
public class CampaignManagementTest extends TestCase {

    CampaignManagement campaignMangement = new CampaignManagement();
    public CampaignManagementTest() throws GeneralSecurityException {
    }

    /**
     * 캠페인 등록 테스트
     * @throws Exception 예외
     */
    @Test
    public void testCreateCampaign() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);
        Campaign campaignWeb = Campaign.builder()
                .name("캠페인 등록2")
                .campaignTp(WEB_SITE)
                .customerId(AppSetting.customerId)
                .dailyBudget(0L)
                .deliveryMethod(STANDARD)
                .build();

        Campaign campaignShop = Campaign.builder()
                .name("쇼핑 캠페인 등록2")
                .campaignTp(SHOPPING)
                .customerId(AppSetting.customerId)
                // .dailyBudget(0L)
                .deliveryMethod(STANDARD)
                .build();

        Campaign campaignDailyBudget = Campaign.builder()
                .name("예산 테스트")
                .campaignTp(SHOPPING)
                .customerId(AppSetting.customerId)
                .useDailyBudget(true)
                // 하루예산 설정 시, useDailyBudget 필드를 설정할 수 없습니다.
                // 하루예산 '제한없음'으로 설정을 원하는 경우 dailyBudget = 0L 로 입력하세요.
                .deliveryMethod(STANDARD)
                .build();

        Campaign result = campaignMangement.createCampaign(restClient, campaignWeb);
        assertThat(result.getNccCampaignId()).isNotEmpty();
    }

    /**
     * 단일 캠페인 ID로 캠페인 조회 테스트
     * @throws Exception 예외
     */
    @Test
    public void testGetCampaign() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);
        String campaignId = "cmp-a001-01-000000004213399"; // null : 캠페인 ID 가 누락되었습니다.
        Campaign result = campaignMangement.getCampaign(restClient,campaignId);
        assertThat(result.getNccCampaignId()).isEqualTo(campaignId);
    }

    /**
     * 전체 캠페인 목록 조회 테스트
     * @throws Exception 예외
     */
    @Test
    public void testGetCampaignList() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);
        List<Campaign> result = campaignMangement.getCampaignList(restClient);
        assertThat(result.size()).isEqualTo(0);
    }

    /**
     * 캠페인 ID 목록에 해당하는 캠페인 목록 조회 테스트
     * @throws Exception 예외
     */
    @Test
    public void testGetCampaignListByIds() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> campaignIdList = new ArrayList<>();
        campaignIdList.add("cmp-a001-01-000000004213402");
        campaignIdList.add("cmp-a001-01-000000004213407");
        campaignIdList.add(null); // 캠페인 ID 가 누락되었습니다.
        List<Campaign> result = campaignMangement.getCampaignListByIds(restClient, campaignIdList);
        assertThat(result.size()).isEqualTo(0); // 3
    }

    /**
     * 특정 유형에 해당하는 캠페인 목록 조회 테스트
     * @throws Exception 예외
     */
    @Test
    public void testGetCampaignListByType() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<Campaign> result = campaignMangement.getCampaignListByType(restClient, SHOPPING); // null 캠페인 유형 정보가 누락되었습니다 .
        assertThat(result.size()).isEqualTo(0);
    }

    /**
     * 캠페인 하루 예산 수정 메소드
     * @throws Exception 예외
     */
    @Test
    public void testUpdateCampaignBudget() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        Budget budget = Budget.builder()
                .nccCampaignId("cmp-a001-01-000000004213399")
                .userLock(false)
                .dailyBudget(0L)
                // .useDailyBudget(false)
                // 하루예산 설정 시, useDailyBudget 필드를 설정할 수 없습니다.
                // 하루예산 '제한없음'으로 설정을 원하는 경우 dailyBudget=0L 로 입력하세요.
                .build();
        Campaign result = campaignMangement.updateCampaignBudget(restClient, budget);
        assertThat(result.getDailyBudget()).isEqualTo(300L);
    }

    /**
     * 캠페인 노출기간 수정 메소드
     * @throws ParameterException 파라미터 예외
     * @throws NapiException 네이버 API 예외
     * @throws GeneralSecurityException 인증 예외
     * @throws ParseException 날짜 포맷팅 예외
     */
    @Test
    public void testUpdateCampaignPeriod() throws ParameterException, NapiException, GeneralSecurityException, ParseException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        int day = 10;
        Period period = Period.builder()
                .nccCampaignId("cmp-a001-01-000000004213399")
                //.usePeriod(true)
                .periodStartDt(getUTCDate(2021,5, day))
                .periodEndDt(getUTCDate(2021,9,3))
                .userLock(false)
                .build();
        Campaign result = campaignMangement.updateCampaignPeriod(restClient, period);
        assertThat(result.getUsePeriod()).isEqualTo(true);
        // assertThat(result.getPeriodStartDt()).isEqualTo(getUTCDate(2021,5,day-1));
        // Timezone 추후 처리 필요 Expected :"2021-05-09T05:59:55.248Z" / Actual :"2021-05-09T15:00:00.000Z"
    }

    /**
     * 캠페인 하루예산, 노출기간, 이름, 추적모드, 예산소진전략 중에서 원하는 필드를 수정 메소드
     * @throws Exception 예외
     */
    // 캠페인 이름, 추적유형, 예산 소진 전략 수정
    @Test
    public void testUpdateCampaign() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        Campaign c = Campaign.builder()
                // 필수
                .nccCampaignId("cmp-a001-01-000000004213399")
                .userLock(true)
                // 선택
                .name("이름 수정합니다.")
                .periodStartDt(getUTCDate(2021,3,7)) //dateFormat.format(new Date())
                .periodEndDt(getUTCDate(2021,9,3))
                .customerId(2248092L)
                .dailyBudget(0L)
                .deliveryMethod(ACCELERATED)
                .trackingMode(AUTO_TRACKING_MODE)
                .build();
        Campaign result = campaignMangement.updateCampaign(restClient, c);
        assertThat(result.getName()).isEqualTo("이름 수정합니다.");
    }

    /**
     * 캠페인 중지 테스트
     * @throws ParameterException 파라미터 예외
     * @throws NapiException 네이버 API 예외
     * @throws GeneralSecurityException 인증 예외
     */
    @Test
    public void testUpdateCampaignUserLock() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        UserLock userLock = UserLock.builder()
                .nccCampaignId("cmp-a001-01-000000004213399")
                .userLock(true)
                .build();
        Campaign result = campaignMangement.updateCampaignUserLock(restClient, userLock);
        assertThat(result.getUserLock()).isEqualTo(true);
    }
}