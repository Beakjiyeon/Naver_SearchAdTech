package util;

import exception.ErrorCode;
import exception.NapiException;
import kong.unirest.HttpResponse;
import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * API 통신 결과 처리 클래스.
 * 성공하면, 객체 리턴
 * 실패히면, NapiException
 */
public class ResponseUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 네이버 라이브러리 응답 결과에 따라 객체 또는 예외를 반환하는 메소드
     * @param o 네이버 라이브러리 응답 결과
     * @param type 요청한 객체의 유형 (캠페인/그룹/키워드/소재)
     * @return 요청한 객체
     * @throws NapiException 네이버 API 처리 과정에서의 예외
     */
    public static Object getResultFromRes(Object o, String type) throws NapiException {
        switch (type) {
        case "campaign": 
            HttpResponse<Campaign> campaignHttpResponse = (HttpResponse<Campaign>) o;
            if (campaignHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return campaignHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (campaignHttpResponse.getBody() == null) {
                    campaignHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + campaignHttpResponse.getStatus() + ", code : " + campaignHttpResponse.getBody().getCode() + ", title : " + campaignHttpResponse.getStatusText() + ", detail : " + campaignHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "group": 
            HttpResponse<Adgroup> adGroupHttpResponse = (HttpResponse<Adgroup>) o;
            if (adGroupHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return adGroupHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (adGroupHttpResponse.getBody() == null) {
                    adGroupHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + adGroupHttpResponse.getStatus() + ", code : " + adGroupHttpResponse.getBody().getCode() + ", title : " + adGroupHttpResponse.getStatusText() + ", detail : " + adGroupHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "keywords": 
            //HttpResponse<AdKeyword> adKeywordHttpResponse = (HttpResponse<AdKeyword>) o;
            HttpResponse<List<AdKeyword>> adKeywordHttpResponse = (HttpResponse<List<AdKeyword>>) o;
            if (adKeywordHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                List<AdKeyword> adKeywordArrayList = adKeywordHttpResponse.getBody();
                List<String> failedObject = new ArrayList<>();
                for (AdKeyword keyword : adKeywordArrayList) {
                    if (keyword.getResultStatus() != null) {
                        failedObject.add("{keyword : " + keyword.getKeyword() + ", code : " + keyword.getResultStatus().getCode() + ", message : " + keyword.getResultStatus().getMessage() + "}");
                    }
                    //throw new NapiException("{code : " + keyword.getResultStatus().getCode() + ", message : " + keyword.getResultStatus().getMessage() +"}" , ErrorCode.INVALID_CODE);
                }
                if (failedObject.size() >= 1) {
                    throw new NapiException(failedObject.toString(), ErrorCode.INVALID_CODE);
                }
                return adKeywordHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (adKeywordHttpResponse.getBody() == null) {
                    adKeywordHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    List<AdKeyword> adKeywordArrayList = adKeywordHttpResponse.getBody();
                    List<String> failedObject = new ArrayList<>();
                    for (AdKeyword keyword : adKeywordArrayList) {
                        if (keyword.getResultStatus() != null) {
                            failedObject.add("{keyword : " + keyword.getKeyword() + ", code : " + keyword.getCode() + ", title : " + keyword.getResultStatus().getMessage() + ", detail : " + keyword.getTitle() + "}");
                        }
                        //throw new NapiException("{code : " + keyword.getResultStatus().getCode() + ", message : " + keyword.getResultStatus().getMessage() +"}" , ErrorCode.INVALID_CODE);
                    }
                    if (failedObject.size() >= 1) {
                        throw new NapiException(failedObject.toString(), ErrorCode.INVALID_CODE);
                    }
                }
            }
            break;
        case "keyword": 
            //HttpResponse<AdKeyword> adKeywordHttpResponse = (HttpResponse<AdKeyword>) o;
            HttpResponse<AdKeyword> adKeywordHttpResponse2 = (HttpResponse<AdKeyword>) o;
            if (adKeywordHttpResponse2.isSuccess()) {
                log.debug("요청 성공");
                return adKeywordHttpResponse2.getBody();
            } else {
                log.error("요청 실패");
                if (adKeywordHttpResponse2.getBody() == null) {
                    adKeywordHttpResponse2.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    //adKeywordHttpResponse2.getBody().getCode()
                    //System.out.println(adKeywordHttpResponse2.getClass());
                    String message = "{status :" + adKeywordHttpResponse2.getStatus() + ", code : " + adKeywordHttpResponse2.getBody().getCode() + ", title : " + adKeywordHttpResponse2.getStatusText() + ", detail : " + adKeywordHttpResponse2.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "ad": 
            HttpResponse<Ad> adHttpResponse = (HttpResponse<Ad>) o;
            if (adHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return adHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (adHttpResponse.getBody() == null) {
                    adHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + adHttpResponse.getStatus() + ", code : " + adHttpResponse.getBody().getCode() + ", title : " + adHttpResponse.getStatusText() + ", detail : " + adHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "adp": 
            HttpResponse<PowerLinkAd> powerLinkAdHttpResponse = (HttpResponse<PowerLinkAd>) o;
            if (powerLinkAdHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return powerLinkAdHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (powerLinkAdHttpResponse.getBody() == null) {
                    powerLinkAdHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + powerLinkAdHttpResponse.getStatus() + ", code : " + powerLinkAdHttpResponse.getBody().getCode() + ", title : " + powerLinkAdHttpResponse.getStatusText() + ", detail : " + powerLinkAdHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "ads": 
            HttpResponse<ShoppingAd> shoppingAdHttpResponse = (HttpResponse<ShoppingAd>) o;
            if (shoppingAdHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return shoppingAdHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (shoppingAdHttpResponse.getBody() == null) {
                    shoppingAdHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + shoppingAdHttpResponse.getStatus() + ", code : " + shoppingAdHttpResponse.getBody().getCode() + ", title : " + shoppingAdHttpResponse.getStatusText() + ", detail : " + shoppingAdHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        case "adDto": 
            HttpResponse<AdDto> adDtoHttpResponse = (HttpResponse<AdDto>) o;
            if (adDtoHttpResponse.isSuccess()) {
                log.debug("요청 성공");
                return adDtoHttpResponse.getBody();
            } else {
                log.error("요청 실패");
                if (adDtoHttpResponse.getBody() == null) {
                    adDtoHttpResponse.getParsingError().ifPresent(e -> {
                        try {
                            throw new NapiException(e.getOriginalBody(), ErrorCode.INVALID_CODE);
                        } catch (NapiException napiException) {
                            napiException.printStackTrace();
                        }
                    });
                } else {
                    String message = "{status :" + adDtoHttpResponse.getStatus() + ", code : " + adDtoHttpResponse.getBody().getCode() + ", title : " + adDtoHttpResponse.getStatusText() + ", detail : " + adDtoHttpResponse.getBody().getTitle() + "}";
                    throw new NapiException(message, ErrorCode.INVALID_CODE);
                }
            }
            break;
        }
        return null;
    }
}
