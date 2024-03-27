package kr.co.neteacher.eclass.controller.view;

import kr.co.neteacher.eclass.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@Controller
public class MainController {

    /**
     * 메인 페이지
     * @return
     */
    @GetMapping("/")
    public String root(@SessionAttribute("teacher") Teacher teacher, Model model) {

        if (teacher != null) {
            model.addAttribute("teacher",teacher);
            model.addAttribute("content", "views/klass/setting");
            return "views/main";
        } else {
            return "redirect:/main";
        }
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("content", "views/klass/setting");
        return "views/main";
    }

    @GetMapping("/menu1")
    public String menu1(Model model) {
        model.addAttribute("content", "views/deploy/list"); // main1.html을 렌더링하기 위한 content 변수 추가
        return "views/main"; // layout.html을 반환
    }

    @GetMapping("/menu2")
    public String menu2(Model model) {
        model.addAttribute("content", "views/deploy/main2"); // main2.html을 렌더링하기 위한 content 변수 추가
        return "views/main"; // layout.html을 반환
    }

    @GetMapping("/menu3")
    public String menu3(Model model) {
        model.addAttribute("content", "views/test/list"); // main3.html을 렌더링하기 위한 content 변수 추가
        return "views/main"; // layout.html을 반환
    }
}
