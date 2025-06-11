package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StreamUtil {
	public static String getSecuredPass(String pwd) {
		// Java SE는 이미 알고리즘 함수를 보유하고 있다.
		String pass = pwd;
		StringBuffer sb = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] hash = md.digest(pass.getBytes("UTF-8"));

			// 잘게 쪼개진 바이트를 16진수 문자열로 변환하는 작업을 진행하여 보자
			System.out.println(hash.length);

			sb = new StringBuffer(); // String 의 불변적 특징을 해결한 객체이다.
			for (int i = 0; i < hash.length; i++) {
				// Integer에 byte 데이터를 16진수로 변경하는 과정에서, byte 안에 음수가 존재할 경우
				// 바이트 데이터 형이 int 형으로 변경되면서, 부호비트가 자동으로 붙게 된다.
				// 이 부호 비트는 암호화에 사용되지 않으므로, 제거해야한다.. 이 떄 제거하는 연산은
				// 16진수 0xff & 중 and 를 허용한다.
				// 참고로) 바이트 데이터가 int 형으로 변경되는 이유는 자바 언어에서
				String hex = Integer.toHexString(0xff & hash[i]); // 16진수 변환 함수
				// System.out.println(hex);
				if (hex.length() < 2)
					sb.append("0");
				sb.append(hex); // Stack of String

			}

			System.out.println(sb.toString());

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
