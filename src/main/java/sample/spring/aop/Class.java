package sample.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Class {
	public void method() {
		System.out.println("Image.method()");

		//System.out.println(0 / 0);
	}
}
