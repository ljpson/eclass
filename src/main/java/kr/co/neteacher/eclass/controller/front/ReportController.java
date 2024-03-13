package kr.co.neteacher.eclass.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    /**
     * 리포트 메인
     * @return 리포트목록
     */
    @GetMapping("/report")
    public String testList() {
        return "views/report/report1";
    }
}
