package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 학년 유형
 */
@AllArgsConstructor
@Getter
public enum GradeType {
    MIDDLE_1("중학교 1학년"),
    MIDDLE_2("중학교 2학년"),
    MIDDLE_3("중학교 3학년"),
    HIGH_1("고등학교 1학년"),
    HIGH_2("고등학교 2학년"),
    HIGH_3("고등학교 3학년");

    private final String typeName;
}
