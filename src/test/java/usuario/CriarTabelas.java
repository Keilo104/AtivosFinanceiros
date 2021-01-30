package usuario;

import br.edu.ifsp.application.main.repository.inMemory.InMemoryUsuarioDAO;
import br.edu.ifsp.domain.entities.usuario.Usuario;

import java.util.logging.Logger;

public class CriarTabelas {
    private static final Logger LOGGER = Logger.getLogger( CriarTabelas.class.getName() );

    public static void main( String[] args ) {

        InMemoryUsuarioDAO inMemoryUsuarioDAO = new InMemoryUsuarioDAO();

        Usuario usuario = new Usuario("123.456.789-10", "Lucas Pastore Masqueti e Souza", "email_topzera@email.com", "123");
        inMemoryUsuarioDAO.create( usuario );
        LOGGER.info( "Usuario criado: " + usuario );

    }

}
