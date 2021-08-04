package operation;

import common.AdGroupTypes;
import common.AdTypes;
import exception.NapiException;
import exception.ParameterException;
import lombok.extern.slf4j.Slf4j;
import model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import util.*;
import java.security.GeneralSecurityException;
import java.util.*;

import static common.AdTypes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static util.AppSetting.restClient;

@Slf4j
public class AdManagementTest {

    AdManagement adManagement = new AdManagement();

    //RestClient restClient = RestClient.of("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==");

    public AdManagementTest() throws GeneralSecurityException {
    }

    /**
     * 파워링크 소재 등록 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testcreatePowerLinkAd() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        /**
         * 필수 정보 : PC 랜딩 URL, Mobile 랜딩 URL, 그룹 ID, 소재 유형
         * 옵션 정보 : 검수 요청 메세지
         */
        String pcUrl = "https://www.naver.com";
        String mobileUrl = "https://www.naver.com";
        String headline = "헤드라인3";
        String description = "디베이스의 모든 것, 디베이스앤 공식 온라인스토어";
        TextNdescription textNdescription = new TextNdescription(pcUrl, mobileUrl, headline, description);

        PowerLinkAd powerLinkAd = new PowerLinkAd("grp-a001-01-000000021714177",TEXT_45,textNdescription);
        powerLinkAd.setInspectRequestMsg("검수요청 메세지");
        PowerLinkAd result = adManagement.createPowerLinkAd(restClient, powerLinkAd);
        // System.out.println(result.getAd().toString());
        assertThat(result.getNccAdId()).isNotEmpty();
    }

    /**
     * 쇼핑검색 소재 등록 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testCreateShoppingAd() throws Exception {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        /**
         * 필수 정보 : 그룹 ID, 소재 유형, 고객 ID, 상품 참조키, 그룹입찰가 사용여부, 개별입찰가
         */

        List<Ad> adList = new ArrayList<Ad>();

        Ad shoppingAd= new ShoppingAd("grp-a001-02-000000021722250", CATALOG_AD, AppSetting.customerId,"27436193522", false, 90L, "MAKER");
        /**
        일단 입찰가, 그룹사용여부 모두 입력하게 하도록 해놨음.
        전에 그룹입찰가만 true 로 하고 보내도 되지 않을까 고민이 되었음.
        그래서 테스트 결과 둘다 API에 입력해야함.
        하지만 사용자가 0을 입력했을 때, Map 에 있는 입찰가 키 자체를 삭제하거나, 그대로 0으로 보낼 수 없다.
        따라서 유효한 범위의 입찰가, 사용여부를 넣어준다.
         */
        adList.add(shoppingAd);
        List<ShoppingAd> result = adManagement.createShoppingAd(restClient, adList);
        // System.out.println(result.get(0).getAd());
        assertThat(result.get(0).getNccAdId()).isNotEmpty();
    }

    /**
     * 단일 소재 조회 메소드
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAd() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adId = "nad-a001-02-000000137500566";
        AdTypes adType = CATALOG_AD;
        if(adType == CATALOG_AD){
            ShoppingAd result = (ShoppingAd) adManagement.getAd(restClient, adId ,adType);
            System.out.println(result.getAd());
            assertThat(result.getNccAdId()).isEqualTo(adId);
        }else{
            PowerLinkAd result = (PowerLinkAd) adManagement.getAd(restClient, adId ,adType);
            System.out.println(result.getAd());
            assertThat(result.getNccAdId()).isEqualTo(adId);
        }

    }

    /**
     * 단일 소재 조회 메소드2
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAd2() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adId = "nad-a001-02-000000137500566";
        AdTypes adType = CATALOG_AD;
        AdDto result = (AdDto) adManagement.getAd(restClient, adId);
        System.out.println(result.getNccAdId() + "," + result.getType());
    }
    /**
     * 쇼핑 그룹 ID 에 속한 소재 조회 메소드
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAdByShoppingAdGroupId() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-02-000000021722250";
        List<ShoppingAd> result = adManagement.getAdByAdShoppingGroupId(restClient, adGroupId);
//        System.out.println(result.size());
//        System.out.println(result.get(0).getAd());
        assertThat(result.get(0).getAd()).isNotEmpty();

    }

    /**
     * 파워링크 그룹 ID 에 속한 소재 조회 메소드
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     *
     */
    @Test
    public void testGetAdByPowerLinkAdGroupId() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021714177";
        List<PowerLinkAd> result = adManagement.getAdByAdPowerLinkGroupId(restClient, adGroupId);
//        System.out.println(result.size());
//        System.out.println(result.get(0).getAd());
        assertThat(result.get(0).getAd()).isNotEmpty();
    }

    /**
     * 소재 ID에 해당하는 소재 목록 조회 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAdList() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> adIdList = new ArrayList<String>();

        adIdList.add("nad-a001-02-000000137514247");// 쇼핑
        adIdList.add("nad-a001-02-000000137516352");// 쇼핑
        adIdList.add("nad-a001-01-000000137499473");// 파워워
        List<AdDto> result = adManagement.getAdList(restClient, adIdList);
//        System.out.println(result.get(0).getAd());
//        System.out.println(result.get(1).getAd());
//        System.out.println(result.get(2).getAd());
        assertThat(result.size()).isEqualTo(3);

    }

    /**
     * 소재 활성화 여부 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdUserLock() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);


        // 파워링크 소재 테스트
        Ad ad1 = new Ad("nad-a001-01-000000137117540", true); // true == off

        // 쇼핑검색 소재 테스트
        Ad ad2 = new Ad("nad-a001-02-000000137500566",false);
        Ad result = adManagement.updateAdUserLock(restClient, ad2);
        assertThat(result.getUserLock()).isEqualTo(false);

    }

    /**
     * 소재 검토메세지 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateAdInspectRequestMsg() throws GeneralSecurityException, NapiException, ParameterException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);


        // 파워링크 소재 테스트
        Ad ad1 = new Ad("nad-a001-01-000000137117540", "검토요청합니다."); // true == off

        // 쇼핑검색 소재 테스트
        Ad ad2 = new Ad("nad-a001-02-000000137500566","검토요청합니다.");
        Ad result = adManagement.updateAdInspectRequestMsg(restClient, ad2);
        assertThat(result.getInspectRequestMsg()).isEqualTo("검토요청합니다");
    }

    /**
     * 소재 삭제 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testDeleteAd() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adId = "nad-a001-01-000000137500000";
        String result = adManagement.deleteAd(restClient, adId);
        log.debug(result);
    }

    /**
     * 소재 복사 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testCopyAd() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> adIdList = new ArrayList<String>();
        adIdList.add("nad-a001-02-000000137513541");
        String targetAdGroupId = "grp-a001-02-000000021725694";
        Boolean userLock = true; // TRUE :OFF
        List<Ad> result = adManagement.copyAd(restClient, adIdList, targetAdGroupId, userLock);
        assertThat(result.size()).isEqualTo(0);
    }

}
