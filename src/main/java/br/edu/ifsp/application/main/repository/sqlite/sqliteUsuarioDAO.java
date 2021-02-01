package br.edu.ifsp.application.main.repository.sqlite;

import br.edu.ifsp.domain.DAOs.GrupoDAO;
import br.edu.ifsp.domain.DAOs.UsuarioDAO;
import br.edu.ifsp.domain.entities.grupo.Grupo;
import br.edu.ifsp.domain.entities.usuario.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class sqliteUsuarioDAO implements UsuarioDAO {

    @Override
    public String create( Usuario usuario ) {
        String sql = "INSERT INTO USUARIO VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, usuario.getCpf() );
            stat.setString( 2, usuario.getNome() );
            stat.setString( 3, usuario.getEmail() );
            stat.setString( 4, usuario.getSenha() );
            stat.setFloat( 5, 0 );
            stat.setFloat( 6, 0 );
            stat.setFloat( 7, 0 );
            stat.setFloat( 8, 0 );
            stat.setFloat( 9, 0 );

            stat.execute();
            return usuario.getCpf();
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Usuario resultSetToEntity( ResultSet rs ) throws SQLException {
        String cpf = rs.getString( "cpf" );
        String nome = rs.getString( "nome" );
        String email = rs.getString( "email" );
        String senha = rs.getString( "senha" );
        Float totalLucrado = rs.getFloat( "totalLucrado" );
        Float totalInvestido = rs.getFloat( "totalInvestido" );
        Float lucroPotencial = rs.getFloat( "lucroPotencial" );
        Float valorAtual = rs.getFloat( "valorAtual" );
        Float investimentoAtual = rs.getFloat( "investimentoAtual" );

        return new Usuario( cpf, nome, email, senha, totalLucrado, totalInvestido, lucroPotencial, valorAtual, investimentoAtual );
    }

    @Override
    public Optional<Usuario> findOne( String cpf ) {
        String sql = "SELECT * FROM USUARIO WHERE cpf= ?";
        Usuario usuario = null;
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, cpf );
            ResultSet rs = stat.executeQuery();

            if ( rs.next() ) {
                usuario = resultSetToEntity( rs );

                GrupoDAO grupoDAO = new sqliteGrupoDAO();
                List<Grupo> grupos = grupoDAO.findAllByCpf( usuario.getCpf() );
                for ( Grupo g : grupos ) {
                    usuario.addGrupo( g );
                }
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable( usuario );
    }

    public Optional<Usuario> findOneByEmail( String email ) {
        String sql = "SELECT * FROM USUARIO WHERE email = ?";
        Usuario usuario = null;
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, email );
            ResultSet rs = stat.executeQuery();

            if ( rs.next() ) {
                usuario = resultSetToEntity( rs );

                GrupoDAO grupoDAO = new sqliteGrupoDAO();
                List<Grupo> grupos = grupoDAO.findAllByCpf( usuario.getCpf() );
                for ( Grupo g : grupos ) {
                    usuario.addGrupo( g );
                }
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return Optional.ofNullable( usuario );
    }

    @Override
    public Usuario checkLogin( String email, String senha ) {
        Usuario u = findOneByEmail( email ).orElse( null );
        if ( u != null && u.getSenha().equals( senha ) ) {
            return u;
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM USUARIO;";
        List<Usuario> usuarios = new ArrayList<>();
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            ResultSet rs = stat.executeQuery();
            while ( rs.next() ) {
                Usuario usuario = resultSetToEntity( rs );
                usuarios.add( usuario );

                GrupoDAO grupoDAO = new sqliteGrupoDAO();
                List<Grupo> grupos = grupoDAO.findAllByCpf( usuario.getCpf() );
                for ( Grupo g : grupos ) {
                    usuario.addGrupo( g );
                }
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean update( Usuario usuario ) {
        String sql = "UPDATE USUARIO SET nome = ?, email = ?, senha = ?, totalLucrado = ?, totalInvestido = ?,  lucroPotencial = ?, valorAtual = ?, investimentoAtual = ? WHERE cpf = ?";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, usuario.getNome() );
            stat.setString( 2, usuario.getEmail() );
            stat.setString( 3, usuario.getSenha() );
            stat.setFloat( 4, usuario.getTotalLucrado() );
            stat.setFloat( 5, usuario.getTotalInvestido() );
            stat.setFloat( 6, usuario.getLucroPotencial() );
            stat.setFloat( 7, usuario.getValorAtual() );
            stat.setFloat( 8, usuario.getInvestimentoAtual() );
            stat.setString( 9, usuario.getCpf() );

            stat.execute();
            return true;
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey( String cpf ) {
        String sql = "DELETE FROM USUARIO WHERE cpf = ?;";
        try ( PreparedStatement stat = ConnectionFactory.createPreparedStatement( sql ) ) {
            stat.setString( 1, cpf );
            stat.execute();
            return true;
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete( Usuario usuario ) {
        if ( usuario == null || usuario.getCpf() == null )
            throw new IllegalArgumentException( "Usuario and Usuario cpf cannot be null" );
        return deleteByKey( usuario.getCpf() );
    }
}
