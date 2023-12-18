package com.jscisco.lom.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameLogTest {

    private GameLog gameLog;

    @BeforeEach
    public void setup() {
        this.gameLog = new GameLog();
    }

    @Test
    public void whenILogAMessage_thenItIsStoredInTheGameLog() {
        this.gameLog.log("test");
        assertThat(this.gameLog.getLogs()).isNotEmpty();
    }

    @Test
    public void givenIHave10Messages_whenIRetrieve5OfThem_IGetThe5Expected() {
        for (int i = 0; i < 10; i++) {
            this.gameLog.log(String.valueOf(i));
        }
        List<String> logs = this.gameLog.getLogs(5);
        assertThat(logs.size()).isEqualTo(5);
        assertThat(logs).contains("5");
        assertThat(logs).contains("9");
    }

    @Test
    public void givenIHave5Messages_whenIRetrieve10OfThem_IGetThe5Expected() {
        for (int i = 0; i < 5; i++) {
            this.gameLog.log(String.valueOf(i));
        }
        List<String> logs = this.gameLog.getLogs(10);
        assertThat(logs.size()).isEqualTo(5);
        assertThat(logs).contains("0");
        assertThat(logs).contains("4");
    }

}
