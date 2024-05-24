package security.mac;

import security.KeyUtil;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * 用於驗證薪資明細的 MAC
 *
 * 證明成功實施了 MAC 驗證策略，並且可以正確地驗證您的薪資明細的完整性和來源。
 * 使用這樣的策略可以確保只有擁有正確 MAC 密鑰的人（在這個例子中是 HR 部門）
 * 才能生成有效的 MAC，而其他人則不能。
 *
 * 這是一個非常重要的安全策略，特別是在涉及敏感資訊（如薪資明細）的場合。
 * 只要保護好您的密鑰，就可以確保消息的真實性和完整性。
 */
public class SalaryMacVerify {
    public static void main(String[] args) throws Exception {
        // 員工
        // 取得薪資檔案位置
        String filePath = "src/main/java/security/mac/my_salary.txt";
        // 取得金鑰檔案位置
        String keyPath = "src/main/java/security/mac/macKey.key";

        // 得到 HR 部門生成的 macValue
        String macValueFromHR = "19050534143b4e5254713595434c036190b0ad3b550474711e52abc13c12d85b";

        // 將密鑰檔 macKey.key 轉密鑰物件
        SecretKey macKey = KeyUtil.getSecretKeyFromFile("HmacSHA256", keyPath);

        // 員工透過 macKey + filePath(my_salary.txt) 自行生成 macValueFromEmployee
        String macValueFromEmployee = KeyUtil.generateMac("HmacSHA256", macKey, filePath);

        // 驗證 macValueFromEmployee == macValueFromHR，來判斷資料是否來自於 HR 部門
        if (macValueFromEmployee.equals(macValueFromHR)) {
            System.out.println("Mac 驗證成功，資料來源: HR");
            // 讀取檔案內容
            String fileContent = Files.readString(Paths.get(filePath));
            System.out.println(fileContent);
        } else {
            System.out.println("Mac 驗證失敗，資料來源:？");
        }
    }
}
