package common;

/**
 * 캠페인 유형 (WEB_SITE, SHOPPING)
 */
public enum CampaignTypes {
    WEB_SITE("WEB_SITE"),
    SHOPPING("SHOPPING");

    private String campaignType;

    CampaignTypes(String campaignType){
        this.campaignType = campaignType;
    }
}
