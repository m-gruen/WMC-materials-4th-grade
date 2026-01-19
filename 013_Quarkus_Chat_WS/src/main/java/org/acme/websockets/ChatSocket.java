package org.acme.websockets;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.acme.services.MessageService;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.mutiny.ext.web.Session;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
@ApplicationScoped
public class ChatSocket {
    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();
    @Inject
    ObjectMapper mapper;
    @Inject
    MessageService messageService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String json, Session session) throws Exception {
        // our code to save the message and notify others...
    }

    private void broadcast(String message) {
        sessions.forEach(s -> s.getAsyncRemote().sendText(message));
    }
}
