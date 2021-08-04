package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 캠페인 노출 기간 등록, 수정 시 필요한 정보 클래스
 * */
@Getter
@Setter
public class Period {
    private String nccCampaignId;
    private Boolean userLock;
    private String periodStartDt;
    private String periodEndDt;
    private Boolean usePeriod;

    @Builder
    public Period(@NonNull String nccCampaignId,
                  @NonNull Boolean userLock,
                  String periodStartDt,
                  String periodEndDt,
                  Boolean usePeriod) {

        this.nccCampaignId = nccCampaignId;
        this.userLock = userLock;
        this.periodStartDt = periodStartDt;
        this.periodEndDt = periodEndDt;
        this.usePeriod = usePeriod;
    }
}
