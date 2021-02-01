package br.edu.ifsp.domain.entities.log;

import java.time.LocalDateTime;

public class Log {
    private LocalDateTime data;

    public Log() {
        this.data = LocalDateTime.now();
    }

    public Log( LocalDateTime data ) {
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Log{" +
                "data=" + data +
                '}';
    }
}
