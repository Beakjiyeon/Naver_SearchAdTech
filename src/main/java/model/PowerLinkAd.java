package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.AdTypes;
import lombok.*;

import java.util.Map;

/**
 * 파워링크 소재 클래스
 */
@Getter
@Setter
public class PowerLinkAd extends Ad{

    /* TND */
    private Map<String, Object> ad;

    /**
     * 파워링크 소재 생성 템플릿 메소드
     * @param nccAdgroupId 소재가 속할 그룹 ID
     * @param type 소재 유형
     * @param textNdescription 헤드라인, 설명, 링크 정보 객체
     */
    public PowerLinkAd(@NonNull String nccAdgroupId, @NonNull AdTypes type, @NonNull TextNdescription textNdescription){
        super(nccAdgroupId, type);
        ObjectMapper objectMapper = new ObjectMapper();
        Map ad = objectMapper.convertValue(textNdescription, Map.class);
        this.ad = ad;
    }
}
