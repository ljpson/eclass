package kr.co.neteacher.eclass.entity;

import kr.co.neteacher.eclass.entity.enums.GradeType;
import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 학급 정보
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_klass")
public class Klass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;                //아이디
    private Long bookId;            //교과서아이디

    @Enumerated(value = EnumType.STRING)
    private GradeType gradeType;    //학년

    private String klassName;       //학급명
    private String password;        //학생초기비밀번호
    private Integer studentLimitNum; //학생수제한

    @Enumerated(value = EnumType.STRING)
    private Yn deleteYn;            //삭제여부

    private Long createBy;        //생성자
    private Date createDate;        //생성일자
    private Long updateBy;        //수정자
    private Date updateDate;        //수정일자

//    @OneToMany(mappedBy = "klass$", cascade = CascadeType.ALL)
//    private List<TeacherKlass> teacherKlasses;


}