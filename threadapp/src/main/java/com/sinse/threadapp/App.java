package com.sinse.threadapp;

import jdk.javadoc.internal.tool.Start;

public class App {
	public static void main(String[] args) {
	//하나의프로세스 내에서 각각 독립적으로 실행할 수 있는 단위인 스레드를 2개 생성하여 각각 별도로 작동하게 해보자.
		
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
		
		//스레드를 생성한다고 하여 OS가 관여하는게 아니라, start()로 밀어넣어야한다
	a.start();
	b.start();
		
	}
}
