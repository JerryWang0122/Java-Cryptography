package security.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import security.KeyUtil;

/**
 依據（JWA、JWK）與（JWS）產生 Token（JWT）

     +-----+   +-----+
     | JWK | → | JWS |
     +-----+   +-----+
         ↑         ↓
     +-----+   +-----+
     | JWA |   | JWT |
     +-----+   +-----+

 */
public class JWTExample {
    public static void main(String[] args) throws Exception {
        // 1. JWA : 決定演算法 -> 使用 HS256
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // 2. JWK : 產生一個簽名用的密鑰給 JWS 使用
        String signingSecret = KeyUtil.generateSecret(32);    // 256位元 (32 bytes)
        System.out.printf("signingSecret: %s%n", signingSecret);

        // 3. 定義 payload
        JWTClaimsSet payload = new JWTClaimsSet.Builder()
                                                .subject("人事公告")  // 設定主題
                                                .issuer("人事部")    // 設定發行者
                                                .claim("name", "JOHN")   // 添加自訂訊息
                                                .claim("title", "升副總經理")   // 添加自訂訊息
                                                .claim("date", "2024-06-01")   // 添加自訂訊息
                                                .build();

        // 4. JWT : 創建 JWT(尚未簽名加密)
        SignedJWT signedJWT = new SignedJWT(header, payload);

        // 5. JWS : 將 JWT 簽名加密
        //    JWS 簽章利用 JWK 生成的密鑰
        JWSSigner jwsSigner = new MACSigner(signingSecret);

        // 6. 進行簽名
        signedJWT.sign(jwsSigner);

        // 7. 透過序列化技術產生 token : 可以被安全的傳遞、儲存
        String token = signedJWT.serialize();  // 序列化

        System.out.printf("JWT(Token): %n%s%n", token);




    }
}