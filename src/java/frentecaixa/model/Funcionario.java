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
    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
   
    @NotEmpty
    private String loginFuncionario;
    @NotEmpty
    private String senhaFuncionario;
    private String tipo;
    private Float salarioFuncionario;

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

    public Float getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(Float salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.getCodPessoa());
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.loginFuncionario, other.loginFuncionario)) {
            return false;
        }
        if (!Objects.equals(this.senhaFuncionario, other.senhaFuncionario)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.salarioFuncionario, other.salarioFuncionario)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return "Funcionário{" + "Código=" + this.getCodPessoa()+ ", Nome=" + this.getNome() + '}';
    }


}