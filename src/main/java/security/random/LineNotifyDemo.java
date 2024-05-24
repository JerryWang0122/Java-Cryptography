package security.random;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LineNotifyDemo {

    public static void main(String[] args) throws Exception {
        // 1. 要發送的資料
        String message = "test" + "&stickerPackageId=" + 8525
                + "&stickerId=" + 16581290;;
        // 2. 存取權杖(也稱為:授權 Token)
        String token = "fBffx1erl4uqP8eatiXDzqxCzxh5uDjafnfnZibr179";
        // 3. Line Notify 的發送位置
        String lineNotifyUrl = "https://notify-api.line.me/api/notify";

        // 4. 發送前設定
        byte[] postData = ("message=" + message).getBytes("UTF-8");
        URL url = new URL(lineNotifyUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // 5. 訊息發送
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        // 6. 回應資料
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}