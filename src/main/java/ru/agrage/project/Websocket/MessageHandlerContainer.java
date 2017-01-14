package ru.agrage.project.Websocket;

/**
 * Created by dmitry on 1/12/17.
 */
public interface MessageHandlerContainer {

    void handle(Message message, long forUser) throws HandleException;

    <T> void registerHandler(Class<T> clazz, MessageHandler<T> handler);

}
