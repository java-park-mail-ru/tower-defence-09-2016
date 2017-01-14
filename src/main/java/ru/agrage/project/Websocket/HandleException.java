package ru.agrage.project.Websocket;

/**
 * Created by dmitry on 1/12/17.
 */
public class HandleException extends Exception {

    public HandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandleException(String message) {
        super(message);
    }

}
