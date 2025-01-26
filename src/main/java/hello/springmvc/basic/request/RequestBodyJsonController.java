package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody={}", messageBody);
    // messageBody를 HelloData.class 타입으로 읽어들이겠다
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    response.getWriter().write("ok");
  }

  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
    log.info("messageBody={}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v3")
  // http message converter가 메세지 바디의 내용을 객체로 변환해줌
  // ** http 요청시에 content-type이 application/json이어야함
  // ObjectMapper가 하는일을 대신해줌
  // @RequestBody 생략불가: 생략하면 스프링이 @ModelAttribute로 생각함
  //     요청파라미터로 넘어온건 없기 때문에 helloData.getUsername(), helloData.getAge() null로 나옴
  public String requestBodyJsonV3(@RequestBody HelloData helloData){
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v4")
  public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
    HelloData helloData = httpEntity.getBody();
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v5")
  public HelloData requestBodyJsonV5(@RequestBody HelloData helloData){
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    // 반환도 http message converter 동작하면서 객체가 json으로 변경
    return helloData;
  }
}
