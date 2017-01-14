package ru.agrage.project.Mechanics.Handler;

import org.springframework.stereotype.Component;
import ru.agrage.project.Mechanics.Interval.GamePlayService;
import ru.agrage.project.Mechanics.Requests.TrollMoveRequest;
import ru.agrage.project.Websocket.HandleException;
import ru.agrage.project.Websocket.MessageHandler;
import ru.agrage.project.Websocket.MessageHandlerContainer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/13/17.
 */

//@Component
public class TrollAddHandler extends MessageHandler<TrollMoveRequest> {

    @NotNull
    private GamePlayService gamePlayService;

    @NotNull
    private MessageHandlerContainer messageHandlerContainer;

    public TrollAddHandler(@NotNull GamePlayService gamePlayService,
                           @NotNull MessageHandlerContainer messageHandlerContainer) {
        super(TrollMoveRequest.class);
        this.gamePlayService = gamePlayService;
        this.messageHandlerContainer = messageHandlerContainer;
    }

    @PostConstruct
    private void init() {
        messageHandlerContainer.registerHandler(TrollMoveRequest.class, this);
    }

    /*
     *  Если клиент купил тролля, то передаем информацию об этом в игровую механику
     */

    @Override
    public void handle(@NotNull TrollMoveRequest message, @NotNull Long forUser) throws HandleException {

        // TODO ADD TROLL

    }
}
