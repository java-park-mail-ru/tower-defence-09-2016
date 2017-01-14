package ru.agrage.project.Mechanics.Handler;

import org.springframework.stereotype.Component;
import ru.agrage.project.Mechanics.GameMechanics;
import ru.agrage.project.Mechanics.Requests.JoinGame;
import ru.agrage.project.Websocket.HandleException;
import ru.agrage.project.Websocket.MessageHandler;
import ru.agrage.project.Websocket.MessageHandlerContainer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/13/17.
 */

// @Component
public class JoinGameHandler extends MessageHandler<JoinGame.Request> {

    @NotNull
    private GameMechanics gameMechanics;

    @NotNull
    private MessageHandlerContainer messageHandlerContainer;

    public JoinGameHandler(@NotNull GameMechanics gameMechanics, @NotNull MessageHandlerContainer messageHandlerContainer) {
        super(JoinGame.Request.class);
        this.gameMechanics = gameMechanics;
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @PostConstruct
    private void init() {
        messageHandlerContainer.registerHandler(JoinGame.Request.class, this);
    }

    @Override
    public void handle(@NotNull JoinGame.Request message, @NotNull Long forUser) throws HandleException {

        // TODO ADD USER

    }
}
