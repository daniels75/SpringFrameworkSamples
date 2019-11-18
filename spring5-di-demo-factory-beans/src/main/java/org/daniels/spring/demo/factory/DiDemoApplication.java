package org.daniels.spring.demo.factory;

import org.daniels.spring.demo.factory.controllers.ConstructorInjectedController;
import org.daniels.spring.demo.factory.controllers.GetterInjectedController;
import org.daniels.spring.demo.factory.controllers.MyController;
import org.daniels.spring.demo.factory.controllers.PropertyInjectedController;
import org.daniels.spring.demo.factory.example.FakeDatasource;
import org.daniels.spring.demo.factory.example.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

		MyController controller = (MyController) ctx.getBean("myController");

		System.out.println(controller.hello());
		System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
		System.out.println(ctx.getBean(GetterInjectedController.class).sayHello());
		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());

		FakeDatasource fakeDatasource = (FakeDatasource)ctx.getBean(FakeDatasource.class);
		System.out.println(String.format("user: %s passwd: %s url: %s", fakeDatasource.getUsername(), fakeDatasource.getPassword(), fakeDatasource.getUrl()));

		System.out.println(String.format("url from env: %s", fakeDatasource.getUrlFromEnv()));

		System.out.println("==================================");
		FakeJmsBroker fakeJmsBroker = ctx.getBean(FakeJmsBroker.class);
		System.out.println(String.format("user: %s passwd: %s url: %s",
				fakeJmsBroker.getUsername(), fakeJmsBroker.getPassword(), fakeJmsBroker.getUrl()));


	}
}
