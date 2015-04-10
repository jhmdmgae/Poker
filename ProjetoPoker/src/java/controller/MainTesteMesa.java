package controller;

import DAO.DAO;
import servlets.entidade.Usuario;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author João Henrique 2
 */
public class MainTesteMesa {
    
    public static void main(String[] args) {
        
        
        Usuario usuario = new Usuario();

        DAO dao = new DAO();
        
        dao.getEntityManager();

        UsuarioJpaController userC = new UsuarioJpaController(dao.getEmf());
        
        Mesa mesa = new Mesa();
        
        mesa.addJogador(userC.findUsuario(1), 0);
        mesa.addJogador(userC.findUsuario(2), 2);
        mesa.addJogador(userC.findUsuario(3), 3);
        mesa.addJogador(userC.findUsuario(4), 6);
        
        mesa.jogada();
        
        mesa.addJogador(userC.findUsuario(5), 7);
        
        mesa.jogada();
        
    }
    
}
