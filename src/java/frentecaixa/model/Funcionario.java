package frentecaixa.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name="CodFuncionario", referencedColumnName = "CodPessoa")
@Table(name="funcionario")
public class Funcionario extends Pessoa{
    private static final long serialVersionUID = 1L;
    
    @NotEmpty
    private Float salarioFuncionario;
    @NotEmpty
    private String loginFuncionario;
    @NotEmpty
    private String senhaFuncionario;
    @NotEmpty
    private String tipo;
    
     public Float getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(Float salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public String getLoginFuncionario() {
        return loginFuncionario;
    }

    public void setLoginFuncionario(String loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (Objects.equals(super.getCodPessoa(), other.getCodPessoa())) {
        } else {
            return false;
        }
        return true;
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.getCodPessoa();
        return hash;
    }
    
    @Override
    public String toString() {
        return "Funcionário{" + "Código=" + this.getCodPessoa()+ ", Nome=" + this.getNome() + '}';
    }


}