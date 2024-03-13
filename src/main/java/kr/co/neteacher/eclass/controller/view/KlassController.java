package kr.co.neteacher.eclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class KlassController {

    private HttpSession session;
    /**
     * 학급/학생 관리 메인
     * @return 학급목록
     */
    @GetMapping("/klass")
    public String klassList(HttpServletRequest request, Model model) {
        if (session == null) {
            session = request.getSession();
        }
        model.addAttribute("teacher", session.getAttribute("teacher"));
        return "views/klass/list";
    }

}
