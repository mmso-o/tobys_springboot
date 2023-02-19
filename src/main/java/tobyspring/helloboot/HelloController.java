package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/myapp")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @ResponseBody // 디폴트로는 스트링으로 리턴 시 스트링 이름의 view 가 존재하는지 찾는다. ResponseBody 어노테이션 존재 시 그대로 리턴한다.
    @GetMapping("/hello")
    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
