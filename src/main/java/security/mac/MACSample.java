package security.mac;

import security.KeyUtil;

import javax.crypto.SecretKey;
import java.util.Arrays;

// MAC 訊息驗證碼簡單範例
public class MACSample {
    public static void main(String[] args) throws Exception {
        // 傳送方
        // 1. 定義我們要加上 MAC 的訊息
        String message = "本月加發 10000 元";
        System.out.printf("原始訊息: %s%n", message);

        // 2. 產生一把專用於 HMAC 的密鑰
        // HMAC -> Hash-based Message Authentication Code
        SecretKey macKey = KeyUtil.generateKeyForHmac();

        // 3. 利用此密鑰(macKey)和訊息(message)生成 MAC 值
        byte[] macValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());

        // 4. 將 MAC 值以 HEX 印出
        System.out.printf("產生的 MAC (HEX): %s%n", KeyUtil.bytesToHex(macValue));

        // -------------------------------------------------------------------------------
        // 5. 在實際應用中，接收方會收到 message 與 macValue (macValue 的 Hex)
        //    此時接收方本身就要有 密鑰macKey (雙方統一都有的)
        //    接收方根據 message + macKey 所產生的值與 macValue (macValue 的 Hex) 進行比對
        byte[] computedMacValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());

        // 6. 比較 macValue 與 computedMacValue 是否相等
        if (Arrays.equals(macValue, computedMacValue)) {
            System.out.println("MAC 驗證成功，來源正確");
        } else {
            System.out.println("MAC 驗證失敗，來源錯誤");
        }
    }
}
