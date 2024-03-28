package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 학교급
 */
@AllArgsConstructor
@Getter
public enum SchoolType {

    M("중학교"),
    H("고등학교");

    private final String schoolTypeName;
}
