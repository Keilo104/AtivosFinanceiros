package br.edu.ifsp.domain.entities.log;

import java.time.LocalDate;

public class Log {
    private LocalDate data;

    public Log() {
        this.data = LocalDate.now();
    }

    public Log(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Log{" +
                "data=" + data +
                '}';
    }
}
