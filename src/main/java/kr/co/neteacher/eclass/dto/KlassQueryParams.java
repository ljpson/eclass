package kr.co.neteacher.eclass.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import kr.co.neteacher.eclass.entity.enums.GradeType;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class KlassQueryParams extends QueryParams {

    Long id;                //공통아이디
    Long teacherId;         //교사아이디
    Long klassId;           //학급아이디
    GradeType gradeType;    //학년
    String klassName;       //학급명
    String password;        //학생초기비밀번호
    String studentAmount;   //학생수

}
