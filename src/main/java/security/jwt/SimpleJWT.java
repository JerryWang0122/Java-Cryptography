package security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import security.KeyUtil;

// 建立一個高鐵票的 JWT
public class SimpleJWT {
    public static void main(String[] args) throws JOSEException {
        // 1. 高鐵公司的簽名專用密鑰 (JWK)
        String signingSecure = "abcdefghijklmnopqrstuvwxyz123456";  // 256bits -> 32bytes

        // 2. 建立 payload (創建 JWT 的聲明 claims) 裡面就是票務資訊
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("高鐵票")
                .issuer("台灣高鐵")
                .claim("起點", "台北")
                .claim("終點", "台中")
                .claim("艙等", "商務艙")
                .claim("車廂", "6")
                .claim("座位", "8E")
                .claim("車次", "651")
                .claim("日期", "2024-05-27")
                .claim("姓名", "王小明")
                .build();

        // 3. 對 JWT 進行簽名，並取得 token
        String token = KeyUtil.signJWT(claimsSet, signingSecure);

        System.out.printf("高鐵票 token: %s%n", token);

    }
}
