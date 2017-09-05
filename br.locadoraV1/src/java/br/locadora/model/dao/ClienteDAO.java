/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.locadora.model.dao;

import br.locadora.model.entidade.Cliente;
import br.locadora.model.util.ErroSistema;
import br.locadora.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Izaquiel
 */
public class ClienteDAO {

    private static ClienteDAO instance;
    private static EntityManager manage;
    HibernateUtil util = HibernateUtil.getInstance();

    private ClienteDAO() {
    }

    public static ClienteDAO getinstance() {

        if (instance == null) {
            instance = new ClienteDAO();
        }

        return instance;
    }

    public void inseri(Cliente cliente) {
        manage = util.getFactory().createEntityManager();
        manage.getTransaction().begin();

        try {
            manage.persist(cliente);
            manage.getTransaction().commit();
        } catch (Exception e) {

            manage.getTransaction().rollback();
            System.out.println("Tais vendo? um erro!!");
            System.out.println(e.getMessage());
            System.out.println((e.getCause()));
        } finally {
            manage.close();
            System.out.println("Fim da sessão!");
        }
    }

    public void altera(Cliente cliente) {
        manage = HibernateUtil.getInstance().getFactory().createEntityManager();
        manage.getTransaction().begin();

        try {
            manage.merge(cliente);
            manage.getTransaction().commit();
            System.out.println("cadastrou!");
        } catch (Exception e) {
            manage.getTransaction().rollback();
            System.out.println("Tais vendo? um erro!!");

        } finally {
            manage.close();
            System.out.println("Fim da sessão!!");
        }

    }

    public Cliente recupera(Cliente cliente) {
        manage = HibernateUtil.getInstance().getFactory().createEntityManager();

        try {

            return (Cliente) manage.find(Cliente.class, cliente.getEmail());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manage.close();
            System.out.println("Fim da sessão!!!");
        }
        return null;

    }

    public List<Cliente> recuperaTodos() {
        manage = HibernateUtil.getInstance().getFactory().createEntityManager();

        try {
            return (List) manage.createQuery("select c from Cliente c", Cliente.class).getResultList();

        } catch (Exception e) {
            System.out.println("Algo inexperado aconteceu, reveja seu código!!");
            System.out.println(e.getMessage());
        } finally {
            manage.close();
            System.out.println("Fim da sessão!!");
        }

        return null;
    }

    public void deletar(Cliente cliente) {
        manage = HibernateUtil.getInstance().getFactory().createEntityManager();
        manage.getTransaction().begin();

        try {
            cliente = manage.find(Cliente.class, cliente.getEmail());
            manage.remove(cliente);
            manage.getTransaction().commit();
        } catch (Exception e) {
            manage.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.err.println("Tais vendo?? um erro!!");
        }
    }

    public Cliente recuperaLogin(String email, String senha) {
        manage = HibernateUtil.getInstance().getFactory().createEntityManager();

        String hql = "from Cliente c where e_mail=:email and senha=:senha";
        Cliente c = null;

        try {
            Query query = manage.createQuery(hql);
            c = (Cliente) query.setParameter("email", email).setParameter("senha", senha).getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("Tais vendo?? um erro!!");
        }

        return c;
    }

}
