/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author João Henrique 2
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import servlets.entidade.Usuario;

public class UsuarioDAO extends DAO {

    public void salvar(Usuario usuario) {
        
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public List exibir() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(c) from Usuario as c");

            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
