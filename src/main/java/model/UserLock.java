package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
/**
 * 캠패인 활성화 여부 클래스
 * */
@Getter
@Setter
@Builder
public class UserLock {
    private String nccCampaignId;
    private Boolean userLock;
}
