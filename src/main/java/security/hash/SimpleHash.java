package security.hash;

import security.KeyUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleHash {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 一段密碼
        String password = "1234";

        // 1. 獲取 SHA-256 消息摘要物件，幫助我們能生成哈希
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // 2. 生成哈希
        byte[] hashedBytes = messageDigest.digest(password.getBytes());

        System.out.println("原始密碼: " + password);

        // 將哈希轉 HEX(16進位) 字串
        String hashedString = KeyUtil.bytesToHex(hashedBytes);
        System.out.println("原始密碼轉哈希(HEX): " + hashedString);

    }
}
