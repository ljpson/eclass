package kr.co.neteacher.eclass.controller.api;

import kr.co.neteacher.eclass.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
public class DefaultRestController {
    private final Marker MARKER_API = MarkerFactory.getMarker("[API]");
    private static final String HEADER_TOTAL_COUNT = "X-Total-Count";
    private static final String HEADER_LIMIT = "X-Limit";

    /**
     * 기본 요청 성공 응답
     *
     * @return ResponseEntity 요청결과
     */
    DeferredResult<ResponseEntity<?>> successResponse() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        result.setResult(ResponseEntity.ok(Result.success()));
        return result;
    }

    /**
     * 결과 객체 포함 요청 성공 응답
     *
     * @param o 결과 객체
     * @return ResponseEntity 요청결과
     */
    DeferredResult<ResponseEntity<?>> successResponse(Object o) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        result.setResult(ResponseEntity.ok(Result.success().data(o).build()));
        return result;
    }

    /**
     * 페이저블 객체 포함 요청 성공 응답
     *
     * @param pageableObject 페이저블 객체
     * @return ResponseEntity 요청결과
     */
    DeferredResult<ResponseEntity<?>> successResponse(Page<?> pageableObject) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_TOTAL_COUNT, Long.toString(pageableObject.getTotalElements()));
        headers.add(HEADER_LIMIT, Integer.toString(pageableObject.getSize()));

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        result.setResult(new ResponseEntity<>(Result.success().data(pageableObject.getContent()).build(), headers, HttpStatus.OK));

        return result;
    }

    /**
     * 기본 요청 실패 응답
     *
     * @param t Throw 객체
     * @return ResponseEntity 요청결과
     */
    DeferredResult<ResponseEntity<?>> failResponse(Throwable t) {
        log.error(MARKER_API, "Error: ", t);

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        result.setErrorResult(ResponseEntity.ok().body(Result.failWithMessage(t.getMessage())));
        return result;
    }

    /**
     * 메시지 포함 요청 실패 응답
     *
     * @param msg 실패 메시지
     * @return ResponseEntity 요청결과
     */
    DeferredResult<ResponseEntity<?>> failResponse(String msg) {
        log.error(MARKER_API, "Error: {}", msg);

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        result.setErrorResult(ResponseEntity.ok().body(Result.failWithMessage(msg)));
        return result;
    }
}
