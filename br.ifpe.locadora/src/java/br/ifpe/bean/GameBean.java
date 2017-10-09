/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.bean;

import br.ifpe.model.GameModel;
import br.ifpe.model.entidades.Game;
import br.ifpe.model.util.erro.ErroSistema;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author izaquias
 */
@ManagedBean(name = "gameBean")
@SessionScoped
public class GameBean implements CrudBean<Game> {
    
    private Game game;
    private GameModel model;
    
    public GameBean() {
    }
    
    public void init() throws ErroSistema {
        game = new Game();
        model = GameModel.getInstance();
    }
    
    @Override
    public String inserirOUatualizar() throws ErroSistema {
        model.inserirOUatualizar(this.game);
        return null;
    }
    
    @Override
    public Game buscar() throws ErroSistema {
       return model.buscar(this.game);
    }
    
    @Override
    public String buscarTodos() throws ErroSistema {
       model.buscarTodos();
       return null;
    }
    
    @Override
    public String deletar() throws ErroSistema {
        model.deletar(this.game);
         return null;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public GameModel getModel() {
        return model;
    }
    
    public void setModel(GameModel model) {
        this.model = model;
    }
    
}
