package operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.NapiException;
import exception.ParameterException;
import lombok.extern.slf4j.Slf4j;
import model.AdKeyword;
import model.Link;
import org.junit.Test;
import util.AppSetting;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static util.AppSetting.restClient;
import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
public class AdKeywordManagementTest {

    AdKeywordManagement adKeywordManagement = new AdKeywordManagement();
    //RestClient restClient = RestClient.of("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==");

    public AdKeywordManagementTest() throws GeneralSecurityException {
    }

    /**
     *  그룹 ID로 키워드 목록 조회 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAdKeywordByAdGroupId() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021714177";
        List<AdKeyword> result = adKeywordManagement.getAdKeywordByAdGroupId(restClient, adGroupId);
        assertThat(result.size()).isEqualTo(0);
    }

    /**
     * 키워드 ID로 단일 키워드 조회 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAdKeyword() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adKeywordId = "nkw-a001-01-000003752320246";
        AdKeyword result = adKeywordManagement.getAdKeyword(restClient, adKeywordId);
        assertThat(result.getKeyword()).isEqualTo("하이");
    }

    /**
     * 키워드 ID 목록으로 키워드 목록 조회 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testGetAdKeywordList() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> adKeywordIdList = new ArrayList<String>();
        adKeywordIdList.add("nkw-a001-01-000003752320249");
        adKeywordIdList.add("nkw-a001-01-000003752320246");
        List<AdKeyword> result = adKeywordManagement.getAdKeywordList(restClient, adKeywordIdList);
        log.debug(result.get(0).getKeyword() +","+result.get(0).getNccKeywordId());
        assertThat(result.size()).isEqualTo(0);

    }

    /**
     * 키워드 등록 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testCreateKeyword() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<AdKeyword> adKeywordIdList = new ArrayList<AdKeyword>();

        // PC, MOBILE 둘 다 넣어줘야 한다.
        Link link = new Link("https://www.naver.com", "https://www.naver.com");
        // ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(link , Map.class);
        AdKeyword k1 = AdKeyword.builder().keyword("카페").links(map).build();
        AdKeyword k2 = AdKeyword.builder().keyword("모카").bidAmt(100L).useGroupBidAmt(false).build();
        AdKeyword k3 = AdKeyword.builder().keyword("라떼").bidAmt(100L).build();
        AdKeyword k4 = AdKeyword.builder().keyword("카공").bidAmt(0L).useGroupBidAmt(true).build(); //0이면 그룹 입찰가 사용
        adKeywordIdList.add(k1);
        adKeywordIdList.add(k2);
        adKeywordIdList.add(k3);
        adKeywordIdList.add(k4);
        String adGroupId = "grp-a001-01-000000021714177";
        List<AdKeyword> result = adKeywordManagement.createAdKeywordList(restClient, adGroupId, adKeywordIdList);
        assertThat(result.get(0).getNccKeywordId()).isNotEmpty();
    }

    /**
     * 키워드 입찰가 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateKeywordBidAmt() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021714177";
        String adKeywordId = "nkw-a001-01-000003752320249";
        Long bidAmt = 3560L;
        AdKeyword result =adKeywordManagement.updateKeywordBidAmt(restClient, adGroupId, adKeywordId, bidAmt);
        assertThat(result.getBidAmt()).isEqualTo(3560L);

    }

    /**
     * 키워드 활성화 여부 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testUpdateKeywordUserLock() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021714177";
        String adKeywordId = "nkw-a001-01-000003752320249";
        Boolean userLock = true;
        AdKeyword result = adKeywordManagement.updateKeywordUserLock(restClient, adGroupId, adKeywordId, userLock);
        assertThat(result.getUserLock()).isEqualTo(userLock);
    }

    /**
     * 키워드 랜딩(연결) URL 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateKeywordLinkk() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adGroupId = "grp-a001-01-000000021714177";
        String adKeywordId = "nkw-a001-01-000003752320249";
        Link link = new Link("http://www.tistory.com","http://www.tis3y2j.com");
        AdKeyword result = adKeywordManagement.updateKeywordLink(restClient, adGroupId, adKeywordId, link);

        // ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        Map temp = objectMapper.convertValue(link, Map.class);

        assertThat(result.getLinks()).isEqualTo(temp);
    }

    /**
     * 키워드 목록 개별 입찰가 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateKeywordListBidAmt() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);


        List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();
        AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").useGroupBidAmt(false).bidAmt(380L).build();
        AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").useGroupBidAmt(false).bidAmt(180L).build();
        adKeywordList.add(adk1);
        adKeywordList.add(adk2);
        List<AdKeyword> result = adKeywordManagement.updateKeywordListBidAmt(restClient, adKeywordList);
        assertThat(result.get(0).getBidAmt()).isEqualTo(180L);

    }


    /**
     * 키워드 목록 개별 활성화 여부 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testUpdateKeywordListUserLock() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);


        List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();
        AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").userLock(false).build();
        AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").userLock(true).build();
        adKeywordList.add(adk1);
        adKeywordList.add(adk2);
        List<AdKeyword> result = adKeywordManagement.updateKeywordListUserLock(restClient, adKeywordList);
        assertThat(result.get(0).getUserLock()).isEqualTo(false);

    }

    /**
     * 키워드 목록 개별 랜딩(연결) URL 수정 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     * @throws ParameterException 파라미터 유효성 예외
     */
    @Test
    public void testUpdateKeywordListLink() throws GeneralSecurityException, ParameterException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);


        List<AdKeyword> adKeywordList = new ArrayList<AdKeyword>();

        Link link = new Link("https://www.naver.com", "https://www.naver.com");

        // ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(link , Map.class);

        AdKeyword adk1 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320249").nccAdgroupId("grp-a001-01-000000021714177").links(map).build();
        AdKeyword adk2 = AdKeyword.builder().nccKeywordId("nkw-a001-01-000003752320246").nccAdgroupId("grp-a001-01-000000021714177").links(map).build();
        adKeywordList.add(adk1);
        adKeywordList.add(adk2);
        List<AdKeyword> result = adKeywordManagement.updateKeywordListLink(restClient, adKeywordList);

        // ObjectMapper
        Map temp = objectMapper.convertValue(link, Map.class);

        assertThat(result.get(0).getLinks()).isEqualTo(temp);
    }


    /**
     * 단일 키워드 삭제 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testDeleteAdKeyword() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        String adKeywordId = "nkw-a001-01-000003752321227";
        String result = adKeywordManagement.deleteAdKeyword(restClient, adKeywordId);
        assertThat(result).isEqualTo(adKeywordId);
    }

    /**
     * 키워드 ID 목록에 해당하는 키워드 목록 삭제 테스트
     * @throws GeneralSecurityException 인증 예외
     * @throws NapiException 네이버 api 예외
     */
    @Test
    public void testDeleteAdKeywordList() throws GeneralSecurityException, NapiException {
        AppSetting.initAuthInfo("https://api.naver.com", "0100000000b2d3d0aeca17715016943fdf868637dafaa6fe9d14ef36df7c51f9183304d698", "AQAAAACy09CuyhdxUBaUP9+GhjfaJFmCMgIDTkaKhX5s78EkEg==",2248092L);

        List<String> adKeywordIdList = new ArrayList<String>();
        adKeywordIdList.add("nkw-a001-01-000003752330618");
        adKeywordIdList.add("nkw-a001-01-000003752330679");
        List<String> result = adKeywordManagement.deleteAdKeywordList(restClient, adKeywordIdList);
        // assertThat(result).isEqualTo(adKeywordIdList);

    }


}
