package com.sinse.threadapp;


/*
 * 개발자는 하나의 자바프로그램 내에서 비동기적으로 동시에 실행하고 싶은 코드가 있다면
 * 스레드로 정의하면 된다.
 * 이 때 코드는 쓰레드의 run() 메서드에 작성해야한다.
 * 이run 메서드는 개발자가 절대로 호출해서는 안된다.
 * run은 os가 해당쓰레드를 스케줄러에 의해 선택한 순간 JVM을 호출하는 것이기 때문에 만일 개발자가run을 호출하면 
 * OS와 JVM이 관여하 않습니다.
 */

public class ThreadA extends Thread{
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("A");
		}
	}
}
