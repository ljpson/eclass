package kr.co.neteacher.eclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvalController {

    /**
     * 평가자료 메인
     * @return 교과서목록
     */
    @GetMapping("/eval")
    public String main(Model model) {
        model.addAttribute("content", "views/eval/list");
        return "views/main";
    }
}
