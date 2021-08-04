package model;

import common.AdTypes;
import common.InspectStatus;
import lombok.*;
import java.util.Map;

/**
 * 소재 클래스
 */
public class Ad {
    /* API 관련 Response 처리 */
    private Long code;
    private String title;
    private String detail;
    /* 키 정보 */
    private String nccAdId;
    private String nccAdgroupId;
    private Long customerId;
    /* 소재 유형 */
    private AdTypes type;
    /* 상태, 검토 관련, 활성화, 품질지수 */
    private String status;
    private String statusReason;
    private String inspectRequestMsg;
    private InspectStatus inspectStatus;
    private Boolean userLock;
    private Map<String, Object> nccQi;

    /**
     * 소재 객체 생성자
     * @param nccAdgroupId 그룹 ID
     * @param type 소재 유형
     */
    public Ad(String nccAdgroupId, AdTypes type) {
        this.nccAdgroupId = nccAdgroupId;
        this.type = type;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdId 소재 ID
     * @param userLock 소재의 활성화 여부
     */
    public Ad(String nccAdId, Boolean userLock) {
        this.nccAdId = nccAdId;
        this.userLock = userLock;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdId 소재 ID
     * @param inspectRequestMsg 소재 검토요청 메세지
     */
    public Ad(String nccAdId, String inspectRequestMsg) {
        this.nccAdId = nccAdId;
        this.inspectRequestMsg = inspectRequestMsg;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdgroupId 그룹 ID
     * @param type 소재 유형
     * @param customerId 고객 ID
     */
    public Ad(String nccAdgroupId, AdTypes type, Long customerId) {
        this.nccAdgroupId = nccAdgroupId;
        this.type = type;
        this.customerId = customerId;
    }


    public static class AdBuilder {
        private Long code;
        private String title;
        private String detail;
        private String nccAdId;
        private String nccAdgroupId;
        private Long customerId;
        private AdTypes type;
        private String status;
        private String statusReason;
        private String inspectRequestMsg;
        private InspectStatus inspectStatus;
        private Boolean userLock;
        private Map<String, Object> nccQi;

        AdBuilder() {
        }

        public Ad.AdBuilder code(final Long code) {
            this.code = code;
            return this;
        }

        public Ad.AdBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public Ad.AdBuilder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Ad.AdBuilder nccAdId(final String nccAdId) {
            this.nccAdId = nccAdId;
            return this;
        }

        public Ad.AdBuilder nccAdgroupId(final String nccAdgroupId) {
            this.nccAdgroupId = nccAdgroupId;
            return this;
        }

        public Ad.AdBuilder customerId(final Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Ad.AdBuilder type(final AdTypes type) {
            this.type = type;
            return this;
        }

        public Ad.AdBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public Ad.AdBuilder statusReason(final String statusReason) {
            this.statusReason = statusReason;
            return this;
        }

        public Ad.AdBuilder inspectRequestMsg(final String inspectRequestMsg) {
            this.inspectRequestMsg = inspectRequestMsg;
            return this;
        }

        public Ad.AdBuilder inspectStatus(final InspectStatus inspectStatus) {
            this.inspectStatus = inspectStatus;
            return this;
        }

        public Ad.AdBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public Ad.AdBuilder nccQi(final Map<String, Object> nccQi) {
            this.nccQi = nccQi;
            return this;
        }

        public Ad build() {
            return new Ad(this.code, this.title, this.detail, this.nccAdId, this.nccAdgroupId, this.customerId, this.type, this.status, this.statusReason, this.inspectRequestMsg, this.inspectStatus, this.userLock, this.nccQi);
        }

        @Override
        public String toString() {
            return "Ad.AdBuilder(code=" + this.code + ", title=" + this.title + ", detail=" + this.detail + ", nccAdId=" + this.nccAdId + ", nccAdgroupId=" + this.nccAdgroupId + ", customerId=" + this.customerId + ", type=" + this.type + ", status=" + this.status + ", statusReason=" + this.statusReason + ", inspectRequestMsg=" + this.inspectRequestMsg + ", inspectStatus=" + this.inspectStatus + ", userLock=" + this.userLock + ", nccQi=" + this.nccQi + ")";
        }
    }

    public static Ad.AdBuilder builder() {
        return new Ad.AdBuilder();
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

    public String getNccAdId() {
        return this.nccAdId;
    }

    public String getNccAdgroupId() {
        return this.nccAdgroupId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public AdTypes getType() {
        return this.type;
    }

    public String getStatus() {
        return this.status;
    }

    public String getStatusReason() {
        return this.statusReason;
    }

    public String getInspectRequestMsg() {
        return this.inspectRequestMsg;
    }

    public InspectStatus getInspectStatus() {
        return this.inspectStatus;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public Map<String, Object> getNccQi() {
        return this.nccQi;
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

    public void setNccAdId(final String nccAdId) {
        this.nccAdId = nccAdId;
    }

    public void setNccAdgroupId(final String nccAdgroupId) {
        this.nccAdgroupId = nccAdgroupId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public void setType(final AdTypes type) {
        this.type = type;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setStatusReason(final String statusReason) {
        this.statusReason = statusReason;
    }

    public void setInspectRequestMsg(final String inspectRequestMsg) {
        this.inspectRequestMsg = inspectRequestMsg;
    }

    public void setInspectStatus(final InspectStatus inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }

    public void setNccQi(final Map<String, Object> nccQi) {
        this.nccQi = nccQi;
    }

    public Ad(final Long code, final String title, final String detail, final String nccAdId, final String nccAdgroupId, final Long customerId, final AdTypes type, final String status, final String statusReason, final String inspectRequestMsg, final InspectStatus inspectStatus, final Boolean userLock, final Map<String, Object> nccQi) {
        this.code = code;
        this.title = title;
        this.detail = detail;
        this.nccAdId = nccAdId;
        this.nccAdgroupId = nccAdgroupId;
        this.customerId = customerId;
        this.type = type;
        this.status = status;
        this.statusReason = statusReason;
        this.inspectRequestMsg = inspectRequestMsg;
        this.inspectStatus = inspectStatus;
        this.userLock = userLock;
        this.nccQi = nccQi;
    }

    public Ad() {
    }
//
//    public String toString(){
//        return "" + code + title + detail + nccAdId +nccAdgroupId + customerId + type + status + statusReason + inspectStatus + inspectRequestMsg + userLock + nccQi;
//    }
//
//    @Builder
//    public Ad(String nccAdId,
//           String nccAdgroupId,
//           Long customerId,
//           InspectStatus inspectStatus,
//           String type,
//           Object adAttr,
//           Map<String, Object> ad,
//           Boolean userLock,
//           String inspectRequestMsg,
//              String referenceKey){
//        this.adAttr = adAttr;
//        this.nccAdId = nccAdId;
//        this.nccAdgroupId = nccAdgroupId;
//        this.customerId = customerId;
//        this.inspectStatus = inspectStatus;
//        this.type = type;
//        this.ad =ad;
//        this.userLock =userLock;
//        this.inspectRequestMsg = inspectRequestMsg;
//        this.referenceKey=referenceKey;
//    }
}
