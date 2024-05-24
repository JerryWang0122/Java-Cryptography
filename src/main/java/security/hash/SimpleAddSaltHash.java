package security.hash;

import security.KeyUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

// 加鹽 Hash
public class SimpleAddSaltHash {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 1. 設定一段密碼
        String password = "1234";

        // 2. 隨機生成一個鹽
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);   // 填充隨機值
        System.out.printf("鹽: %s%n", Arrays.toString(salt));
        System.out.printf("鹽(Hex): %s%n", KeyUtil.bytesToHex(salt));

        // 3. 獲取 SHA-256 消息摘要物件，幫助我們生成密碼哈希
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // 4. 加鹽
        messageDigest.update(salt);

        // 5. 將密碼轉換為 byte[] 然後生成哈希
        byte[] hashedBytes = messageDigest.digest(password.getBytes());

        // 6. 將 byte[] 轉 Hex
        String hashedHexString = KeyUtil.bytesToHex(hashedBytes);
        System.out.printf("原始密碼: %s%n", password);
        System.out.printf("加鹽後的哈希密碼: %s%n", hashedHexString);


    }
}
