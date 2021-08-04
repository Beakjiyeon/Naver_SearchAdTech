package model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * PC, MOBILE 링크 클래스
 * */
@Getter
public class Link {
    Map<String,String> pc;
    Map<String,String> mobile;
    public Link(String pcUrl, String mobileUrl){
        this.pc = new HashMap<>();
        this.mobile = new HashMap<>();
        this.pc.put("final", pcUrl);
        this.mobile.put("final", mobileUrl);
    }
}
