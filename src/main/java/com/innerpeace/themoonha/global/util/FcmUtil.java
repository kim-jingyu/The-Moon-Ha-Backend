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

    public void send_FCM(String tokenId, String title, String content) {
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
            Message msg = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000) // 1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle(title)
                                    .setBody(content)
                                    .setIcon("stock_ticker_update")
                                    .setColor("#f45342")
                                    .build())
                            .build())
                    .setToken(registrationToken)
                    .build();

            // 알림 전송
            String response = FirebaseMessaging.getInstance().send(msg);
            System.out.println("Successfully sent message: " + response);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
