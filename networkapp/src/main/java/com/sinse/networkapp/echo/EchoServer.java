package com.sinse.networkapp.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//접속한 클라이언트의 메시지를 그대로 보내주는 에코서버를 구축하여본다.
public class EchoServer {
	ServerSocket server; //이는 대화용 소켓이 아니라 어떤 유저가 접속하는지를 감지하는 감지용 소켓이다.
	
	public EchoServer() {
		try {
			server = new ServerSocket(9999);
			
			//접속자가 발생할 때까지 무한 대기 상태가 된다.
			Socket socket = server.accept();
			
			String ip = socket.getInetAddress().getHostAddress();
			System.out.println(ip + " 가 접속함");
			
			//소켓을 통해 데이터를 주고 받을 수 있는 스트림을 얻자.
			//방향에 따라 - 입력, 출력
			//데이터 처리 방식 - 바이트 stream , 문자 reader, writer,버퍼
			
			InputStream is = socket.getInputStream(); //byte 기반의 입력 스트림을 얻어온다.
			OutputStream os = socket.getOutputStream(); //byte 기반의 출력 스트림을 얻어온다.
			//무한 루프는 엄청난 속도이므로 프로그램에서 주의해야한다..
			//단 stream 처리에서는 read 메서드 자체가 상대방의 메시지를 받을 때까지는 대기상태에 빠지기 때문에 부하를 일으키지 않는다.
			while(true) {
				int data  = is.read(); //1byte만 읽을 수 있다. (입력) - 말을 듣
				
				System.out.print((char)data);
				os.write(data); //읽어들인 바이트 데이터를다시 보내버린다. (출력) - 말을 하는 것
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
