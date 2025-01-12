package hello.springmvc;
// spring project setting할 때 패키징을 War가 아닌 Jar로 한 이유
// War: 톰캣 서버를 별도로 설치 하고 빌드된 파일을 넣을 때 사용, jsp 사용할 때
// Jar: 내장 톰캣 서버를 돌릴 때 사용. webapp 경로 사용하지 않음
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

}
