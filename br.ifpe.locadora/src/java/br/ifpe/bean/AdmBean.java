/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.bean;

import br.ifpe.model.AdmModel;
import br.ifpe.model.entidades.Adm;
import br.ifpe.model.util.erro.ErroSistema;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author izaquias
 */
@ManagedBean(name = "admBean")
@SessionScoped
public class AdmBean implements CrudBean<Adm> {

    private Adm adm;
    private AdmModel model;

    public AdmBean() {
    }

    @PostConstruct
    public void iniciar() {
        adm = new Adm();
        try {
            model = AdmModel.getInstance();
        } catch (ErroSistema ex) {
            Logger.getLogger(AdmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String inserirOUatualizar() throws ErroSistema {
        model.inserirOUatualizar(this.adm);
        return "menuAdm.xhtml";
    }

    @Override
    public Adm buscar() throws ErroSistema {
        return model.buscar(this.adm);
    }

    @Override
    public String buscarTodos() throws ErroSistema {
        model.buscarTodos();
        return "vizualizaAdm.xhtml";
    }

    @Override
    public String deletar() throws ErroSistema {
        model.deletar(this.adm);
        return "cadastraAdm.xhtml";
    }

    public Adm getAdm() {
        if (adm == null) {
            adm = new Adm();
        }
        return adm;
    }

    public void setAdm(Adm adm) {
        this.adm = adm;
    }

    public AdmModel getModel() {
        return model;
    }

    public void setModel(AdmModel model) {
        this.model = model;
    }

}
