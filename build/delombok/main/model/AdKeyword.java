package model;

import common.InspectStatus;
import lombok.*;
import java.util.Date;
import java.util.Map;

/**
 * 키워드 클래스
 */
public class AdKeyword {
    /* API 관련 Response 처리 */
    private Long code;
    private String title;
    private String detail;
    /* 키 정보 */
    private String nccKeywordId;
    private Long customerId;
    private String nccAdgroupId;
    private String nccCampaignId;
    /* 키워드*/
    private String keyword;
    /* PC, Mobile 연결 URL*/
    private Map<String, Object> links;
    /* 입찰 정보 */
    private Long bidAmt;
    private Boolean useGroupBidAmt;
    /* 활성화 여부, 상태, 품질지수, 시간, 검토 관련 */
    private Boolean userLock;
    private String status;
    private String statusReason;
    private NccQi nccQi;
    private Date regTm;
    private Date editTm;
    private InspectStatus inspectStatus;
    private ManagedKeyword managedKeyword;
    private ResultStatus resultStatus;


    /**
     * 품질 지수 클래스
     */
    public static class NccQi {
        private Integer qiGrade;
    }


    /**
     * 키워드 관련 클래스
     */
    public static class ManagedKeyword {
        private String keyword;
        private Boolean isAdult;
        private Boolean isBrand;
        private Boolean isRestricted;
        private Boolean isSeason;
        private Boolean isSellProhibit;
        private Boolean isShoppingMall;
        private Boolean isLowSearchVolume;
        private Integer pCPLMaxDepth;
        private Date regTm;
        private Date editTm;
    }


    /**
     * 키워드 결과 클래스
     */
    public static class ResultStatus {
        private Integer code;
        private String message;

        public Integer getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    AdKeyword(final Long code, final String title, final String detail, final String nccKeywordId, final Long customerId, final String nccAdgroupId, final String nccCampaignId, final String keyword, final Map<String, Object> links, final Long bidAmt, final Boolean useGroupBidAmt, final Boolean userLock, final String status, final String statusReason, final NccQi nccQi, final Date regTm, final Date editTm, final InspectStatus inspectStatus, final ManagedKeyword managedKeyword, final ResultStatus resultStatus) {
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.nccKeywordId = nccKeywordId;
        this.customerId = customerId;
        this.nccAdgroupId = nccAdgroupId;
        this.nccCampaignId = nccCampaignId;
        this.keyword = keyword;
        this.links = links;
        this.bidAmt = bidAmt;
        this.useGroupBidAmt = useGroupBidAmt;
        this.userLock = userLock;
        this.status = status;
        this.statusReason = statusReason;
        this.nccQi = nccQi;
        this.regTm = regTm;
        this.editTm = editTm;
        this.inspectStatus = inspectStatus;
        this.managedKeyword = managedKeyword;
        this.resultStatus = resultStatus;
    }


    public static class AdKeywordBuilder {
        private Long code;
        private String title;
        private String detail;
        private String nccKeywordId;
        private Long customerId;
        private String nccAdgroupId;
        private String nccCampaignId;
        private String keyword;
        private Map<String, Object> links;
        private Long bidAmt;
        private Boolean useGroupBidAmt;
        private Boolean userLock;
        private String status;
        private String statusReason;
        private NccQi nccQi;
        private Date regTm;
        private Date editTm;
        private InspectStatus inspectStatus;
        private ManagedKeyword managedKeyword;
        private ResultStatus resultStatus;

        AdKeywordBuilder() {
        }

        public AdKeyword.AdKeywordBuilder code(final Long code) {
            this.code = code;
            return this;
        }

        public AdKeyword.AdKeywordBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public AdKeyword.AdKeywordBuilder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public AdKeyword.AdKeywordBuilder nccKeywordId(final String nccKeywordId) {
            this.nccKeywordId = nccKeywordId;
            return this;
        }

