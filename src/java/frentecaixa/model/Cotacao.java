package frentecaixa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="cotacao")
public class Cotacao implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CodCotacao;
    
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CodProduto", nullable = false)
    private Produto produto;
    
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cotacao")
    private List<ItemCotacao> itemCotacao;
    
    @Temporal(TemporalType.DATE)
    private Date dtCotacao;

    public Integer getCodCotacao() {
        return CodCotacao;
    }

    public void setCodCotacao(Integer CodCotacao) {
        this.CodCotacao = CodCotacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<ItemCotacao> getItemCotacao() {
        return itemCotacao;
    }

    public void setItemCotacao(List<ItemCotacao> itemCotacao) {
        this.itemCotacao = itemCotacao;
    }

    public Date getDtCotacao() {
        return dtCotacao;
    }

    public void setDtCotacao(Date dtCotacao) {
        this.dtCotacao = dtCotacao;
    }
   
    
    @Override
    public int hashCode() {
        int hash = 9;
        hash = 97 * hash + Objects.hashCode(this.getCodCotacao());
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
        final Cotacao other = (Cotacao) obj;
        if (!Objects.equals(this.CodCotacao, other.CodCotacao)) {
            return false;
        }
        return true;
    }
    
    public Cotacao() {
    }
    
}
