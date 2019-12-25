package com.jscisco.lom;

import com.badlogic.gdx.graphics.Color;
import com.jscisco.lom.log.Message;
import com.jscisco.lom.log.MessageElement;
import com.jscisco.lom.log.MessageLog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestMessageLog {

    @Test
    void addingMessageToLogAddsItToTheLog() {
        MessageLog log = MessageLog.get();
        Message message = new Message();
        message.withElement(new MessageElement("My log", Color.BLACK));
        log.add(message);

        Assertions.assertThat(log.recentMessages().size()).isEqualTo(1);
    }

    @Test
    void recentMessagesShouldOnlyContainTheMostRecentFiveLogs() {
        MessageLog log = MessageLog.get();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.withElement(new MessageElement(String.format("%s", i), Color.BLACK));
            log.add(message);
        }
        List<Message> recentMessages = log.recentMessages();
        Assertions.assertThat(recentMessages.size()).isEqualTo(5);
        Assertions.assertThat(recentMessages.get(0).getElements().get(0).getText()).isEqualTo("9");
    }

}
