package kr.co.neteacher.eclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    /**
     * 리포트 메인
     * @return 리포트목록
     */
    @GetMapping("/report")
    public String main(Model model) {

        // 페이지 부분변경
        model.addAttribute("content", "views/report/report1");
        return "views/main";
    }
}
