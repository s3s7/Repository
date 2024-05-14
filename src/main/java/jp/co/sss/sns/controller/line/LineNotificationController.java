package jp.co.sss.sns.controller.line;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.stream.Collectors;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

//@RequiredArgsConstructor
@Controller
public class LineNotificationController {

 //   public class  LineNotifys  {
   // public static void LineNotify (String b) {
        public void LineNotifyRun (String a) {
            LineNotificationController lineNotify = new LineNotificationController("g5OcOwirS949faJhVyfUZDLEzGcZAoxw5IJvnBEZ4HG"); // LINE Notifyのアクセストークン(適宜変更)
            lineNotify.notify("http://kakedashi-xx.comを閲覧することで、IT知識が拡充されます。");
            System.out.println("javaからlineへ通知しました。");
        }

        private final String token;
         public LineNotificationController(String token) {
            this.token = token;
        }

        public void notify (String message){
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://notify-api.line.me/api/notify");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.addRequestProperty("Authorization", "Bearer " + token);
                try (OutputStream os = connection.getOutputStream();
                     PrintWriter writer = new PrintWriter(os)) {
                    writer.append("message=").append(URLEncoder.encode(message, "UTF-8")).flush();
                    try (InputStream is = connection.getInputStream();
                         BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
                        String res = r.lines().collect(Collectors.joining());
                        if (!res.contains("\"message\":\"ok\"")) {
                            System.out.println(res);
                        }
                    }
                }
            } catch (Exception ignore) {
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
   // }
//}
}