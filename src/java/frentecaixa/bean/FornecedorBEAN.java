package frentecaixa.bean;

import frentecaixa.model.Fornecedor;
import frentecaixa.modelDAO.FornecedorDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class FornecedorBEAN {

    private Fornecedor fornecedor = new Fornecedor();
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private List<Fornecedor> listaFornecedor;

    public FornecedorBEAN() {
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.fornecedor);
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
        final FornecedorBEAN other = (FornecedorBEAN) obj;
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        return true;
    }

    public String inserirFornecedor(){
        fornecedorDAO.inserirFornecedor(fornecedor);
        return "consulta_fornecedor";
    }
    
    public String editarFornecedor(){
        fornecedorDAO.editarFornecedor(fornecedor);
        return "consulta_fornecedor";
    }
        
    public String excluirFornecedor(Fornecedor c){
        fornecedorDAO.excluirFornecedor(c);
        return "consulta_fornecedor";
    }

    public List listarFornecedor(){
        listaFornecedor = fornecedorDAO.getLista();
        return this.listaFornecedor;
    }

    public String carregaFornecedor(Fornecedor c){
        fornecedor = c;
        return "cadastro_fornecedor";
    }
    
    public String novoFornecedor(){
        fornecedor.setCodPessoa(null);
        fornecedor.setNome(null);
        fornecedor.setEmail(null);
        fornecedor.setCNPJ(null);
        return "cadastro_fornecedor";
    }

    public String confirmarFornecedor(){
        if (listaFornecedor.contains(fornecedor)) {
            return editarFornecedor();
        }
        return inserirFornecedor();
    }

}
