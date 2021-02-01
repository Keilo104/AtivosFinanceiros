package br.edu.ifsp.domain.usecases.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public void send( String email, String token ) {
        //permitir a acesso a aplicativos na conta do google
        //desativar antivirus?
        String emailFrom = "ativosfinanceiros23@gmail.com";
        String password = "A1234567B";
        String emailTo = email;

        Properties props = new Properties();
        props.put( "mail.smtp.host", "smtp.gmail.com" );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.smtp.socketFactory.port", "587" );
        props.put( "mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.port", "587" );

        Session session = Session.getDefaultInstance( props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication( emailFrom,
                                password );
                    }
                } );

        try {

            Message message = new MimeMessage( session );
            message.setFrom( new InternetAddress( emailFrom ) );

            Address[] toUser = InternetAddress //Destinatário(s) -> , e + emails
                    .parse( emailTo );

            message.setRecipients( Message.RecipientType.TO, toUser );
            message.setSubject( "Recuperação de senha" );
            message.setText( "Você solicitou a recuperação de sua senha no sistema de Ativos Financeiros.\nEste é o seu token:\n" + token + "\nCaso você não tenha solicitado a recuperação de senha, favor ignorar este email :)" );

            Transport.send( message );
        } catch ( MessagingException e ) {
            throw new RuntimeException( e );
        }
    }
}
