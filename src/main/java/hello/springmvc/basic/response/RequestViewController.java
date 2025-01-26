package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestViewController {

  @RequestMapping("/response-view-v1")
  public ModelAndView responseViewV1() {
    ModelAndView mav = new ModelAndView("response/hello")
            .addObject("data", "hello!");
    return mav;
  }

  @RequestMapping("/response-view-v2")
  public String responseViewV2(Model model) {
    model.addAttribute("data", "hello!");
    return "response/hello";
  }

  // controller의 경로와 view의 경로가 같으면 반환을 하지 않아도 스프링이 알아서 해당 경로로 가줌
  // 노 권장
  @RequestMapping("/response/hello")
  public void responseViewV3(Model model) {
    model.addAttribute("data", "hello!");
  }
}
