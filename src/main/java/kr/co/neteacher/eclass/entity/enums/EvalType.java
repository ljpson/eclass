package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 평가 유형
 */
@AllArgsConstructor
@Getter
public enum EvalType {

    VOCABULARY("단어 평가"),
    READING("문장 평가"),
    WRITING("쓰기 평가"),
    PRESENTATION("제출 평가"),
    UNIT("단원별 종합평가");

    private final String evalTypeName;
}
