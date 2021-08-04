package model;

import lombok.NonNull;

/**
 * 캠패인 활성화 여부 클래스
 */
public class UserLock {
    private String nccCampaignId;
    private Boolean userLock;

    UserLock(final String nccCampaignId, final Boolean userLock) {
        this.nccCampaignId = nccCampaignId;
        this.userLock = userLock;
    }


    public static class UserLockBuilder {
        private String nccCampaignId;
        private Boolean userLock;

        UserLockBuilder() {
        }

        public UserLock.UserLockBuilder nccCampaignId(final String nccCampaignId) {
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public UserLock.UserLockBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public UserLock build() {
            return new UserLock(this.nccCampaignId, this.userLock);
        }

        @Override
        public String toString() {
            return "UserLock.UserLockBuilder(nccCampaignId=" + this.nccCampaignId + ", userLock=" + this.userLock + ")";
        }
    }

    public static UserLock.UserLockBuilder builder() {
        return new UserLock.UserLockBuilder();
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }
}
