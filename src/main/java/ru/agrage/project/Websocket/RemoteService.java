package ru.agrage.project.Websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dmitry on 1/12/17.
 */

@Service
public class RemoteService {

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void registerUser(Long identifier, WebSocketSession webSocketSession) {
        sessions.put(identifier, webSocketSession);
    }

    public boolean isConnected(Long userId) {
        return sessions.containsKey(userId) && sessions.get(userId).isOpen();
    }

    public WebSocketSession removeUser(Long userId)
    {
        return sessions.remove(userId);
    }

    public void cutDownConnection(Long userId, CloseStatus closeStatus) {
        final WebSocketSession webSocketSession = sessions.get(userId);
        if (webSocketSession != null && webSocketSession.isOpen()) {
            try {
                webSocketSession.close(closeStatus);
            } catch (IOException ignore) {
            }
        }
    }

    public void sendMessageToUser(Long userId, Message message) throws IOException {
        final WebSocketMessage<String> webSocketMessage = new TextMessage(objectMapper.writeValueAsString(message));
        final WebSocketSession webSocketSession = sessions.get(userId);
        if (webSocketSession == null) {
            throw new IOException("no game websocket for user " + userId);
        }
        if (!webSocketSession.isOpen()) {
            throw new IOException("session is closed or not exists");
        }
        try {
            webSocketSession.sendMessage(webSocketMessage);
        } catch (JsonProcessingException | WebSocketException e) {
            throw new IOException("Unable to send message", e);
        }
    }

    public WebSocketSession get (long userId) {
        return sessions.get(userId);
    }

    public boolean contains (WebSocketSession session) {
        return sessions.containsValue(session);
    }
}
