package model;

/**
 * 캠페인 예산 등록, 수정 시 필요 정보를 담은 클래스
 */
public class Budget {
    private String nccCampaignId;
    private Boolean userLock;
    private Long dailyBudget;
    private Boolean useDailyBudget;

    public Budget(String nccCampaignId, Boolean userLock, Long dailyBudget, Boolean useDailyBudget) {
        this.nccCampaignId = nccCampaignId;
        this.userLock = userLock;
        this.dailyBudget = dailyBudget;
        this.useDailyBudget = useDailyBudget;
    }


    public static class BudgetBuilder {
        private String nccCampaignId;
        private Boolean userLock;
        private Long dailyBudget;
        private Boolean useDailyBudget;

        BudgetBuilder() {
        }

        public Budget.BudgetBuilder nccCampaignId(final String nccCampaignId) {
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public Budget.BudgetBuilder userLock(final Boolean userLock) {
            this.userLock = userLock;
            return this;
        }

        public Budget.BudgetBuilder dailyBudget(final Long dailyBudget) {
            this.dailyBudget = dailyBudget;
            return this;
        }

        public Budget.BudgetBuilder useDailyBudget(final Boolean useDailyBudget) {
            this.useDailyBudget = useDailyBudget;
            return this;
        }

        public Budget build() {
            return new Budget(this.nccCampaignId, this.userLock, this.dailyBudget, this.useDailyBudget);
        }

        @Override
        public String toString() {
            return "Budget.BudgetBuilder(nccCampaignId=" + this.nccCampaignId + ", userLock=" + this.userLock + ", dailyBudget=" + this.dailyBudget + ", useDailyBudget=" + this.useDailyBudget + ")";
        }
    }

    public static Budget.BudgetBuilder builder() {
        return new Budget.BudgetBuilder();
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public Long getDailyBudget() {
        return this.dailyBudget;
    }

    public Boolean getUseDailyBudget() {
        return this.useDailyBudget;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }

    public void setDailyBudget(final Long dailyBudget) {
        this.dailyBudget = dailyBudget;
    }

    public void setUseDailyBudget(final Boolean useDailyBudget) {
        this.useDailyBudget = useDailyBudget;
    }
}
