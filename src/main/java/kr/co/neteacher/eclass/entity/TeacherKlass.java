package kr.co.neteacher.eclass.entity;

import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import kr.co.neteacher.eclass.entity.enums.TeacherType;

import javax.persistence.*;
import java.util.Date;

/**
 * 교사-학급 관계정보
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_teacher_klass")
public class TeacherKlass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;                //아이디
    private Long teacherId;           //교사아이디
    private Long klassId;           //학급아이디

    @Enumerated(value = EnumType.STRING)
    private TeacherType teacherType;    //교사유형
    @Enumerated(value = EnumType.STRING)
    private Yn submitYn;            //수락여부
    @Enumerated(value = EnumType.STRING)
    private Yn deleteYn;            //삭제여부

    private Long createBy;        //생성자
    private Date createDate;        //생성일자
    private Long updateBy;        //수정자
    private Date updateDate;        //수정일자

//    @ManyToOne(optional = false, fetch = LAZY)
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;           //교사아이디

//    @ManyToOne(optional = false, fetch = LAZY)
//    @JoinColumn(name = "klass_id")
//    private Klass klass$;            //학급아이디

}