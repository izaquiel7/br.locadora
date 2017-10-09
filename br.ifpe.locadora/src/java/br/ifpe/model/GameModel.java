/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model;

import br.ifpe.model.dao.GameDao;
import br.ifpe.model.entidades.Game;
import br.ifpe.model.util.erro.ErroSistema;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Izack
 */
public class GameModel implements CrudModel<Game>, Serializable {

    private static GameModel instance;
    private GameDao dao;
    private Game game;

    private GameModel() throws ErroSistema {
        dao = GameDao.getInstance();
        game = new Game();
    }

    public static GameModel getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Game entidade) throws ErroSistema {
        dao.inserirOUatualizar(entidade);
    }

    @Override
    public Game buscar(Game entidade) throws ErroSistema {
        if (dao.buscar(entidade) == null) {
            //mensagem de erro Faces
            return null;
        }

        return dao.buscar(entidade);

    }

    @Override
    public List<Game> buscarTodos() throws ErroSistema {
        List<Game> games = dao.buscarTodos();
        if (games == null) {
            //mensagem de erro Faces
            return null;
        }
        return games;

    }

    @Override
    public void deletar(Game entidade) throws ErroSistema {
        dao.deletar(entidade);
    }

    
    
    
    //********************************************************************//   
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameDao getDao() {
        return dao;
    }

    public void setDao(GameDao dao) {
        this.dao = dao;
    }

}
