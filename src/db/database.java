package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class database {
	static BufferedReader br; // 클라이언트 입력	
	static BufferedWriter bw;
	static String response;
	static Socket socket;

	public static void main(String[] args)  {
		
		String sendMsg = "000062CN03BsaW10www.mcash.co.kr     kgtg        TEST_202203070000002";
		
		try {
			socket = new Socket("172.21.5.6", 9110); // 소켓 생성
			System.out.println(socket.getInetAddress() + " , " + socket.getPort());
			
			// 형변환
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(sendMsg);
			//bw.newLine();
			bw.flush();
			
			
			// 응답 처리
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 서버의 반환값을 읽음
			response = br.readLine();
			System.out.println("서버에서 온 응답 : " + response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
				try {
					socket.close();
					bw.close();
					br.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}