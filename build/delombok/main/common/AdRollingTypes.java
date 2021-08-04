package common;

/**
 * 소재 노출 방식 유형 (ROUND_ROBIN, PERFORMANCE)
 */
public enum AdRollingTypes {
    ROUND_ROBIN("ROUND_ROBIN"),
    PERFORMANCE("PERFORMANCE");

    private String adRollingType;

    AdRollingTypes(String adRollingType){
        this.adRollingType = adRollingType;
    }
}
