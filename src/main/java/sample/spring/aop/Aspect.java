package sample.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
	@Pointcut("execution(public void sample.spring.aop.Class.method())")
	public void pointcut() {}

	@Before("pointcut()")
	public void before() {
		System.out.println("***** @Before *****");
	}

	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("***** @AfterReturning *****");
	}

	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("***** @AfterThrowing *****");
	}

	@After("pointcut()")
	public void after() {
		System.out.println("***** @After *****");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("***** @Around: start *****");

		Object result = pjp.proceed();

		System.out.println("***** @Around: end *****");

		return result;
	}
}
