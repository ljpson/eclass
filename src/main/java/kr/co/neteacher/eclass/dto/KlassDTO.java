package kr.co.neteacher.eclass.dto;

import kr.co.neteacher.eclass.entity.Teacher;
import kr.co.neteacher.eclass.entity.TeacherKlass;
import kr.co.neteacher.eclass.entity.enums.GradeType;
import kr.co.neteacher.eclass.entity.enums.TeacherType;
import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import kr.co.neteacher.eclass.entity.Klass;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class KlassDTO {

    // teacher
    private Long    teacherId;
    private String  neId;
    private String  teacherName;

    // klass
    private Long klassId;                //아이디
    private Long bookId;            //교과서아이디
    private GradeType gradeType;    //학년
    private String klassName;       //학급명
    private String password;        //학생초기비밀번호
    private Integer studentLimitNum; //학생수제한

    //teacherKlass
    private TeacherType teacherType;    //교사유형
    private Yn submitYn;            //수락여부

    // 객체 저장
    private Klass klass;            //학급정보
    private Teacher teacher;           //교사정보

    // 객체 리스트 저장
    private List<TeacherKlass> teacherKlasses; //교사학급목록
    private List<Teacher> teachers; //교사목록

    // 공통
    private Yn deleteYn;            //삭제여부
    private Long createBy;        //생성자
    private Date createDate;        //생성일자
    private Long updateBy;        //수정자
    private Date updateDate;        //수정일자
}
