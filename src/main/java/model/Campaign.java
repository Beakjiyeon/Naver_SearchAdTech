package model;
import common.CampaignTypes;
import common.DeliveryMethodTypes;
import common.TrackingModeTypes;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor

/**
 * 캠페인 클래스
 * */
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

}