package model;

import common.AdGroupTypes;
import common.AdRollingTypes;
import lombok.*;
import java.util.List;

/**
 * 그룹 클래스
 */
public class Adgroup {
    /* API 관련 Response 처리 */
    private Long code;
    private String title;
    private String detail;
    /* 키 정보 */
    private String nccAdgroupId;
    private Long customerId;
    private String nccCampaignId;
    private String mobileChannelId;
    private String pcChannelId;
    /* 유형 정보 */
    private String name;
    private AdRollingTypes adRollingType;
    private AdGroupTypes adgroupType;
    /* 타겟 정보 */
    private Object targets; //private List<Target> targets;
    /* 입찰 정보 */
    private Long bidAmt;
    private Long contentsNetworkBidAmt;
    private Boolean useCntsNetworkBidAmt;
    private Long mobileNetworkBidWeight;
    private Long pcNetworkBidWeight;
    /* 하루예산 정보 */
    private Long dailyBudget;
    private Boolean useDailyBudget;
    /* 활성화여부, 상태, 시간정보 */
    private Boolean userLock;
    private String status;
    private String statusReason;
    private String regTm;
    private String editTm;
    private Boolean useKeywordPlus;
    private Integer keywordPlusWeight;

    Adgroup(final Long code, final String title, final String detail, final String nccAdgroupId, final Long customerId, final String nccCampaignId, final String mobileChannelId, final String pcChannelId, final String name, final AdRollingTypes adRollingType, final AdGroupTypes adgroupType, final Object targets, final Long bidAmt, final Long contentsNetworkBidAmt, final Boolean useCntsNetworkBidAmt, final Long mobileNetworkBidWeight, final Long pcNetworkBidWeight, final Long dailyBudget, final Boolean useDailyBudget, final Boolean userLock, final String status, final String statusReason, final String regTm, final String editTm, final Boolean useKeywordPlus, final Integer keywordPlusWeight) {
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.nccAdgroupId = nccAdgroupId;
        this.customerId = customerId;
        this.nccCampaignId = nccCampaignId;
        this.mobileChannelId = mobileChannelId;
        this.pcChannelId = pcChannelId;
        this.name = name;
        this.adRollingType = adRollingType;
        this.adgroupType = adgroupType;
        this.targets = targets;
        this.bidAmt = bidAmt;
        this.contentsNetworkBidAmt = contentsNetworkBidAmt;
        this.useCntsNetworkBidAmt = useCntsNetworkBidAmt;
        this.mobileNetworkBidWeight = mobileNetworkBidWeight;
        this.pcNetworkBidWeight = pcNetworkBidWeight;
        this.dailyBudget = dailyBudget;
        this.useDailyBudget = useDailyBudget;
        this.userLock = userLock;
        this.status = status;
        this.statusReason = statusReason;
        this.regTm = regTm;
        this.editTm = editTm;
        this.useKeywordPlus = useKeywordPlus;
        this.keywordPlusWeight = keywordPlusWeight;
    }


    public static class AdgroupBuilder {
        private Long code;
        private String title;
        private String detail;
        private String nccAdgroupId;
        private Long customerId;
        private String nccCampaignId;
        private String mobileChannelId;
        private String pcChannelId;
        private String name;
        private AdRollingTypes adRollingType;
        private AdGroupTypes adgroupType;
        private Object targets;
        private Long bidAmt;
        private Long contentsNetworkBidAmt;
        private Boolean useCntsNetworkBidAmt;
        private Long mobileNetworkBidWeight;
        private Long pcNetworkBidWeight;
        private Long dailyBudget;
        private Boolean useDailyBudget;
        private Boolean userLock;
        private String status;
        private String statusReason;
        private String regTm;
        private String editTm;
        private Boolean useKeywordPlus;
        private Integer keywordPlusWeight;

        AdgroupBuilder() {
        }

        public Adgroup.AdgroupBuilder code(final Long code) {
            this.code = code;
            return this;
        }

        public Adgroup.AdgroupBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Adgroup.AdgroupBuilder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Adgroup.AdgroupBuilder nccAdgroupId(final String nccAdgroupId) {
            this.nccAdgroupId = nccAdgroupId;
            return this;
        }

