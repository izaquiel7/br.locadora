/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.dao;

import br.ifpe.model.entidades.Game;
import br.ifpe.model.util.conexao.Conexao;
import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author izaquiel
 */
public class GameDao implements CrudDao<Game> {

    private static GameDao instance = null;
    private Session session;
    private Conexao conexao;

    private GameDao() throws ErroSistema {
        this.conexao = Conexao.getInstance();
    }

    public static GameDao getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new GameDao();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Game entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();
        session.getTransaction().begin();

        try {

            session.saveOrUpdate(entidade);

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivel salvar o Game, erro interno!!");
            System.err.println(e.getMessage());

        } finally {

            session.getTransaction().commit();
            System.out.println("Game salvo!");
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(GameDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

    }

    @Override
    public Game buscar(Game entidade) throws ErroSistema {

        session = conexao.getFactory().openSession();

        session.getTransaction().begin();
        try {

            return (Game) session.get(Game.class, entidade.getId());
        } catch (NullPointerException e) {
            System.err.println("Nome invalido, não existe esse nome no DB!!");
            System.err.println(e.getMessage());
        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(GameDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }
        return null;
    }

    @Override
    public List<Game> buscarTodos() throws ErroSistema {

        session = conexao.getFactory().openSession();

        session.getTransaction().begin();

        try {

            return session.createQuery("from Game").list();

        } catch (HibernateException e) {
            System.err.println("Erro interno!");
            System.err.println(e.getMessage());

        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(GameDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

        return null;
    }

    @Override
    public void deletar(Game entidade) throws ErroSistema {
        if (session == null) {
            session = conexao.getFactory().openSession();
        }
        session.getTransaction().begin();
        try {

            Game game = (Game) session.get(Game.class, entidade.getId());
            session.delete(game);
            session.getTransaction().commit();
            System.out.println("Game deletado!");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Não foi possivél deletar!!");
            System.err.println(e.getMessage());

        } finally {
            try {
                conexao.fecharSessao();
            } catch (ErroSistema ex) {
                Logger.getLogger(GameDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sessão finalizada");
        }

    }

}
