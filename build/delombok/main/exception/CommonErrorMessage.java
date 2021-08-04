package exception;

/**
 * 파라미터 예외 메세지 반환 클래스
 */
public class CommonErrorMessage {

    public String getMessage(int errorCode) {
        String resultMessage = ""; // StringUtils.EMPTY;
        switch (errorCode) {

            /* 변수 관련 에러 처리 */

            case ParameterException.PARAMETER_EXCEPTION:
                resultMessage = "변수에 문제가 있습니다 .";
                break;

            case ParameterException.NULL_PARAMETER_EXCEPTION:
                resultMessage = "변수가 누락되었습니다 .";
                break;

            case ParameterException.NULL_NAME_PARAMETER_EXCEPTION:
                resultMessage = "이름 정보가 누락되었습니다 .";
                break;

            case ParameterException.NULL_CAMPAIGN_TP_PARAMETER_EXCEPTION:
                resultMessage = "캠페인 유형 정보가 누락되었습니다 .";
                break;

            case ParameterException.NULL_CUSTOMER_ID_PARAMETER_EXCEPTION:
                resultMessage = "고객 ID 정보가 누락되었습니다 .";
                break;

            case ParameterException. ILLEGAL_CAMPAIGN_NAME_PARAMETER_EXCEPTION:
                resultMessage = "캠페인 이름은 1자 이상 30자 이하입니다 .";
                break;

            case ParameterException.ILLEGAL_CAMPAIGN_NAME_PATTERN_PARAMETER_EXCEPTION:
                resultMessage = "캠페인 이름은 한글/영어/숫자/공백/특수문자(_, #, -, .) 를 허용합니다 .";
                break;

            case ParameterException.ILLEGAL_POWERLINK_DAILY_BUDGET_PARAMETER_EXCEPTION:
                resultMessage = "파워링크의 하루 예산은 70~100000000 범위를 허용합니다 . (단위 : 10)";
                break;

            case ParameterException.ILLEGAL_SHOPPING_DAILY_BUDGET_PARAMETER_EXCEPTION:
                resultMessage = "쇼핑검색의 하루 예산은 50~100000000 범위를 허용합니다 . (단위 : 10)";
                break;

            case ParameterException.NULL_TRACKING_URL_PARAMETER_EXCEPTION:
                resultMessage = "PASS_THROUGH_SITE_MODE 추적 유형은 선택 시, 추적 URL이 꼭 필요합니다 . (다른 추적 모드는 URL이 적용되지 않습니다.)";
                break;

            case ParameterException.ILLEGAL_URL_PARAMETER_EXCEPTION:
                resultMessage = "유효하지 않은 URL 입니다.";
                break;

            case ParameterException.NULL_CAMPAIGN_ID_PARAMETER_EXCEPTION:
                resultMessage = "캠페인 ID 가 누락되었습니다.";
                break;

            case ParameterException.NULL_USERLOCK_PARAMETER_EXCEPTION:
                resultMessage = "활성화 여부가 누락되었습니다.";
                break;

            case ParameterException.NULL_ADGROUP_TYPE_PARAMETER_EXCEPTION:
                resultMessage = "그룹 유형이 누락되었습니다.";
                break;

            case ParameterException.NULL_PC_CHANNEL_ID_PARAMETER_EXCEPTION:
                resultMessage = "PC 채널 ID가 누락되었습니다.";
                break;

            case ParameterException.ILLEGAL_BID_AMT_PARAMETER_EXCEPTION :
                resultMessage = "그룹 기본 입찰가  70~100000000 범위를 허용합니다.";
                break;

            case ParameterException.ILLEGAL_GROUP_NAME_PARAMETER_EXCEPTION:
                resultMessage = "그룹 이름은 1자 이상 30자 이하입니다 .";
                break;

            case ParameterException.ILLEGAL_PC_BID_WEIGHT_PARAMETER_EXCEPTION :
                resultMessage = "PC 플랫폼 입찰 가중치는 10 ~ 500 범위를 허용합니다.";
                break;

            case ParameterException.ILLEGAL_MOBILE_BID_WEIGHT_PARAMETER_EXCEPTION:
                resultMessage = "Mobile 플랫폼 입찰 가중치는 10 ~ 500 범위를 허용합니다.";
                break;

            case ParameterException.ILLEGAL_KEYWORD_NAME_PARAMETER_EXCEPTION:
                resultMessage = "키워드는 1자 이상 25자 이하를 허용합니다. (공백문자는 글자 수로 세지 않음)";
                break;

            case ParameterException.NULL_ADGROUP_ID_PARAMETER_EXCEPTION:
                resultMessage = "그룹 ID 가 누락되었습니다.";
                break;

            case ParameterException.NULL_AD_TYPE_PARAMETER_EXCEPTION:
                resultMessage = "소재 타입이 누락되었습니다.";
                break;

            case ParameterException.ILLEGAL_DESCRIPTION_PARAMETER_EXCEPTION:
                resultMessage = "소재 설명은 20자 이상 45자 이하여야 합니다.";
                break;

            case ParameterException.ILLEGAL_HEADLINE_PARAMETER_EXCEPTION:
                resultMessage = "소재 헤드라인은 1자 이상 15자 이하여야 합니다.";
                break;

            case ParameterException.NULL_TND_PARAMETER_EXCEPTION:
                resultMessage = "파워링크 소재는 헤드라인, 디스크립션, PC 연결 URL, Mobile 연결 URL 정보가 반드시 필요합니다.";
                break;

            case ParameterException.ILLEGAL_MAP_PARAMETER_EXCEPTION:
                resultMessage = "Map에 'final' 키가 누락되었습니다. ";
                break;

            case ParameterException.NULL_SHOPPING_SEARCH_PARAMETER_EXCEPTION:
                resultMessage = "쇼핑검색 등록시 소재 유형, 그룹 입찰가 사용여부, 기본입찰가 정보가 반드시 필요합니다. ";
                break;

            case ParameterException.ILLEGAL_DAILY_BUDGET_PARAMETER_EXCEPTION:
                resultMessage = "하루예산은 10원단위로 입력하셔야 합니다. ";
                break;

            case ParameterException.ILLEGAL_SHOPPING_BID_AMT_PARAMETER_EXCEPTION:
                resultMessage = "쇼핑소재 개별 입찰가 범위는 최소 50 이상입니다. ";
                break;

            case ParameterException.NULL_SHOPPING_AD_PARAMETER_EXCEPTION:
                resultMessage = "쇼핑 소재 등록, 수정시 그룹입찰가 사용여부, 개별입찰가 를 반드시 입력해야 합니다. ";
                break;

            case ParameterException.NULL_AD_ID_PARAMETER_EXCEPTION:
                resultMessage = "소재 ID 정보가 누락되었습니다. ";
                break;

            case ParameterException.NULL_INSPECT_MSG_PARAMETER_EXCEPTION:
                resultMessage = "검토요청 메세지 정보가 누락되었습니다. ";
                break;

            case ParameterException.ILLEGAL_DATE_PARAMETER_EXCEPTION:
                resultMessage = "유효하지 않은 날짜입니다. ";
                break;

            case ParameterException.NULL_END_DATE_PARAMETER_EXCEPTION:
                resultMessage = "노출 시작일을 미래로 설정할 경우, 종료일 정보가 반드시 필요합니다.";
                break;

            case ParameterException.ILLEGAL_START_DATE_PARAMETER_EXCEPTION:
                resultMessage = "시작일이 종료일보다 미래기간이면 안됩니다. ";
                break;

            case ParameterException.ILLEGAL_USE_PERIOD_PARAMETER_EXCEPTION:
                resultMessage = "노출기한 제한여부(usePeriod) 변수가 유효하지 않습니다. 입력값을 반전(!) 하세요. ";
                break;

            case ParameterException.NULL_USE_DAILY_BUDGET_PARAMETER_EXCEPTION:
                resultMessage = "하루예산 설정 시, useDailyBudget 필드를 설정할 수 없습니다. 하루예산 '제한없음'으로 설정을 원하는 경우 dailyBudget=0L 로 입력하세요. ";
                break;

            case ParameterException.NULL_DAILY_BUDGET_PARAMETER_EXCEPTION:
                resultMessage = "하루예산 정보가 누락되었습니다. ";
                break;

            case ParameterException.NONNULL_ADGROUP_TYPE_PARAMETER_EXCEPTION:
                resultMessage = "그룹 등록, 수정 시 그룹 유형을 지정할 수 없습니다. 캠페인의 유형과 동일하게 적용됩니다. ";
                break;

            case ParameterException.NULL_PC_NETWORK_BID_AMT_PARAMETER_EXCEPTION:
                resultMessage = "PC 입찰가 가중치가 누락되었습니다. ";
                break;

            case ParameterException.NULL_MOBILE_NETWORK_BID_AMT_PARAMETER_EXCEPTION:
                resultMessage = "Mobile 입찰가 가중치가 누락되었습니다. ";
                break;
        }

        return resultMessage;
    }

}
