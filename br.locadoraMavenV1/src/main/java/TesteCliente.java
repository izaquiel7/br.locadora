
import br.locadora.model.ClienteModel;
import br.locadora.model.entidade.Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Izaquiel
 */
public class TesteCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ClienteModel cm = ClienteModel.getInstance();
        Cliente c = new Cliente();
        c.setNome("izack");
        c.setEmail("I@email.com");
        c.setSenha("1234");
        cm.cadastra(c);
        
        System.out.println(cm.recupera(c).getNome());
        System.out.println(cm.recupera(c).getEmail());
        System.out.println(cm.recupera(c).getSenha());
        
        for(int i=0; i<cm.recuperaTodos().size(); i++){
            System.out.println(cm.recuperaTodos().get(i));
        }
    }
    
}
