package kr.co.neteacher.eclass.config;

import kr.co.neteacher.eclass.entity.Teacher;
import kr.co.neteacher.eclass.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class InterceptorConfig implements HandlerInterceptor {

    @Autowired
    private TeacherService teacherService;

    /**
     * 세션에서 teacher 조회
     * 없으면 쿠키에서 C_USERID 조회
     * C_USERID가 있으면 해당 값 복호화
     * 복호화 된 데이터로 NE_TEACHER, NE_MEMBERSHIP 테이블 조회
     * 해당 값으로 eclass teacher 조회
     * 없으면 새로운 데이터 생성 후 세션 저장
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();

        // 세션에 teacher 정보가 없을 때
        if (session.getAttribute("teacher") == null || session.isNew()) {

            // 개발용 임시 세팅
            String neId = "dualcat";
            String teacherName = "듀얼캣";

            // 선생님 정보 가져오기
            Optional<Teacher> teacher = teacherService.getTeacher(neId, teacherName);
            // 세션 저장
            session.setAttribute("teacher", teacher.get());
        }
        return true;
    }
}
