package kr.co.neteacher.eclass.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeployController {

    /**
     * 학급평가함 메인
     * @return 배포한 평가목록
     */
    @GetMapping("/deploy")
    public String deployList() {
        return "views/deploy/list";
    }
}
