package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 교과서 개정년도
 */
@AllArgsConstructor
@Getter
public enum YearType {

    Y_2015("2015개정"),
    Y_2022("2022개정");

    private final String yearTypeName;
}
