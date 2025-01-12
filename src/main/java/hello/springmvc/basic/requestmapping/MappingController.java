package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
  private final Logger log = LoggerFactory.getLogger(getClass());

  // 해당 url로 접속시 메소드 실행됨
  // {"/hello-basic", "/hello-go"} 이런식으로 배열로 넣으면 다중설정 가능
  // http method 지정안하면 method와 무관하게 호출됨
  @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
  public String helloBasic() {
    log.info("helloBasic");
    return "ok";
  }

  @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
  public String mappingGetV1() {
    log.info("mappingGetV1");
    return "ok";
  }

  @GetMapping(value = "/mapping-get-v2")
  public String mappingGetV2() {
    log.info("mappingGetV2");
    return "ok";
  }

  @GetMapping("/mapping/{userId}")
  //@PathVariable String userId : path variable 변수명과 메소드에서 받는 매개변수가 같으면 이렇게도 가능
  public String mappingPath(@PathVariable("userId") String data) {
    log.info("mappingPath userId={}", data);
    return "ok";
  }

  @GetMapping("/mapping/users/{userId}/orders/{orderId}")
  public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
    log.info("mappingPath userId={}, orderId={}", userId, orderId);
    return "ok";
  }

  // query string에 ?mode=debug 가 있어야 매핑됨
  @GetMapping(value = "/mapping-param", params = "mode=debug")
  public String mappingParam() {
    log.info("mappingParam");
    return "ok";
  }

  // 헤더에 해당 정보가 있어야 매핑됨
  @GetMapping(value = "/mapping-header", headers = "mode=debug")
  public String mappingHeader() {
    log.info("mappingHeader");
    return "ok";
  }

  // header의 요청 Content-Type이 application/json 일 때만 매핑. 서버에서는 application/json 타입만 소비가능
  @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String mappingConsume() {
    log.info("mappingConsume");
    return "ok";
  }

  // 헤더의 Accept 타입이 "text/html" 일 때만 매핑. 클라이언트에서 text/html만 accept, 응답할 때 text/html로 응답. 서버에서 생산해내는 타입
  @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
  public String mappingProduce() {
    log.info("mappingProduce");
    return "ok";
  }
}
