package ru.agrage.project.Websocket;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.agrage.project.Models.UserModel;
import ru.agrage.project.Service.UserService;
import javax.naming.AuthenticationException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class GameSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameSocketHandler.class);

    @NotNull
    private UserService userService;

    @NotNull
    private final MessageHandlerContainer messageHandlerContainer;

    @NotNull
    private final RemoteService remoteService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GameSocketHandler(@NotNull MessageHandlerContainer messageHandlerContainer,
                             @NotNull UserService userService,
                             @NotNull RemoteService remotePointService) {
        this.messageHandlerContainer = messageHandlerContainer;
        this.userService = userService;
        this.remoteService = remotePointService;
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws AuthenticationException {
        if (!webSocketSession.isOpen()) {
            LOGGER.error("[GameSocketHandler] Connection is not established.");
            throw new AuthenticationException("Connection is not established.");
        }
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        if (authentication == null) {
//            LOGGER.error("[GameSocketHandler] The user doesn't have auth session.");
//            throw new AuthenticationException("The user doesn't have auth session.");
//        }
//        String username = String.valueOf(authentication.getPrincipal());
//        if (remoteService.get(userService.getIdByUsername(username)) != null) {
//            LOGGER.error("[GameSocketHandler] User are playing in the game. ");
//            throw new WebSocketException ("User are playing in the game.");
//        }
        //remoteService.registerUser(userId, webSocketSession);
//
        LOGGER.info("#####################################################");
//        LOGGER.info("New player {} #{}", username, userService.getIdByUsername(username));
//
//        final Message message = new Message(JoinGame.Request.class, "{}");
//        try {
//            messageHandlerContainer.handle(message, userService.getIdByUsername(username));
//        }
//        catch (HandleException e) {
//            LOGGER.error("Can't handle message.");
//        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws AuthenticationException {

        final UserModel userModel;
        final Long ID = (Long) webSocketSession.getAttributes().get("ID");

        if (ID == null || (userModel = userService.getById(ID.intValue())) == null) {
            throw new AuthenticationException("Only authenticated users allowed to play a game.");
        }
        handleMessage(userModel, message);
    }

    @SuppressWarnings("OverlyBroadCatchBlock")
    private void handleMessage(UserModel userModel, TextMessage text) {

        final Message message;
        try {
            message = objectMapper.readValue(text.getPayload(), Message.class);
        } catch (IOException ex) {
            LOGGER.error("wrong json format at ping response", ex);
            return;
        }
        try {
            //noinspection ConstantConditions
            messageHandlerContainer.handle(message, userModel.getId());
        } catch (HandleException e) {
            LOGGER.error("Can't handle message of type " + message.getType() + " with content: " + message.getData(), e);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        LOGGER.warn("Websocket transport problem", throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        final Long ID = (Long) webSocketSession.getAttributes().get("ID");
        if (ID == null) {
            LOGGER.warn("User disconnected but his session was not found (closeStatus=" + closeStatus + ')');
            return;
        }
        remoteService.removeUser(ID);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
