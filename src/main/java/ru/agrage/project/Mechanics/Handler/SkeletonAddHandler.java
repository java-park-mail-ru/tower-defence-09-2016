package ru.agrage.project.Mechanics.Handler;

import org.springframework.stereotype.Component;
import ru.agrage.project.Mechanics.Interval.GamePlayService;
import ru.agrage.project.Mechanics.Requests.SkeletonMoveRequest;
import ru.agrage.project.Websocket.HandleException;
import ru.agrage.project.Websocket.MessageHandler;
import ru.agrage.project.Websocket.MessageHandlerContainer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/13/17.
 */

//@Component
public class SkeletonAddHandler extends MessageHandler<SkeletonMoveRequest> {

    @NotNull
    private GamePlayService gamePlayService;

    @NotNull
    private MessageHandlerContainer messageHandlerContainer;

    public SkeletonAddHandler(@NotNull GamePlayService gamePlayService,
                              @NotNull MessageHandlerContainer messageHandlerContainer) {
        super(SkeletonMoveRequest.class);
        this.gamePlayService = gamePlayService;
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @PostConstruct
    private void init() {
        messageHandlerContainer.registerHandler(SkeletonMoveRequest.class, this);
    }

    /*
     *  Если клиент купил скелета, то передаем информацию об этом в игровую механику
     */
    @Override
    public void handle(@NotNull SkeletonMoveRequest message, @NotNull Long forUser) throws HandleException {

        // TODO ADD SKELETON

    }
}
