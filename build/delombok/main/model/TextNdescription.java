package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.NonNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 헤드라인, 설명, 랜딩 URL - 파워 링크 소재 클래스
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TextNdescription {
    private Map<String, String> pc;
    private Map<String, String> mobile;
    private String headline;
    private String description;

    public TextNdescription(@NonNull String pcUrl, @NonNull String mobileUrl, @NonNull String headline, @NonNull String description) {
        if (pcUrl == null) {
            throw new NullPointerException("pcUrl is marked non-null but is null");
        }
        if (mobileUrl == null) {
            throw new NullPointerException("mobileUrl is marked non-null but is null");
        }
        if (headline == null) {
            throw new NullPointerException("headline is marked non-null but is null");
        }
        if (description == null) {
            throw new NullPointerException("description is marked non-null but is null");
        }
        this.pc = new HashMap<String, String>();
        this.mobile = new HashMap<String, String>();
        this.pc.put("final", pcUrl);
        this.mobile.put("final", mobileUrl);
        this.headline = headline;
        this.description = description;
    }
}
