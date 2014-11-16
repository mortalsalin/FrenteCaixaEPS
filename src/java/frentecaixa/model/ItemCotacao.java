package frentecaixa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCotacao implements Serializable{
    @Id
    @GeneratedValue
    private Integer CodItemCotacao;
    
    @ManyToOne
    private Fornecedor fornecedor;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cotacao cotacao;
    
    private Float vlrFornecedor;

    public Integer getCodItemCotacao() {
        return CodItemCotacao;
    }

    public void setCodItemCotacao(Integer CodItemCotacao) {
        this.CodItemCotacao = CodItemCotacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }

    public Float getVlrFornecedor() {
        return vlrFornecedor;
    }

    public void setVlrFornecedor(Float vlrFornecedor) {
        this.vlrFornecedor = vlrFornecedor;
    }

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.getCodItemCotacao());
        //hash = 83 * hash + Objects.hashCode(this.getFornecedor());
        hash = 83 * hash + Objects.hashCode(this.getCotacao());
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
        final ItemCotacao other = (ItemCotacao) obj;
        if (!Objects.equals(this.CodItemCotacao, other.CodItemCotacao)) {
            return false;
        }
        if (!Objects.equals(this.cotacao, other.cotacao)) {
            return false;
        }
        return true;
    }

    public ItemCotacao() {
    }

    
}