        public AdKeyword.AdKeywordBuilder customerId(final Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public AdKeyword.AdKeywordBuilder nccAdgroupId(final String nccAdgroupId) {
            this.nccAdgroupId = nccAdgroupId;
            return this;
        }

        public AdKeyword.AdKeywordBuilder nccCampaignId(final String nccCampaignId) {
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public AdKeyword.AdKeywordBuilder keyword(final String keyword) {
            this.keyword = keyword;
            return this;
        }

        public AdKeyword.AdKeywordBuilder links(final Map<String, Object> links) {
            this.links = links;
            return this;
        }

        public AdKeyword.AdKeywordBuilder bidAmt(final Long bidAmt) {
            this.bidAmt = bidAmt;
            return this;
        }

        public AdKeyword.AdKeywordBuilder useGroupBidAmt(final Boolean useGroupBidAmt) {
            this.useGroupBidAmt = useGroupBidAmt;
            return this;
        }

        public AdKeyword.AdKeywordBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public AdKeyword.AdKeywordBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public AdKeyword.AdKeywordBuilder statusReason(final String statusReason) {
            this.statusReason = statusReason;
            return this;
        }

        public AdKeyword.AdKeywordBuilder nccQi(final NccQi nccQi) {
            this.nccQi = nccQi;
            return this;
        }

        public AdKeyword.AdKeywordBuilder regTm(final Date regTm) {
            this.regTm = regTm;
            return this;
        }

        public AdKeyword.AdKeywordBuilder editTm(final Date editTm) {
            this.editTm = editTm;
            return this;
        }

        public AdKeyword.AdKeywordBuilder inspectStatus(final InspectStatus inspectStatus) {
            this.inspectStatus = inspectStatus;
            return this;
        }

        public AdKeyword.AdKeywordBuilder managedKeyword(final ManagedKeyword managedKeyword) {
            this.managedKeyword = managedKeyword;
            return this;
        }

        public AdKeyword.AdKeywordBuilder resultStatus(final ResultStatus resultStatus) {
            this.resultStatus = resultStatus;
            return this;
        }

        public AdKeyword build() {
            return new AdKeyword(this.code, this.title, this.detail, this.nccKeywordId, this.customerId, this.nccAdgroupId, this.nccCampaignId, this.keyword, this.links, this.bidAmt, this.useGroupBidAmt, this.userLock, this.status, this.statusReason, this.nccQi, this.regTm, this.editTm, this.inspectStatus, this.managedKeyword, this.resultStatus);
        }

        @Override
        public String toString() {
            return "AdKeyword.AdKeywordBuilder(code=" + this.code + ", title=" + this.title + ", detail=" + this.detail + ", nccKeywordId=" + this.nccKeywordId + ", customerId=" + this.customerId + ", nccAdgroupId=" + this.nccAdgroupId + ", nccCampaignId=" + this.nccCampaignId + ", keyword=" + this.keyword + ", links=" + this.links + ", bidAmt=" + this.bidAmt + ", useGroupBidAmt=" + this.useGroupBidAmt + ", userLock=" + this.userLock + ", status=" + this.status + ", statusReason=" + this.statusReason + ", nccQi=" + this.nccQi + ", regTm=" + this.regTm + ", editTm=" + this.editTm + ", inspectStatus=" + this.inspectStatus + ", managedKeyword=" + this.managedKeyword + ", resultStatus=" + this.resultStatus + ")";
        }
    }

    public static AdKeyword.AdKeywordBuilder builder() {
        return new AdKeyword.AdKeywordBuilder();
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

    public String getNccKeywordId() {
        return this.nccKeywordId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public String getNccAdgroupId() {
        return this.nccAdgroupId;
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public Map<String, Object> getLinks() {
        return this.links;
    }

    public Long getBidAmt() {
        return this.bidAmt;
    }

    public Boolean getUseGroupBidAmt() {
        return this.useGroupBidAmt;
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

    public NccQi getNccQi() {
        return this.nccQi;
    }

    public Date getRegTm() {
        return this.regTm;
    }

    public Date getEditTm() {
        return this.editTm;
    }

    public InspectStatus getInspectStatus() {
        return this.inspectStatus;
    }

    public ManagedKeyword getManagedKeyword() {
        return this.managedKeyword;
    }

    public ResultStatus getResultStatus() {
        return this.resultStatus;
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

    public void setNccKeywordId(final String nccKeywordId) {
        this.nccKeywordId = nccKeywordId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public void setNccAdgroupId(final String nccAdgroupId) {
        this.nccAdgroupId = nccAdgroupId;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public void setLinks(final Map<String, Object> links) {
        this.links = links;
    }

    public void setBidAmt(final Long bidAmt) {
        this.bidAmt = bidAmt;
    }

    public void setUseGroupBidAmt(final Boolean useGroupBidAmt) {
        this.useGroupBidAmt = useGroupBidAmt;
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

    public void setNccQi(final NccQi nccQi) {
        this.nccQi = nccQi;
    }

    public void setRegTm(final Date regTm) {
        this.regTm = regTm;
    }

    public void setEditTm(final Date editTm) {
        this.editTm = editTm;
    }

    public void setInspectStatus(final InspectStatus inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    public void setManagedKeyword(final ManagedKeyword managedKeyword) {
        this.managedKeyword = managedKeyword;
    }

    public void setResultStatus(final ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
