package ru.agrage.project.Configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import ru.agrage.project.Websocket.GameSocketHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;


/**
 * Created by dmitry on 1/14/17.
 *
 *
 * @author Rossen Stoyanchev
 * @since 4.0
 */

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor {

    private Collection<String> attributeNames;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionHandshakeInterceptor.class);


    public HttpSessionHandshakeInterceptor() {
        this(null);
    }

    public HttpSessionHandshakeInterceptor(Collection<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            Cookie[] session = servletRequest.getServletRequest().getCookies();
            if (session.length == 0) {
                LOGGER.error("[beforeHandshake] User don't have cookie.");
                return false;
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
    }
}

