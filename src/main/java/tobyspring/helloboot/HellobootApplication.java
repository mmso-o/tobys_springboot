package tobyspring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration // 구성정보를 가지고 있다는걸 명시하며 Configuration 이 스프링 정보 중 가장 먼저 등록됨
@ComponentScan // Component 어노테이션이 붙은 모든 클래스를 빈으로 등록한다.
public class HellobootApplication {
	public static void main(String[] args) {
		// 애플리케이션의 정보, 리소스 접근 정보 내부 이벤트 전달 및 구독 방법 등을 담고 있는 오브젝트
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();  // 생략하면 안됨

				TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();




	}
}