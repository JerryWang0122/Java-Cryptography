package security.hash;

import security.KeyUtil;

// 驗證 my_salary.txt 的 hash 是否被篡改
public class SalaryHashValidator {
    public static void main(String[] args) {
        // 透過 SalaryHashGenerator.java 先得知 Hash
        String knowHash = "ac47256ad517e18160e201a185414e2b5d772ed1b1fe86650d0ecebd00e1de6a";

        // 重新針對 my_salary.txt 產生 Hash
        // 宣告 my_salary.txt 的文件路徑
        String filePath = "src/main/java/security/hash/my_salary.txt";
        // 取得 hash
        String fileHash = KeyUtil.generateFileHash(filePath);


        // 比較 knowHash == hash 是否相等？
        if (knowHash.equals(fileHash)) {
            System.out.printf("%s 沒有遭受竄改%n", filePath);
        } else {
            System.out.printf("%s 可能遭受竄改%n", filePath);
        }

    }
}
