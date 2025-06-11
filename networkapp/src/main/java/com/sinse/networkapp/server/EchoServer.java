package com.sinse.networkapp.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//서버란, 토잇ㄴ의 양 당사자 중 접속을 기다리는 쪽이다.
//자바 se 소켓서버를 구축하려면 ServerSocket이라는 클래스를 이용한다.

public class EchoServer {
	ServerSocket server;
	
	public EchoServer() {
		try {
			server = new ServerSocket(9999);//0~1023 까지는 절대 사용하면 안됨!!! 이것은 시스템 점유 번호이다.
			System.out.println("서버객체 생성");
			
			//socket은 상대방의 정보를 가지고 있다. 
			Socket socket = server.accept(); //사용자의 접속을 청취하는 메서드, 접속이 발생할 때까지 무한정 대기상태에 빠진다.
			InetAddress addr = socket.getInetAddress();
			String ip = addr.getHostAddress();
			System.out.println(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
