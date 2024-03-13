package kr.co.neteacher.eclass.controller.front;

import kr.co.neteacher.eclass.entity.Teacher;
import kr.co.neteacher.eclass.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {

    private HttpSession session;

    @Autowired
    private final TeacherRepository teacherRepository;

    /**
     * 메인 페이지
     * @return
     */
    @GetMapping("/")
    public String main(HttpServletRequest request, Model model) {

        if (session == null) {
            session = request.getSession();
            // 능률아이디 조회
            String neId = "NE00001";
            String teacherName = "이선생";
            // 교사정보 조회
            Optional<Teacher> teacher = teacherRepository.findByNeId(neId);
            if (!teacher.isPresent()) {
                Teacher saveTeacher = new Teacher()
                        .setNeId(neId)
                        .setTeacherName(teacherName);
                teacherRepository.save(saveTeacher);
                // 교사정보 조회(저장할 때 생성된 아이디 포함)
                teacher = teacherRepository.findByNeId(neId);
            }
            // 세션 저장
            session.setAttribute("teacher", teacher.get());
        }
        model.addAttribute("teacher",session.getAttribute("teacher"));

        return "views/main";
    }
}
