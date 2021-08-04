package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

import java.util.Date;
import java.util.Map;
/**
 * 타겟 정보 클래스
 * */
@Getter
@Setter
@NoArgsConstructor
public class Target {
    private String nccTargetId;
    private String ownerId;
    private String targetTp;
    private Map<String, Object> target;
    private Date regTm;
    private Date editTm;
    @Builder
    public Target(String nccTargetId, String ownerId, String targetTp, Map<String, Object> target){
        this.nccTargetId = nccTargetId;
        this.ownerId = ownerId;
        this.targetTp = targetTp;
        this.target = target;
    }
}
