package kr.co.neteacher.eclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    /**
     * 평가자료 메인
     * @return 교과서목록
     */
    @GetMapping("/test")
    public String testList() {
        return "views/test/list";
    }
}
