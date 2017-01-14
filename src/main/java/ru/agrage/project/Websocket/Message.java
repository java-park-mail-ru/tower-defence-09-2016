package ru.agrage.project.Websocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/12/17.
 */

public class Message {

    @NotNull
    @JsonProperty("type")
    private String type;

    @NotNull
    @JsonProperty("data")
    private String data;

    public Message()    {
    }

    public Message(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public Message(Class clazz, String data) {
        this(clazz.getName(), data);
    }

    public static final String INITIALIZE_USER = "InitializePlayer";
    public static final String SNAPSHOT = "Snapshot";
    public static final String REMOVE_USER = "RemovePlayer";

    public void setType(String type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }
    public String getData() {
        return data;
    }

}
