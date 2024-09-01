package com.innerpeace.themoonha.global.handler;

import com.innerpeace.themoonha.global.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.innerpeace.themoonha.global.exception.ErrorCode.LIVE_STREAM_FAILED;

@Component
public class StreamingWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        sendToRtmpServer(message.asBytes(), extractStreamKey(session.getUri()));
    }

    private String extractStreamKey(URI uri) {
        Map<String, String> queryPairs = new HashMap<>();
        for (String splitQuery : uri.getQuery().split("&")) {
            int index = splitQuery.indexOf("=");
            queryPairs.put(splitQuery.substring(0, index), splitQuery.substring(index + 1));
        }
        return queryPairs.get("streamKey");
    }

    private void sendToRtmpServer(byte[] payload, String streamKey) {
        try {
            OutputStream processOutputStream = getProcessBuilder(streamKey).start().getOutputStream();
            processOutputStream.write(payload);
            processOutputStream.flush();
        } catch (IOException e) {
            throw new CustomException(LIVE_STREAM_FAILED);
        }
    }

    private ProcessBuilder getProcessBuilder(String streamKey) {
        return new ProcessBuilder(
                "ffmpeg",
                "-f", "webm",
                "-i", "pipe:0",
                "-c:v", "libx264",
                "-c:a", "aac",
                "-f", "flv",
                "rtmp://nginx-rtmp/live/" + streamKey
        );
    }
}
