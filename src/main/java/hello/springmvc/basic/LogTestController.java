package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// @Controller: 반환값이 String이면 뷰 이름으로 인식됨. 뷰를 찾고 뷰를 렌더링시킴
// 메소드의 반환값(스트링) 그대로 http의 바디 데이터로 넘어감
@RestController
@Slf4j // lombok이 지원
public class LogTestController {
  // @Slf4j 어노테이션으로 대체 가능
//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    // 실무에서는 콘솔찍지 말고 로그찍기. 성능도 더 좋고 파일로 남길수도 있고.

    System.out.println("name: " + name);

    // 만약 trace level log를 안찍도록 해놨음.
    // 근데 이 코드가 있으면 더하기 연산을 함 -> 리소스 낭비
    // log.trace("trace log={}", name); 이렇게 하면 메소드에 파라미터를 넘기기만 하는거라 리소스 낭비가 없음.
    log.trace("trace log=" + name);

    log.trace("trace log={}", name);
    log.debug("debug log={}", name);
    log.info("info log={}", name);
    log.warn("warn={}", name);
    log.error(" error log={}", name);  // 시간, 컨트롤러이름, info log=Spring 이렇게 찍힘

    return "ok";
  }
}
