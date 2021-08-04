package model;

import common.AdTypes;
import common.InspectStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 소재 클래스 (파워링크, 쇼핑검색 모두 통합해서 통신하기 위한 객체)
 */
@Getter
@Setter
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

}