        public Adgroup.AdgroupBuilder customerId(final Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Adgroup.AdgroupBuilder nccCampaignId(final String nccCampaignId) {
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public Adgroup.AdgroupBuilder mobileChannelId(final String mobileChannelId) {
            this.mobileChannelId = mobileChannelId;
            return this;
        }

        public Adgroup.AdgroupBuilder pcChannelId(final String pcChannelId) {
            this.pcChannelId = pcChannelId;
            return this;
        }

        public Adgroup.AdgroupBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public Adgroup.AdgroupBuilder adRollingType(final AdRollingTypes adRollingType) {
            this.adRollingType = adRollingType;
            return this;
        }

        public Adgroup.AdgroupBuilder adgroupType(final AdGroupTypes adgroupType) {
            this.adgroupType = adgroupType;
            return this;
        }

        public Adgroup.AdgroupBuilder targets(final Object targets) {
            this.targets = targets;
            return this;
        }

        public Adgroup.AdgroupBuilder bidAmt(final Long bidAmt) {
            this.bidAmt = bidAmt;
            return this;
        }

        public Adgroup.AdgroupBuilder contentsNetworkBidAmt(final Long contentsNetworkBidAmt) {
            this.contentsNetworkBidAmt = contentsNetworkBidAmt;
            return this;
        }

        public Adgroup.AdgroupBuilder useCntsNetworkBidAmt(final Boolean useCntsNetworkBidAmt) {
            this.useCntsNetworkBidAmt = useCntsNetworkBidAmt;
            return this;
        }

        public Adgroup.AdgroupBuilder mobileNetworkBidWeight(final Long mobileNetworkBidWeight) {
            this.mobileNetworkBidWeight = mobileNetworkBidWeight;
            return this;
        }

        public Adgroup.AdgroupBuilder pcNetworkBidWeight(final Long pcNetworkBidWeight) {
            this.pcNetworkBidWeight = pcNetworkBidWeight;
            return this;
        }

        public Adgroup.AdgroupBuilder dailyBudget(final Long dailyBudget) {
            this.dailyBudget = dailyBudget;
            return this;
        }

        public Adgroup.AdgroupBuilder useDailyBudget(final Boolean useDailyBudget) {
            this.useDailyBudget = useDailyBudget;
            return this;
        }

        public Adgroup.AdgroupBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public Adgroup.AdgroupBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public Adgroup.AdgroupBuilder statusReason(final String statusReason) {
            this.statusReason = statusReason;
            return this;
        }

        public Adgroup.AdgroupBuilder regTm(final String regTm) {
            this.regTm = regTm;
            return this;
        }

        public Adgroup.AdgroupBuilder editTm(final String editTm) {
            this.editTm = editTm;
            return this;
        }

        public Adgroup.AdgroupBuilder useKeywordPlus(final Boolean useKeywordPlus) {
            this.useKeywordPlus = useKeywordPlus;
            return this;
        }

        public Adgroup.AdgroupBuilder keywordPlusWeight(final Integer keywordPlusWeight) {
            this.keywordPlusWeight = keywordPlusWeight;
            return this;
        }

        public Adgroup build() {
            return new Adgroup(this.code, this.title, this.detail, this.nccAdgroupId, this.customerId, this.nccCampaignId, this.mobileChannelId, this.pcChannelId, this.name, this.adRollingType, this.adgroupType, this.targets, this.bidAmt, this.contentsNetworkBidAmt, this.useCntsNetworkBidAmt, this.mobileNetworkBidWeight, this.pcNetworkBidWeight, this.dailyBudget, this.useDailyBudget, this.userLock, this.status, this.statusReason, this.regTm, this.editTm, this.useKeywordPlus, this.keywordPlusWeight);
        }

        @Override
        public String toString() {
            return "Adgroup.AdgroupBuilder(code=" + this.code + ", title=" + this.title + ", detail=" + this.detail + ", nccAdgroupId=" + this.nccAdgroupId + ", customerId=" + this.customerId + ", nccCampaignId=" + this.nccCampaignId + ", mobileChannelId=" + this.mobileChannelId + ", pcChannelId=" + this.pcChannelId + ", name=" + this.name + ", adRollingType=" + this.adRollingType + ", adgroupType=" + this.adgroupType + ", targets=" + this.targets + ", bidAmt=" + this.bidAmt + ", contentsNetworkBidAmt=" + this.contentsNetworkBidAmt + ", useCntsNetworkBidAmt=" + this.useCntsNetworkBidAmt + ", mobileNetworkBidWeight=" + this.mobileNetworkBidWeight + ", pcNetworkBidWeight=" + this.pcNetworkBidWeight + ", dailyBudget=" + this.dailyBudget + ", useDailyBudget=" + this.useDailyBudget + ", userLock=" + this.userLock + ", status=" + this.status + ", statusReason=" + this.statusReason + ", regTm=" + this.regTm + ", editTm=" + this.editTm + ", useKeywordPlus=" + this.useKeywordPlus + ", keywordPlusWeight=" + this.keywordPlusWeight + ")";
        }
    }

    public static Adgroup.AdgroupBuilder builder() {
        return new Adgroup.AdgroupBuilder();
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

    public String getNccAdgroupId() {
        return this.nccAdgroupId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public String getMobileChannelId() {
        return this.mobileChannelId;
    }

    public String getPcChannelId() {
        return this.pcChannelId;
    }

    public String getName() {
        return this.name;
    }

    public AdRollingTypes getAdRollingType() {
        return this.adRollingType;
    }

    public AdGroupTypes getAdgroupType() {
        return this.adgroupType;
    }

    public Object getTargets() {
        return this.targets;
    }

    public Long getBidAmt() {
        return this.bidAmt;
    }

    public Long getContentsNetworkBidAmt() {
        return this.contentsNetworkBidAmt;
    }

    public Boolean getUseCntsNetworkBidAmt() {
        return this.useCntsNetworkBidAmt;
    }

    public Long getMobileNetworkBidWeight() {
        return this.mobileNetworkBidWeight;
    }

    public Long getPcNetworkBidWeight() {
        return this.pcNetworkBidWeight;
    }

    public Long getDailyBudget() {
        return this.dailyBudget;
    }

    public Boolean getUseDailyBudget() {
        return this.useDailyBudget;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public String getStatus() {
        return this.status;
    }

    public String getStatusReason() {
        return this.statusReason;
    }

    public String getRegTm() {
        return this.regTm;
    }

    public String getEditTm() {
        return this.editTm;
    }

    public Boolean getUseKeywordPlus() {
        return this.useKeywordPlus;
    }

    public Integer getKeywordPlusWeight() {
        return this.keywordPlusWeight;
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

    public void setNccAdgroupId(final String nccAdgroupId) {
        this.nccAdgroupId = nccAdgroupId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setMobileChannelId(final String mobileChannelId) {
        this.mobileChannelId = mobileChannelId;
    }

    public void setPcChannelId(final String pcChannelId) {
        this.pcChannelId = pcChannelId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAdRollingType(final AdRollingTypes adRollingType) {
        this.adRollingType = adRollingType;
    }

    public void setAdgroupType(final AdGroupTypes adgroupType) {
        this.adgroupType = adgroupType;
    }

    public void setTargets(final Object targets) {
        this.targets = targets;
    }

    public void setBidAmt(final Long bidAmt) {
        this.bidAmt = bidAmt;
    }

    public void setContentsNetworkBidAmt(final Long contentsNetworkBidAmt) {
        this.contentsNetworkBidAmt = contentsNetworkBidAmt;
    }

    public void setUseCntsNetworkBidAmt(final Boolean useCntsNetworkBidAmt) {
        this.useCntsNetworkBidAmt = useCntsNetworkBidAmt;
    }

    public void setMobileNetworkBidWeight(final Long mobileNetworkBidWeight) {
        this.mobileNetworkBidWeight = mobileNetworkBidWeight;
    }

    public void setPcNetworkBidWeight(final Long pcNetworkBidWeight) {
        this.pcNetworkBidWeight = pcNetworkBidWeight;
    }

    public void setDailyBudget(final Long dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public void setUseDailyBudget(final Boolean useDailyBudget) {
        this.useDailyBudget = useDailyBudget;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setStatusReason(final String statusReason) {
        this.statusReason = statusReason;
    }

    public void setRegTm(final String regTm) {
        this.regTm = regTm;
    }

    public void setEditTm(final String editTm) {
        this.editTm = editTm;
    }

    public void setUseKeywordPlus(final Boolean useKeywordPlus) {
        this.useKeywordPlus = useKeywordPlus;
    }

    public void setKeywordPlusWeight(final Integer keywordPlusWeight) {
        this.keywordPlusWeight = keywordPlusWeight;
    }
}
