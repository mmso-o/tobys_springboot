package tobyspring.helloboot;

@MyComponent    // 메타 어노테이션을 이용한 커스텀 어노테이션 사용가능
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
