package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.AdTypes;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 쇼핑 검색 소재 클래스
 */
@Getter
@Setter
public class ShoppingAd extends Ad{

    /* 그룹입찰가 사용여부, 개별 입찰가 */
    private Object adAttr;
    private String referenceKey;
    private Object referenceData;
    private Map<String, Object> ad;

    /**
     * 쇼핑검색 소재 생성 템플릿 메소드
     * @param nccAdgroupId 소재가 속할 그룹 ID
     * @param type 소재 유형
     * @param customerId 고객 ID
     * @param referenceKey 상품 참조 번호
     * @param useGroupBidAmt 그룹 입찰가 사용여부
     * @param bidAmt 개별입찰가
     * @param catalogType 카탈로그 소재의 카탈로그 유형
     */
    public ShoppingAd(@NonNull String nccAdgroupId, @NonNull AdTypes type, @NonNull Long customerId, @NonNull String referenceKey, @NonNull Boolean useGroupBidAmt, @NonNull Long bidAmt, @NonNull String catalogType){
        super(nccAdgroupId, type, customerId);
        this.referenceKey = referenceKey;
        Map<String, Object> temp = new HashMap<>();
        temp.put("useGroupBidAmt", useGroupBidAmt);
        temp.put("bidAmt", bidAmt);
        this.adAttr = temp;
        this.ad = new HashMap<>();
        this.ad.put("catalogType", catalogType); //"ad":{"catalogType":"BRAND"},
    }
}
