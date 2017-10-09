/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.model.util.conexao;

import br.ifpe.model.util.erro.ErroSistema;
import javax.xml.parsers.FactoryConfigurationError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Izack
 */
public class Conexao {

    private SessionFactory factory;
    private static Conexao conexao = null;
    private Session session;

    private Conexao() throws ErroSistema {
          if (factory == null) {
            try {
                factory = new Configuration().configure().buildSessionFactory();

            } catch (FactoryConfigurationError ex) {
                throw new ErroSistema("Erro ao construir fabrica de conexão!", ex);
            }
        }
    }

    public SessionFactory getFactory() throws ErroSistema {
      
        return factory;
    }

    public void fecharSessao() throws ErroSistema {
        if (session != null && session.isOpen()) {
            try {
                session.close();
                session = null;
            } catch (HibernateException ex) {
                throw new ErroSistema("Erro ao fechar conexão com o banco de dados!", ex);
            }
        }
    }

    public static Conexao getInstance() throws ErroSistema {
        if (conexao == null) {
            try {
                conexao = new Conexao();
            } catch (HibernateException ex) {
                throw new ErroSistema("Erro ao criar conexão com o banco de dados!", ex);
            }
        }
        return conexao;
    }

}
