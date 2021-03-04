package org.daniels.springframework;

import org.daniels.springframework.controllers.ConstructorInjectedController;
import org.daniels.springframework.controllers.GetterInjectedController;
import org.daniels.springframework.controllers.MyController;
import org.daniels.springframework.controllers.PropertyInjectedController;
import org.daniels.springframework.controllers.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) throws UnknownHostException {
		ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

		MyController controller1 = (MyController) ctx.getBean("myController");

		System.out.println("MyController instance: " + controller1.getSimpleBean());

		//System.out.println(controller1.hello());
		//System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
		//System.out.println(ctx.getBean(GetterInjectedController.class).sayHello());
		//System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());

		MyController controller2 = (MyController) ctx.getBean("myController");
		System.out.println("MyController instance: " + controller2.getSimpleBean());

		System.out.println("hostname: " + InetAddress.getLocalHost().getHostName());
	}
}
