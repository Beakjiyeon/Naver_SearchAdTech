package util;

import lombok.extern.slf4j.Slf4j;

import java.security.GeneralSecurityException;

/**
 * 유저 인증 정보, 도메인 상수
 * */
@Slf4j
public class AppSetting {
    private static String baseUrl;
    private static String apiKey;
    private static String secretKey;
    public static Long customerId;
    public static RestClient restClient;


    /**
     *유저 인증 정보 초기화 메소드
     * @param baseUrlInfo baseUrl 정보
     * @param apiKeyInfo 네이버 apiKey 정보
     * @param secretKeyInfo 네이버 secretKey 정보
     * @param customerIdInfo 고객 ID 정보
     * @throws GeneralSecurityException 인증 처리 과정에서의 예외
     */
    public static void initAuthInfo(String baseUrlInfo, String apiKeyInfo, String secretKeyInfo, Long customerIdInfo) throws GeneralSecurityException {
        log.debug("유저 인증 정보 초기화");
        baseUrl = baseUrlInfo;
        apiKey = apiKeyInfo;
        secretKey = secretKeyInfo;
        customerId = customerIdInfo;
        restClient = RestClient.of(AppSetting.baseUrl, AppSetting.apiKey, AppSetting.secretKey);
    }

    /**
     * 유저 인증 정보 삭제 메소드
     */
    public static void deleteAuthInfo() {
        log.debug("유저 인증 정보 삭제");
        baseUrl = null;
        apiKey = null;
        secretKey = null;
        customerId = null;
        restClient = null;
    }


    public static Long MIN_POWER_LINK_DAILY_BUDGET = 70L;
    public static Long MAX_POWER_LINK_DAILY_BUDGET = 1000000000L;

    public static Long MIN_SHOPPING_DAILY_BUDGET = 50L;
    public static Long MAX_SHOPPING_DAILY_BUDGET = 1000000000L;

    public static Long MIN_GROUP_BIDAMT = 70L;
    public static Long MAX_GROUP_BIDAMT = 100000L;

    public static Long MIN_CONTENTS_NETWORK_BIDAMT = 70L;
    public static Long MAX_CONTENTS_NETWORK_BIDAMT = 100000L;

    public static Long MIN_SHOPPING_AD_BIDAMT = 50L;
    public static Long MAX_SHOPPING_AD_BIDAMT = 100000L;


    public static int MIN_CAMPAIGN_NAME_LENGTH = 1;
    public static int MAX_CAMPAIGN_NAME_LENGTH = 30;

    public static int MIN_GROUP_NAME_LENGTH = 1;
    public static int MAX_GROUP_NAME_LENGTH = 30;

    public static int MIN_KEYWORD_NAME_LENGTH = 1;
    public static int MAX_KEYWORD_NAME_LENGTH = 25;

    public static int MIN_HEADLINE_LENGTH = 1;
    public static int MAX_HEADLINE_LENGTH = 15;

    public static int MIN_DESCRIPTION_LENGTH = 20;
    public static int MAX_DESCRIPTION_LENGTH = 45;

    public static int MIN_NETWORK_BID_WEIGHT = 10;
    public static int MAX_NETWORK_BID_WEIGHT = 500;
}
