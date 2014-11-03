package frentecaixa.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="CodCliente", referencedColumnName = "CodPessoa")
@Table(name="cliente")
public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;
    
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String pCPF) {
        this.CPF = pCPF;
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
        return "Cliente{" + "Código=" + this.getCodPessoa()+ ", Nome=" + this.getNome() + '}';
    }

}