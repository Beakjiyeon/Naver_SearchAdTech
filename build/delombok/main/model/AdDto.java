package model;

import common.AdTypes;
import common.InspectStatus;
import java.util.Map;

/**
 * 소재 클래스 (파워링크, 쇼핑검색 모두 통합해서 통신하기 위한 객체)
 */
public class AdDto {
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
    private Map<String, Object> ad;
    private Object adAttr;
    private String referenceKey;
    private Object referenceData;

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

    public Map<String, Object> getAd() {
        return this.ad;
    }

    public Object getAdAttr() {
        return this.adAttr;
    }

    public String getReferenceKey() {
        return this.referenceKey;
    }

    public Object getReferenceData() {
        return this.referenceData;
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

    public void setAd(final Map<String, Object> ad) {
        this.ad = ad;
    }

    public void setAdAttr(final Object adAttr) {
        this.adAttr = adAttr;
    }

    public void setReferenceKey(final String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public void setReferenceData(final Object referenceData) {
        this.referenceData = referenceData;
    }
}
