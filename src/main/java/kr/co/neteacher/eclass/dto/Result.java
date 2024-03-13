package kr.co.neteacher.eclass.dto;


import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import static java.util.Objects.nonNull;

/**
 * API JSON 결과 포맷 Entity
 *
 * @author dualcat
 */
public class Result extends LinkedHashMap<String, Object> {

    private static final Integer RESULT_SUCCESS = 200;
    private static final Integer RESULT_FAIL = 400;

    private static final String KEY_STATUS_CODE = "status_code";
    private static final String KEY_STATUS_MESSAGE = "status_message";
    private static final String KEY_DATA = "data";
    private static final String KEY_TIMESTAMP = "timestamp";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private Object data;
    private String message;

    public static Result success() {
        return buildResult(RESULT_SUCCESS);
    }

    public static Result successWithData(Object data) {
        return buildResult(RESULT_SUCCESS, data);
    }

    public static Result fail() {
        return buildResult(RESULT_FAIL);
    }

    public static Result failWithMessage(String message) {
        return buildResult(RESULT_FAIL, message);
    }

    public Result build() {

        // Message 값이 존재할 경우 기본값 덮어쓰기
        if (!ObjectUtils.isEmpty(message)) {
            this.put(KEY_STATUS_MESSAGE, message);
        }

        // Data 설정. null 일 경우 빈값('{}')
        if (nonNull(data)) {
            this.put(KEY_DATA, data);
        } else {
            this.put(KEY_DATA, null);
        }

        this.put(KEY_TIMESTAMP, getCurrentTime());

        // Sort
//        return (Result) this.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return this;
    }

    private static Result buildResult(Integer result) {
        Result returnResult = new Result();
        returnResult.put(KEY_STATUS_CODE, result);

        if (RESULT_SUCCESS.equals(result)) {
            returnResult.put(KEY_STATUS_MESSAGE, "Success");
        } else if (RESULT_FAIL.equals(result)) {
            returnResult.put(KEY_STATUS_MESSAGE, "Fail");
        }
        returnResult.put(KEY_TIMESTAMP, getCurrentTime());
        return returnResult;
    }

    private static Result buildResult(Integer result, Object data) {
        Result returnResult = buildResult(result);
        if (nonNull(data)) {
            if (data instanceof String) {
                returnResult.put(KEY_STATUS_MESSAGE, data);
            } else if (data instanceof LinkedHashMap) {
                ((LinkedHashMap) data).forEach((key, value) -> returnResult.put((String) key, value));
            } else {
                returnResult.put(KEY_DATA, data);
            }
        }

        return returnResult;
    }

    private static String getCurrentTime() {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }
}
