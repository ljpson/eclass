package kr.co.neteacher.eclass.entity;

import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 학생
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "klass_id")
    @Comment("학급 정보")
    private Klass klass;

    @Comment("출석 번호")
    private String attendanceNo;

    @Column(length = 255)
    @Comment("참조 정보")
    private String referenceInfo;

    @Comment("비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Comment("삭제 여부")
    private Yn deleteYn = Yn.N;

    @Enumerated(EnumType.STRING)
    @Comment("비밀번호 변경 여부")
    private Yn passwordChange = Yn.N;

    @Column(nullable = false)
    @Comment("등록일시")
    private LocalDateTime createDate = LocalDateTime.now();

    @Comment("수정자")
    private Long updateBy;

    @Comment("수정일시")
    private LocalDateTime updateDate;

    @Comment("스쿨타입+출석 번호")
    private String viewAttendanceNo;

}

