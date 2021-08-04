package model;

import common.AdGroupTypes;
import common.AdRollingTypes;
import lombok.*;
import java.util.List;

/**
 * 그룹 클래스
 */
@Getter
@Setter
@Builder
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

}
