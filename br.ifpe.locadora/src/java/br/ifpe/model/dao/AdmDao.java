/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.dao;

import br.ifpe.model.entidades.Adm;
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
public class AdmDao implements CrudDao<Adm> {

    private static AdmDao instance = null;
    private Session session;
    private Conexao conexao;

    private AdmDao() throws ErroSistema {
        this.conexao = Conexao.getInstance();
    }

    public static AdmDao getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new AdmDao();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Adm entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();
        session.getTransaction().begin();

        try {

            session.saveOrUpdate(entidade);

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivel salvar o Adm, erro interno!!");
            System.err.println(e.getMessage());

        } finally {

            session.getTransaction().commit();
            System.out.println("Adm salvo!");
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(AdmDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }
    }

    @Override
    public Adm buscar(Adm entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();

        session.getTransaction().begin();
        try {

            return (Adm) session.get(Adm.class, entidade.getId());
        } catch (NullPointerException e) {
            System.err.println("Nome invalido, não existe esse nome no DB!!");
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
    public List<Adm> buscarTodos() throws ErroSistema {
        if (session == null) {
            session = conexao.getFactory().openSession();
        }
        session.getTransaction().begin();

        try {

            return session.createQuery("from Administrador").list();

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
    public void deletar(Adm entidade) throws ErroSistema {
        if (session == null) {
            session = conexao.getFactory().openSession();
        }
        session.getTransaction().begin();
        try {

            Adm adm = (Adm) session.get(Adm.class, entidade.getId());
            session.delete(adm);
            session.getTransaction().commit();
            System.out.println("Adm deletado!");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivél deletar!!");
            System.err.println(e.getMessage());

        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(AdmDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

    }

    public Adm buscarLogin(String email, String senha) throws ErroSistema {

        String qr = "from Administrador a where adm_email=:email and adm_senha=:senha";

        Adm a = null;

        session = conexao.getFactory().openSession();
        try {
            Query query = session.createQuery(qr);
            a = (Adm) query.setParameter("email", email).setParameter("senha", senha).uniqueResult();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return a;
    }
}
