package kr.co.neteacher.eclass.entity;

import kr.co.neteacher.eclass.entity.enums.BadgeType;
import kr.co.neteacher.eclass.entity.enums.SchoolType;
import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 학급
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_klass")
public class Klass {

    @Id
    @Comment("학급 아이디")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @Comment("개설 선생님 정보")
    private Teacher teacher;

    @Enumerated(value = EnumType.STRING)
    @Comment("학교 유형")
    @Column(length = 1)
    private SchoolType schoolType;

    @Enumerated(value = EnumType.STRING)
    @Comment("배지 유형")
    @Column(length = 11)
    private BadgeType badgeType = BadgeType.TYPE_1;

    @Comment("배지 이미지 경로")
    @Column(length = 255)
    private String badgeImagePath;

    @Comment("학년")
    @Column(length =1)
    private String grade;

    @Comment("반")
    @Column(length = 2)
    private String ban;

    @Comment("학급명")
    @Column(length = 100, nullable = false)
    private String klassName;

    @Comment("학급명(별칭)")
    @Column(length = 100)
    private String klassNickName;

    @Comment("학급 학생 수")
    private Integer studentAmount = 0;

    @Comment("삭제여부")
    @Enumerated(value = EnumType.STRING)
    private Yn deleteYn = Yn.N;

    @Comment("생성자")
    @Column(nullable = false, updatable = false)
    private Long createBy;

    @Comment("생성일시")
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();;

    @Comment("수정자")
    private Long updateBy;

    @Comment("수정일시")
    private LocalDateTime updateDate;

}