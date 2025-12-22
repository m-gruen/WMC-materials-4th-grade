package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.entities.Message;

@ApplicationScoped
public class MessageService {

    @Transactional
    public void create(Message message) {
        Message m = new Message();
        m.setUserId(message.getUserId());
        m.setText(message.getText());
        m.setTimestamp(message.getTimestamp());
        m.persistAndFlush();
    }
}
