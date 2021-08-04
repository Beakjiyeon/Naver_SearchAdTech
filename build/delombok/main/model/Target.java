package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.Date;
import java.util.Map;

/**
 * 타겟 정보 클래스
 */
public class Target {
    private String nccTargetId;
    private String ownerId;
    private String targetTp;
    private Map<String, Object> target;
    private Date regTm;
    private Date editTm;

    public Target(String nccTargetId, String ownerId, String targetTp, Map<String, Object> target) {
        this.nccTargetId = nccTargetId;
        this.ownerId = ownerId;
        this.targetTp = targetTp;
        this.target = target;
    }


    public static class TargetBuilder {
        private String nccTargetId;
        private String ownerId;
        private String targetTp;
        private Map<String, Object> target;

        TargetBuilder() {
        }

        public Target.TargetBuilder nccTargetId(final String nccTargetId) {
            this.nccTargetId = nccTargetId;
            return this;
        }

        public Target.TargetBuilder ownerId(final String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Target.TargetBuilder targetTp(final String targetTp) {
            this.targetTp = targetTp;
            return this;
        }

        public Target.TargetBuilder target(final Map<String, Object> target) {
            this.target = target;
            return this;
        }

        public Target build() {
            return new Target(this.nccTargetId, this.ownerId, this.targetTp, this.target);
        }

        @Override
        public String toString() {
            return "Target.TargetBuilder(nccTargetId=" + this.nccTargetId + ", ownerId=" + this.ownerId + ", targetTp=" + this.targetTp + ", target=" + this.target + ")";
        }
    }

    public static Target.TargetBuilder builder() {
        return new Target.TargetBuilder();
    }

    public String getNccTargetId() {
        return this.nccTargetId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getTargetTp() {
        return this.targetTp;
    }

    public Map<String, Object> getTarget() {
        return this.target;
    }

    public Date getRegTm() {
        return this.regTm;
    }

    public Date getEditTm() {
        return this.editTm;
    }

    public void setNccTargetId(final String nccTargetId) {
        this.nccTargetId = nccTargetId;
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setTargetTp(final String targetTp) {
        this.targetTp = targetTp;
    }

    public void setTarget(final Map<String, Object> target) {
        this.target = target;
    }

    public void setRegTm(final Date regTm) {
        this.regTm = regTm;
    }

    public void setEditTm(final Date editTm) {
        this.editTm = editTm;
    }

    public Target() {
    }
}
