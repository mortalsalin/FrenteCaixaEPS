package frentecaixa.bean;

import frentecaixa.model.Cliente;
import frentecaixa.modelDAO.ClienteDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class ClienteBEAN {

    private Cliente cliente = new Cliente();
    private ClienteDAO ClienteDAO = new ClienteDAO();
    private List<Cliente> listaCliente;

    public ClienteBEAN() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente apostador) {
        this.cliente = apostador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClienteBEAN other = (ClienteBEAN) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

    public String inserirCliente(){
        ClienteDAO.inserirCliente(cliente);
        return "consulta_cliente";
    }
    
    public String editarCliente(){
        ClienteDAO.editarCliente(cliente);
        return "consulta_cliente";
    }
        
    public String excluirCliente(Cliente c){
        ClienteDAO.excluirCliente(c);
        return "consulta_cliente";
    }

    public List listarCliente(){
        listaCliente = ClienteDAO.getLista();
        return this.listaCliente;
    }

    public String carregaCliente(Cliente c){
        cliente = c;
        return "cadastro_cliente";
    }
    
    public String novoCliente(){
        cliente.setCodPessoa(null);
        cliente.setNome(null);       
        cliente.setEmail(null);
        cliente.setCPF(null);
        return "cadastro_cliente";
    }

    public String confirmarCliente(){
        if (listaCliente.contains(cliente)) {
            return editarCliente();
        }
        return inserirCliente();
    }
    
}
