/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.dao;

import br.ifpe.model.entidades.Cliente;
import br.ifpe.model.util.conexao.Conexao;
import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author izaquiel
 */
public class ClienteDao implements CrudDao<Cliente> {

    private static ClienteDao instance = null;
    private Session session;
    private Conexao conexao;

    private ClienteDao() throws ErroSistema {
        this.conexao = Conexao.getInstance();
    }

    public static ClienteDao getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Cliente entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();
        session.getTransaction().begin();

        try {

            session.saveOrUpdate(entidade);

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivel salvar o Cliente, erro interno!!");
            System.err.println(e.getMessage());

        } finally {

            session.getTransaction().commit();
            System.out.println("Cliente salvo!");
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(AdmDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }
    }

    @Override
    public Cliente buscar(Cliente entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();

        session.getTransaction().begin();
        try {

            return (Cliente) session.get(Cliente.class, entidade.getId());
        } catch (NullPointerException e) {
            System.err.println("Nome invalido, não existe esse nome no DB!!");
            System.err.println(e.getMessage());
        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() throws ErroSistema {
        if (session == null) {
            session = conexao.getFactory().openSession();
        }
        session.getTransaction().begin();

        try {

            return session.createQuery("from Cliente").list();

        } catch (HibernateException e) {
            System.err.println("Erro interno!");
            System.err.println(e.getMessage());

        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(AdmDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

        return null;
    }

    @Override
    public void deletar(Cliente entidade) throws ErroSistema {
        if (session == null) {
            session = conexao.getFactory().openSession();
        }
        session.getTransaction().begin();
        try {

            Cliente cliente = (Cliente) session.get(Cliente.class, entidade.getId());
            session.delete(cliente);
            session.getTransaction().commit();
            System.out.println("Cliente deletado!");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivél deletar!!");
            System.err.println(e.getMessage());

        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

    }

    public Cliente buscarLogin(String email, String senha) throws ErroSistema {

        String qr = "from Cliente a where cliente_email=:email and cliente_senha=:senha";

        Cliente c = null;

        session = conexao.getFactory().openSession();
        try {
            Query query = session.createQuery(qr);
            c = (Cliente) query.setParameter("email", email).setParameter("senha", senha).uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return c;
    }
}