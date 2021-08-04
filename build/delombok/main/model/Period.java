package model;

import lombok.NonNull;

/**
 * 캠페인 노출 기간 등록, 수정 시 필요한 정보 클래스
 */
public class Period {
    private String nccCampaignId;
    private Boolean userLock;
    private String periodStartDt;
    private String periodEndDt;
    private Boolean usePeriod;

    public Period(@NonNull String nccCampaignId, @NonNull Boolean userLock, String periodStartDt, String periodEndDt, Boolean usePeriod) {
        if (nccCampaignId == null) {
            throw new NullPointerException("nccCampaignId is marked non-null but is null");
        }
        if (userLock == null) {
            throw new NullPointerException("userLock is marked non-null but is null");
        }
        this.nccCampaignId = nccCampaignId;
        this.userLock = userLock;
        this.periodStartDt = periodStartDt;
        this.periodEndDt = periodEndDt;
        this.usePeriod = usePeriod;
    }


    public static class PeriodBuilder {
        private String nccCampaignId;
        private Boolean userLock;
        private String periodStartDt;
        private String periodEndDt;
        private Boolean usePeriod;

        PeriodBuilder() {
        }

        public Period.PeriodBuilder nccCampaignId(@NonNull final String nccCampaignId) {
            if (nccCampaignId == null) {
                throw new NullPointerException("nccCampaignId is marked non-null but is null");
            }
            this.nccCampaignId = nccCampaignId;
            return this;
        }

        public Period.PeriodBuilder userLock(@NonNull final Boolean userLock) {
            if (userLock == null) {
                throw new NullPointerException("userLock is marked non-null but is null");
            }
            this.userLock = userLock;
            return this;
        }

        public Period.PeriodBuilder periodStartDt(final String periodStartDt) {
            this.periodStartDt = periodStartDt;
            return this;
        }

        public Period.PeriodBuilder periodEndDt(final String periodEndDt) {
            this.periodEndDt = periodEndDt;
            return this;
        }

        public Period.PeriodBuilder usePeriod(final Boolean usePeriod) {
            this.usePeriod = usePeriod;
            return this;
        }

        public Period build() {
            return new Period(this.nccCampaignId, this.userLock, this.periodStartDt, this.periodEndDt, this.usePeriod);
        }

        @Override
        public String toString() {
            return "Period.PeriodBuilder(nccCampaignId=" + this.nccCampaignId + ", userLock=" + this.userLock + ", periodStartDt=" + this.periodStartDt + ", periodEndDt=" + this.periodEndDt + ", usePeriod=" + this.usePeriod + ")";
        }
    }

    public static Period.PeriodBuilder builder() {
        return new Period.PeriodBuilder();
    }

    public String getNccCampaignId() {
        return this.nccCampaignId;
    }

    public Boolean getUserLock() {
        return this.userLock;
    }

    public String getPeriodStartDt() {
        return this.periodStartDt;
    }

    public String getPeriodEndDt() {
        return this.periodEndDt;
    }

    public Boolean getUsePeriod() {
        return this.usePeriod;
    }

    public void setNccCampaignId(final String nccCampaignId) {
        this.nccCampaignId = nccCampaignId;
    }

    public void setUserLock(final Boolean userLock) {
        this.userLock = userLock;
    }

    public void setPeriodStartDt(final String periodStartDt) {
        this.periodStartDt = periodStartDt;
    }

    public void setPeriodEndDt(final String periodEndDt) {
        this.periodEndDt = periodEndDt;
    }

    public void setUsePeriod(final Boolean usePeriod) {
        this.usePeriod = usePeriod;
    }
}
