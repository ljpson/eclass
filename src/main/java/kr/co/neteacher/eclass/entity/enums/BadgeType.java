package kr.co.neteacher.eclass.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 학급 배지 타입
 */
@AllArgsConstructor
@Getter
public enum BadgeType {

    TYPE_1("type-1"),
    TYPE_2("type-2"),
    TYPE_3("type-3"),
    TYPE_4("type-4"),
    TYPE_5("type-5"),
    TYPE_CUSTOM("type-custom");

    private final String badgeTypeName;
}
