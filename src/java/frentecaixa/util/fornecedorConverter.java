package frentecaixa.util;
import frentecaixa.model.Fornecedor;
import frentecaixa.modelDAO.FornecedorDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.convert.FacesConverter;

@FacesConverter("fornecedorConverter")
public class fornecedorConverter implements Converter {
    
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private List<Fornecedor> listaFornecedors = fornecedorDAO.getLista();
    private Fornecedor fornecedor = new Fornecedor();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        for (Fornecedor listaFornecedor : listaFornecedors) {
            if (listaFornecedor.getCodPessoa().equals( Integer.parseInt(valor))) {
                return listaFornecedor;
            }
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão.", "Fornecedor inexistente na lista");
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        fornecedor = (Fornecedor) value;
        return String.valueOf(fornecedor.getCodPessoa());
    }
    
}