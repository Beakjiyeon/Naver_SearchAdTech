package common;

/**
 * 그룹 유형 (WEB_SITE, CATALOG)
 */
public enum AdGroupTypes {
    WEB_SITE("WEB_SITE"),
    CATALOG("CATALOG");

    private String adGroupType;

    AdGroupTypes(String adGroupType){
        this.adGroupType = adGroupType;
    }
}
