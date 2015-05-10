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
        
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        
        player1.setJogador(userC.findUsuario(1));
        player2.setJogador(userC.findUsuario(2));
        player3.setJogador(userC.findUsuario(3));
        player4.setJogador(userC.findUsuario(4));
        
        player1.setMoney(1000);
        player2.setMoney(2000);
        player3.setMoney(3000);
        player4.setMoney(1000);
        
        mesa.addJogador(player1, 0);
        mesa.addJogador(player2, 2);
        mesa.addJogador(player3, 3);
        mesa.addJogador(player4, 6);
        
        mesa.jogada();
        
    }
    
}
