package frentecaixa.model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import frentecaixa.modelDAO.ClienteDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.convert.FacesConverter;

@FacesConverter("clienteConverter")
public class clienteConverter implements Converter {
    
    private ClienteDAO clienteDAO = new ClienteDAO();
    private List<Cliente> listaClientes = clienteDAO.getLista();
    private Cliente cliente = new Cliente();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        for (Cliente listaCliente : listaClientes) {
            if (listaCliente.getCodPessoa().equals( Integer.parseInt(valor))) {
                return listaCliente;
            }
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão.", "Cliente inexistente na lista");
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        cliente = (Cliente) value;
        return String.valueOf(cliente.getCodPessoa());
    }
    
}
