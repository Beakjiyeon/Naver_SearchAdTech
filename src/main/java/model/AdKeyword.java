package model;

import common.InspectStatus;
import lombok.*;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
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
    @Getter
    public static class ResultStatus {
        private Integer code;
        private String message;
    }
}
