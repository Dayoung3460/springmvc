package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
  @PostMapping("/request-body-string-v1")
  public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody={}", messageBody);
    response.getWriter().write("ok");
  }

  @PostMapping("/request-body-string-v2")
  // InputStream: http request message body 내용을 직접 조회 가능
  // Writer: http response message body에 직접 결과 출력 가능
  public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody={}", messageBody);
    responseWriter.write("ok");
  }

  @PostMapping("/request-body-string-v3")
  // HttpEntity: http header, body 정보를 편리하게 조회 가능
  //            응답에도 사용가능
  // 요청파라미터(@RequestParam, @ModelAttribute)와는 상관없음
  // GET query parameter, POST HTTP Form 으로 보낼때만 @RequestParam, @ModelAttribute 사용
  public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
    String messageBody = httpEntity.getBody();
    log.info("messageBody={}", messageBody);
    return new HttpEntity<>("ok");
  }

  @ResponseBody
  @PostMapping("/request-body-string-v4")
  // request parameter 외에 http message body 직접 조회할 땐 @RequestBody
  public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
    log.info("messageBody={}", messageBody);
    return "ok";
  }
}
