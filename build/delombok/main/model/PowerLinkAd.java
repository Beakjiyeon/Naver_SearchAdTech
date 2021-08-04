package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.AdTypes;
import lombok.*;
import java.util.Map;

/**
 * 파워링크 소재 클래스
 */
public class PowerLinkAd extends Ad {
    /* TND */
    private Map<String, Object> ad;

    /**
     * 파워링크 소재 생성 템플릿 메소드
     * @param nccAdgroupId 소재가 속할 그룹 ID
     * @param type 소재 유형
     * @param textNdescription 헤드라인, 설명, 링크 정보 객체
     */
    public PowerLinkAd(@NonNull String nccAdgroupId, @NonNull AdTypes type, @NonNull TextNdescription textNdescription) {
        super(nccAdgroupId, type);
        if (nccAdgroupId == null) {
            throw new NullPointerException("nccAdgroupId is marked non-null but is null");
        }
        if (type == null) {
            throw new NullPointerException("type is marked non-null but is null");
        }
        if (textNdescription == null) {
            throw new NullPointerException("textNdescription is marked non-null but is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map ad = objectMapper.convertValue(textNdescription, Map.class);
        this.ad = ad;
    }

    public Map<String, Object> getAd() {
        return this.ad;
    }

    public void setAd(final Map<String, Object> ad) {
        this.ad = ad;
    }
}
