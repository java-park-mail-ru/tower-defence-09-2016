package ru.agrage.project.Mechanics.Handler;

import org.springframework.stereotype.Component;
import ru.agrage.project.Mechanics.Interval.GamePlayService;
import ru.agrage.project.Mechanics.Requests.SkeletonMoveRequest;
import ru.agrage.project.Mechanics.Requests.TowerHealthRequest;
import ru.agrage.project.Websocket.HandleException;
import ru.agrage.project.Websocket.MessageHandler;
import ru.agrage.project.Websocket.MessageHandlerContainer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/13/17.
 */

// @Component
public class TowerHealthHandler extends MessageHandler<TowerHealthRequest> {

    @NotNull
    private GamePlayService gamePlayService;

    @NotNull
    private MessageHandlerContainer messageHandlerContainer;

    public TowerHealthHandler(@NotNull GamePlayService gamePlayService,
                              @NotNull MessageHandlerContainer messageHandlerContainer) {
        super(TowerHealthRequest.class);
        this.gamePlayService = gamePlayService;
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @PostConstruct
    private void init() {
        messageHandlerContainer.registerHandler(TowerHealthRequest.class, this);
    }

    /*
     *  Снятие жизней с каждым ударом
     */
    @Override
    public void handle(@NotNull TowerHealthRequest message, @NotNull Long forUser) throws HandleException {

        // TODO ADD SKELETON

    }
}
