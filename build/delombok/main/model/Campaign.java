package model;

import common.CampaignTypes;
import common.DeliveryMethodTypes;
import common.TrackingModeTypes;
import lombok.*;

/**
 * 캠페인 클래스
 */
public class Campaign {
    /* API 관련 Response 처리 */
    private Long code;
    private String title;
    private String detail;
    /* 키 정보 */
    private String nccCampaignId;
    private Long customerId;
    /* 유형 */
    private CampaignTypes campaignTp;
    private DeliveryMethodTypes deliveryMethod;
    /* 하루예산 정보 */
    private Boolean useDailyBudget;
    private Long dailyBudget;
    private TrackingModeTypes trackingMode;
    private String trackingUrl;
    /* 노출기간 */
    private Boolean usePeriod;
    private String periodStartDt;
    private String periodEndDt;
    /* 활성화 여부, 이름 */
    private String name;
    private Boolean userLock;


    public static class CampaignBuilder {
        private Long code;
        private String title;
        private String detail;
        private String nccCampaignId;
        private Long customerId;
        private CampaignTypes campaignTp;
        private DeliveryMethodTypes deliveryMethod;
        private Boolean useDailyBudget;
        private Long dailyBudget;
        private TrackingModeTypes trackingMode;
        private String trackingUrl;
        private Boolean usePeriod;
        private String periodStartDt;
        private String periodEndDt;
        private String name;
        private Boolean userLock;

        CampaignBuilder() {
        }

        public Campaign.CampaignBuilder code(final Long code) {
            this.code = code;
            return this;
        }

        public Campaign.CampaignBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Campaign.CampaignBuilder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Campaign.CampaignBuilder nccCampaignId(final String nccCampaignId) {
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public Campaign.CampaignBuilder customerId(final Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Campaign.CampaignBuilder campaignTp(final CampaignTypes campaignTp) {
            this.campaignTp = campaignTp;
            return this;
        }

        public Campaign.CampaignBuilder deliveryMethod(final DeliveryMethodTypes deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
            return this;
        }

        public Campaign.CampaignBuilder useDailyBudget(final Boolean useDailyBudget) {
            this.useDailyBudget = useDailyBudget;
            return this;
        }

        public Campaign.CampaignBuilder dailyBudget(final Long dailyBudget) {
            this.dailyBudget = dailyBudget;
            return this;
        }

        public Campaign.CampaignBuilder trackingMode(final TrackingModeTypes trackingMode) {
            this.trackingMode = trackingMode;
            return this;
        }

        public Campaign.CampaignBuilder trackingUrl(final String trackingUrl) {
            this.trackingUrl = trackingUrl;
            return this;
        }

        public Campaign.CampaignBuilder usePeriod(final Boolean usePeriod) {
            this.usePeriod = usePeriod;
            return this;
        }

        public Campaign.CampaignBuilder periodStartDt(final String periodStartDt) {
            this.periodStartDt = periodStartDt;
            return this;
        }

        public Campaign.CampaignBuilder periodEndDt(final String periodEndDt) {
            this.periodEndDt = periodEndDt;
            return this;
        }

        public Campaign.CampaignBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public Campaign.CampaignBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public Campaign build() {
            return new Campaign(this.code, this.title, this.detail, this.nccCampaignId, this.customerId, this.campaignTp, this.deliveryMethod, this.useDailyBudget, this.dailyBudget, this.trackingMode, this.trackingUrl, this.usePeriod, this.periodStartDt, this.periodEndDt, this.name, this.userLock);
        }

        @Override
        public String toString() {
            return "Campaign.CampaignBuilder(code=" + this.code + ", title=" + this.title + ", detail=" + this.detail + ", nccCampaignId=" + this.nccCampaignId + ", customerId=" + this.customerId + ", campaignTp=" + this.campaignTp + ", deliveryMethod=" + this.deliveryMethod + ", useDailyBudget=" + this.useDailyBudget + ", dailyBudget=" + this.dailyBudget + ", trackingMode=" + this.trackingMode + ", trackingUrl=" + this.trackingUrl + ", usePeriod=" + this.usePeriod + ", periodStartDt=" + this.periodStartDt + ", periodEndDt=" + this.periodEndDt + ", name=" + this.name + ", userLock=" + this.userLock + ")";
        }
    }

    public static Campaign.CampaignBuilder builder() {
        return new Campaign.CampaignBuilder();
    }

    public Long getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public CampaignTypes getCampaignTp() {
        return this.campaignTp;
    }

    public DeliveryMethodTypes getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public Boolean getUseDailyBudget() {
        return this.useDailyBudget;
    }

    public Long getDailyBudget() {
        return this.dailyBudget;
    }

    public TrackingModeTypes getTrackingMode() {
        return this.trackingMode;
    }

    public String getTrackingUrl() {
        return this.trackingUrl;
    }

    public Boolean getUsePeriod() {
        return this.usePeriod;
    }

    public String getPeriodStartDt() {
        return this.periodStartDt;
    }

    public String getPeriodEndDt() {
        return this.periodEndDt;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public void setCode(final Long code) {
        this.code = code;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDetail(final String detail) {
        this.detail = detail;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public void setCampaignTp(final CampaignTypes campaignTp) {
        this.campaignTp = campaignTp;
    }

    public void setDeliveryMethod(final DeliveryMethodTypes deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setUseDailyBudget(final Boolean useDailyBudget) {
        this.useDailyBudget = useDailyBudget;
    }

    public void setDailyBudget(final Long dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public void setTrackingMode(final TrackingModeTypes trackingMode) {
        this.trackingMode = trackingMode;
    }

    public void setTrackingUrl(final String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public void setUsePeriod(final Boolean usePeriod) {
        this.usePeriod = usePeriod;
    }

    public void setPeriodStartDt(final String periodStartDt) {
        this.periodStartDt = periodStartDt;
    }

    public void setPeriodEndDt(final String periodEndDt) {
        this.periodEndDt = periodEndDt;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }

    public Campaign(final Long code, final String title, final String detail, final String nccCampaignId, final Long customerId, final CampaignTypes campaignTp, final DeliveryMethodTypes deliveryMethod, final Boolean useDailyBudget, final Long dailyBudget, final TrackingModeTypes trackingMode, final String trackingUrl, final Boolean usePeriod, final String periodStartDt, final String periodEndDt, final String name, final Boolean userLock) {
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.nccCampaignId = nccCampaignId;
        this.customerId = customerId;
        this.campaignTp = campaignTp;
        this.deliveryMethod = deliveryMethod;
        this.useDailyBudget = useDailyBudget;
        this.dailyBudget = dailyBudget;
        this.trackingMode = trackingMode;
        this.trackingUrl = trackingUrl;
        this.usePeriod = usePeriod;
        this.periodStartDt = periodStartDt;
        this.periodEndDt = periodEndDt;
        this.name = name;
        this.userLock = userLock;
    }
}
