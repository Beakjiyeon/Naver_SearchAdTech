package util;

import exception.ParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 노출기간 등록, 수정시 적합한 DATE 형태를 만들어주는 클래스
 */
public class DateFormat {

    /**
     * UTC 형태로 만들어주는 메소드
     * @param year 년도 (4자리)
     * @param month 달 (1 ~ 12)
     * @param day 일
     * @return UTC 형태 문자열
     * @throws ParameterException 유효하지 않은 날짜 입력 시 발생
     */
    public static String getUTCDate(int year, int month, int day) throws ParameterException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(year, month-1 , day);

        String result = dateFormat.format(cal.getTime());

        if(dateCheck(result)){
            return dateFormat.format(cal.getTime());
        }else{
            throw new ParameterException(ParameterException.ILLEGAL_DATE_PARAMETER_EXCEPTION);
        }
    }

    /**
     * 유효한 날짜인지 검사하는 메소드
     * @param dt 날짜
     * @return 유효여부
     */
    private static boolean dateCheck(String dt){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            format.setLenient(false);
            format.parse(dt);
        } catch (ParseException e) { return false; }
        catch ( IllegalArgumentException e ) { return false; }

        return true;
    }
}
