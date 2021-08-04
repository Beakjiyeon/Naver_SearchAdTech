package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 캠페인 예산 등록, 수정 시 필요 정보를 담은 클래스
 * */
@Getter
@Setter
public class Budget {
    private String nccCampaignId;
    private Boolean userLock;
    private Long dailyBudget;
    private Boolean useDailyBudget;

    @Builder
    public Budget(String nccCampaignId,
                  Boolean userLock,
                  Long dailyBudget,
                  Boolean useDailyBudget) {

        this.nccCampaignId = nccCampaignId;
        this.userLock = userLock;
        this.dailyBudget = dailyBudget;
        this.useDailyBudget = useDailyBudget;
    }
}
