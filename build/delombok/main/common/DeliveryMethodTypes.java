package common;

/**
 * 예산 소진 방식 유형 (ACCELERATED, STANDARD)
 */
public enum DeliveryMethodTypes {

    ACCELERATED("ACCELERATED"),
    STANDARD("STANDARD");

    private String deliveryMethodType;

    DeliveryMethodTypes(String deliveryMethodType){
        this.deliveryMethodType = deliveryMethodType;
    }
}
