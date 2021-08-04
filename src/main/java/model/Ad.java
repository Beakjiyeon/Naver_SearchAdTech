package model;

import common.AdTypes;
import common.InspectStatus;
import lombok.*;
import java.util.Map;
/**
 * 소재 클래스
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    public Ad(String nccAdgroupId, AdTypes type){
        this.nccAdgroupId = nccAdgroupId;
        this.type = type;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdId 소재 ID
     * @param userLock 소재의 활성화 여부
     */
    public Ad(String nccAdId, Boolean userLock){
        this.nccAdId = nccAdId;
        this.userLock = userLock;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdId 소재 ID
     * @param inspectRequestMsg 소재 검토요청 메세지
     */
    public Ad(String nccAdId, String inspectRequestMsg){
        this.nccAdId = nccAdId;
        this.inspectRequestMsg = inspectRequestMsg;
    }

    /**
     * 소재 객체 생성자
     * @param nccAdgroupId 그룹 ID
     * @param type 소재 유형
     * @param customerId 고객 ID
     */
    public Ad(String nccAdgroupId, AdTypes type, Long customerId){
        this.nccAdgroupId = nccAdgroupId;
        this.type = type;
        this.customerId = customerId;
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
