package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		// 애플리케이션의 정보, 리소스 접근 정보 내부 이벤트 전달 및 구독 방법 등을 담고 있는 오브젝트
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		// 클래스 정보만 넘긴다.
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		// 가지고 있는 구성정보로 컨테이너를 초기화하기 위해서 refresh 호출
		applicationContext.refresh();


		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
					new DispatcherServlet(applicationContext) // DispatcherSevlet 에 컨테이너 정보들을 넘겨줘야하는데 applicationContext를 사용한다.
			).addMapping("/*");	// 모든 요청을 다 처리한다.
		});
		webServer.start();

	}
}