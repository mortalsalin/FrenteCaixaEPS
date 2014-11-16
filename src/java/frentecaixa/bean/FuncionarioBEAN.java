package frentecaixa.bean;

import frentecaixa.model.Funcionario;
import frentecaixa.modelDAO.FuncionarioDAO;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FuncionarioBEAN {

    private Funcionario funcionario = new Funcionario();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private List<Funcionario> listaFuncionario;

    public FuncionarioBEAN() {
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String inserirFuncionario(){
        funcionarioDAO.inserirFuncionario(funcionario);
        return "consulta_funcionario";
    }
    
    public String editarFuncionario(){
        funcionarioDAO.editarFuncionario(funcionario);
        return "consulta_funcionario";
    }
        
    public String excluirFuncionario(Funcionario c){
        funcionarioDAO.excluirFuncionario(c);
        return "consulta_funcionario";
    }

    public List listarFuncionario(){
        listaFuncionario = funcionarioDAO.getLista();
        return this.listaFuncionario;
    }

    public String carregaFuncionario(Funcionario c){
        funcionario = c;
        return "cadastro_funcionario";
    }
    
    public String novoFuncionario(){
        funcionario.setCodPessoa(null);
        funcionario.setNome(null);
        funcionario.setEmail(null);
        funcionario.setLoginFuncionario(null);
        funcionario.setSenhaFuncionario(null);
        funcionario.setSalarioFuncionario(0.f);
        funcionario.setTipo(null);
        return "cadastro_funcionario";
    }

    public String confirmarFuncionario(){
        if (listaFuncionario.contains(funcionario)) {
            return editarFuncionario();
        }
        return inserirFuncionario();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 9 * hash + Objects.hashCode(this.funcionario);
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
        final FuncionarioBEAN other = (FuncionarioBEAN) obj;
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return true;
    }
    
    

}

