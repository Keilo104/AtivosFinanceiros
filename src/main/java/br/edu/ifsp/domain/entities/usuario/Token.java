package br.edu.ifsp.domain.entities.usuario;

import java.time.LocalDate;

public class Token {
    private Usuario usuario;
    private String token;
    private LocalDate dateTime;

    public Token( String token, LocalDate dateTime ) {
        this.token = token;
        this.dateTime = dateTime;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario( Usuario usuario ) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime( LocalDate dateTime ) {
        this.dateTime = dateTime;
    }
}
