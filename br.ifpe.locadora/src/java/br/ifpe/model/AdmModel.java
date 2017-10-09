/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model;

import br.ifpe.model.dao.AdmDao;
import br.ifpe.model.entidades.Adm;
import br.ifpe.model.util.erro.ErroSistema;
import java.util.List;

/**
 *
 * @author izaquias
 */
public class AdmModel implements CrudModel<Adm>{

    private static AdmModel instance;
    private AdmDao dao;
    private Adm adm;

    private AdmModel() throws ErroSistema {
        dao = AdmDao.getInstance();
        adm = new Adm();
    }

    public static AdmModel getInstance() throws ErroSistema {
        if (instance == null) {
            instance = new AdmModel();
        }
        return instance;
    }

    @Override
    public void inserirOUatualizar(Adm entidade) throws ErroSistema {
     
        dao.inserirOUatualizar(entidade);
    }

    @Override
    public Adm buscar(Adm entidade) throws ErroSistema {
        if (dao.buscar(entidade) == null) {
            //mensagem de erro Faces
            return null;
        }

        return dao.buscar(entidade);

    }
    

    @Override
    public List<Adm> buscarTodos() throws ErroSistema {
          List<Adm> adms = dao.buscarTodos();
        if (adms == null) {
            //mensagem de erro Faces
            return null;
        }
        return adms;

    }

    @Override
    public void deletar(Adm entidade) throws ErroSistema {
         dao.deletar(entidade);
    }
    
    
    //********************************************************************//

    public AdmDao getDao() {
        return dao;
    }

    public void setDao(AdmDao dao) {
        this.dao = dao;
    }

    public Adm getAdm() {
        return adm;
    }

    public void setAdm(Adm adm) {
        this.adm = adm;
    }
    
    
    
}
