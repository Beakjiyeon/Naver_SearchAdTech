package common;

/**
 * 전환 추적 모드 유형 (TRACKING_DISABLED, AUTO_TRACKING_MODE, PASS_THROUGH_SITE_MODE)
 */
public enum TrackingModeTypes {

    TRACKING_DISABLED("TRACKING_DISABLED"),
    AUTO_TRACKING_MODE("AUTO_TRACKING_MODE"),
    PASS_THROUGH_SITE_MODE("PASS_THROUGH_SITE_MODE");

    private String trackingModeType;

    TrackingModeTypes(String trackingMode){
        this.trackingModeType = trackingModeType;
    }
}
