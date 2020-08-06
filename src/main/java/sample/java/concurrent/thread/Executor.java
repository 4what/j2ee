package sample.java.concurrent.thread;

import java.util.concurrent.*;

public class Executor {


	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.
			//newSingleThreadExecutor()
			newCachedThreadPool()
		;

		//executorService.execute(new Runnable());

		Future<Object> future = executorService.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return "callback";
			}
		});

		System.out.println(future.get());
	}
}
