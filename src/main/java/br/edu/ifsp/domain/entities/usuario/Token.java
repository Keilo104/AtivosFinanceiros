package br.edu.ifsp.domain.entities.usuario;

import java.time.LocalDateTime;

public class Token {
    private int id;
    private Usuario usuario;
    private String token;
    private LocalDateTime dateTime;

    public Token( String token, LocalDateTime dateTime ) {
        this.token = token;
        this.dateTime = dateTime;
    }

    public Token(Usuario usuario, LocalDateTime dateTime) {
        this.usuario = usuario;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime( LocalDateTime dateTime ) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", token='" + token + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
