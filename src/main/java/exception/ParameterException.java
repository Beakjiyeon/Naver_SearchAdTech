package exception;

import lombok.extern.slf4j.Slf4j;

/**
 * 파라미터 예외로 발생하는 예외 상수 클래스
 * */
public class ParameterException extends  CommonException {

    public static final int PARAMETER_EXCEPTION = 700;

    // Null Paremeter
    public static final int NULL_PARAMETER_EXCEPTION = 710;
    public static final int NULL_NAME_PARAMETER_EXCEPTION = 711;
    public static final int NULL_CAMPAIGN_TP_PARAMETER_EXCEPTION = 712;
    public static final int NULL_CUSTOMER_ID_PARAMETER_EXCEPTION = 713;
    public static final int NULL_TRACKING_URL_PARAMETER_EXCEPTION = 714;
    public static final int NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION = 715;
    public static final int NULL_USERLOCK_PARAMETER_EXCEPTION = 716;
    public static final int NULL_ADGROUP_TYPE_PARAMETER_EXCEPTION = 717;
    public static final int NULL_PC_CHANNEL_ID_PARAMETER_EXCEPTION = 718;
    public static final int NULL_ADGROUP_ID_PARAMETER_EXCEPTION = 719;
    public static final int NULL_AD_TYPE_PARAMETER_EXCEPTION = 720;
    public static final int NULL_TND_PARAMETER_EXCEPTION = 721;
    public static final int NULL_SHOPPING_SEARCH_PARAMETER_EXCEPTION = 722;
    public static final int NULL_SHOPPING_AD_PARAMETER_EXCEPTION = 723;
    public static final int NULL_AD_ID_PARAMETER_EXCEPTION = 724;
    public static final int NULL_INSPECT_MSG_PARAMETER_EXCEPTION = 725;
    public static final int NULL_REFERENCE_KEY_PARAMETER_EXCEPTION = 726;
    public static final int NULL_END_DATE_PARAMETER_EXCEPTION = 727;
    public static final int NULL_USE_DAILY_BUDGET_PARAMETER_EXCEPTION = 728 ;
    public static final int NULL_DAILY_BUDGET_PARAMETER_EXCEPTION = 729;


    public static final int NULL_PC_NETWORK_BID_AMT_PARAMETER_EXCEPTION = 600;
    public static final int NULL_MOBILE_NETWORK_BID_AMT_PARAMETER_EXCEPTION = 601;



    // Illegal Parameter
    public static final int WRONG_PARAMETER_EXCEPTION = 730;
    public static final int ILLEGAL_CAMPAIGN_NAME_PARAMETER_EXCEPTION = 731;
    public static final int ILLEGAL_CAMPAIGN_NAME_PATTERN_PARAMETER_EXCEPTION = 732;
    public static final int ILLEGAL_POWERLINK_DAILY_BUDGET_PARAMETER_EXCEPTION = 733 ;
    public static final int ILLEGAL_SHOPPING_DAILY_BUDGET_PARAMETER_EXCEPTION = 734;
    public static final int ILLEGAL_URL_PARAMETER_EXCEPTION = 735;
    public static final int ILLEGAL_BID_AMT_PARAMETER_EXCEPTION = 736;
    public static final int ILLEGAL_GROUP_NAME_PARAMETER_EXCEPTION = 737 ;
    public static final int ILLEGAL_PC_BID_WEIGHT_PARAMETER_EXCEPTION = 738;
    public static final int ILLEGAL_MOBILE_BID_WEIGHT_PARAMETER_EXCEPTION = 739;
    public static final int ILLEGAL_KEYWORD_NAME_PARAMETER_EXCEPTION = 740;
    public static final int ILLEGAL_DESCRIPTION_PARAMETER_EXCEPTION = 741;
    public static final int ILLEGAL_HEADLINE_PARAMETER_EXCEPTION = 742;
    public static final int ILLEGAL_MAP_PARAMETER_EXCEPTION = 743;
    public static final int ILLEGAL_DAILY_BUDGET_PARAMETER_EXCEPTION = 744;
    public static final int ILLEGAL_SHOPPING_BID_AMT_PARAMETER_EXCEPTION = 745;
    public static final int ILLEGAL_DATE_PARAMETER_EXCEPTION = 746;

    public static final int ILLEGAL_START_DATE_PARAMETER_EXCEPTION =747 ;
    public static final int ILLEGAL_END_DATE_PARAMETER_EXCEPTION = 748;
    public static final int ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION = 749;


    public static final int NONNULL_ADGROUP_TYPE_PARAMETER_EXCEPTION = 770 ;



    public ParameterException(int errorCode) {
        super(errorCode);
    }

}
