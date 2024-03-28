package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 교육 레벨
 */
@AllArgsConstructor
@Getter
public enum EduType {

    MIDDLE("중등"),
    HIGH("고등");

    private final String eduTypeName;
}
