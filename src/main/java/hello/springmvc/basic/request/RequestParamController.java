package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username={}, age={}", username, age);

    response.getWriter().write("ok");
  }

  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(@RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge) {
    log.info("username={}, age={}", memberName, memberAge);
    return "ok";

  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(@RequestParam String username,
                               @RequestParam int age) {
    log.info("username={}, age={}", username, age);
    return "ok";

  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  // @RequestParam 안적어줘도 됨. 그대신 요청파라미터의 키 값과 매개변수명이 같아야함
  // primitive type일 경우만 해당
  public String requestParamV4(String username, int age) {
    log.info("username={}, age={}", username, age);
    return "ok";

  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  // required = true가 디폴트.
  // 클라이언트에서 username이 안넘어오면 에러남(400).
  // username= 이렇게 보내면 빈문자("")가 넘어가는너임. 괜춘. 아예 username 조차 안보내면(null) 에러
  // 프론트가 http 요청 파라미터 스펙 잘 맞춰줘야지

  // age를 안보냈을 때(null) age 가 int type이면 500에러남. int는 null값 못가지니까. Integer 타입이여야함
  public String requestParamRequired(@RequestParam(required = true) String username,
                                     @RequestParam(required = false) Integer age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  // 빈문자가 들어와도 디폴트값이 적용됨
  public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                     @RequestParam(defaultValue = "-1") Integer age) {
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  // 요청 파라미터를 한꺼번에 map으로 받아옴
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/modal-attribute-v1")
  // @ModelAttribute가 있으면 HelloData 객체를 생성
  // -> HelloData 객채의 프로퍼티(username, age)를 찾음
  // -> 해당 프로퍼티의 세터를 호출해서 파라미터의 값을 넣음
  public String modalAttributeV1(@ModelAttribute HelloData helloData) {
    log.info("username={} age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @RequestMapping("/modal-attribute-v2")
  // @ModelAttribute 생략가능
  // 단순 타입(String, int, Integer) 같은 경우는 스프링이 @RequestParam이 생략됐구나 생각하고
  // 나머지(ex.내가 만든 클래스)는 @ModelAttribute로 적용
  public String modalAttributeV2(HelloData helloData) {
    log.info("username={} age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }
}
