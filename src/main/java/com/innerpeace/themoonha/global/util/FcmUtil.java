package com.innerpeace.themoonha.global.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

/**
 * FCM 알림 Util
 * @author 조희정
 * @since 2024.09.11
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	조희정       최초 생성
 * </pre>
 */
@Component
public class FcmUtil {

    @Value("${fcm.key.path}")
    private String SERIVCE_ACCOUNT_JSON;

    public void send_FCM(String tokenId, String title, String content, String type, Long id) {
        try {
            //JSON 파일
//            FileInputStream refreshToken = new FileInputStream(SERIVCE_ACCOUNT_JSON);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(SERIVCE_ACCOUNT_JSON).getInputStream()))
                    .build();

            // initializing
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // FCM 토큰 입력
            String registrationToken = tokenId;

            // message 작성
            Message.Builder messageBuilder = Message.builder()
                    .putData("title", title)
                    .putData("message", content)
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000) // 1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .setNotification(AndroidNotification.builder()
//                                    .setTitle(title)
//                                    .setBody(content)
                                    .setIcon("ic_noti")
                                    .setColor("#01A76B")
                                    .build())
                            .build())
                    .setToken(registrationToken);

            // type이 null이 아니면 데이터 추가
            if (type != null) {
                messageBuilder.putData("type", type);
            }

            // id가 null이 아니면 데이터 추가
            if (id != null) {
                messageBuilder.putData("id", id.toString());
            }

            // 알림 전송
            Message msg = messageBuilder.build();
            String response = FirebaseMessaging.getInstance().send(msg);
            System.out.println("Successfully sent message: " + response);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void send_FCM(String tokenId, String title, String content) {
        send_FCM(tokenId, title, content, null, null);
    }

    public void send_FCM(String tokenId, String title, String content, Long id) {
        send_FCM(tokenId, title, content, null, id);
    }

    public void send_FCM(String tokenId, String title, String content, String type) {
        send_FCM(tokenId, title, content, type, null);
    }
}



