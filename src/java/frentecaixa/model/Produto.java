package frentecaixa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CodProduto;
    private String Nome;
    private Float Preco;
    private Float Estoque;

    public Integer getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(Integer pCodigo) {
        this.CodProduto = pCodigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    
      public Float getPreco() {
        return Preco;
    }

    public void setPreco(Float pPreco) {
        this.Preco = pPreco;
    }
    
    public Float getEstoque() {
        return Estoque;
    }

    public void setEstoque(Float pEstoque) {
        this.Estoque = pEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 4;
        hash = 97 * hash + Objects.hashCode(this.CodProduto);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.CodProduto, other.CodProduto)) {
            return false;
        }
        return true;
    }
}
