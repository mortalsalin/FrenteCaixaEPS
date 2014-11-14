package frentecaixa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda implements Serializable{
    @Id
    @GeneratedValue
    private Integer CodItemVenda;
    
    @ManyToOne
    private Produto produto;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PedidoVenda venda;
    
    private Integer quantItemVenda;
    private Float vltTotalProduto;
    
     public Integer getCodItemVenda() {
        return CodItemVenda;
    }

    public void setCodItemVenda(Integer CodItemVenda) {
        this.CodItemVenda = CodItemVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PedidoVenda getVenda() {
        return venda;
    }

    public void setVenda(PedidoVenda venda) {
        this.venda = venda;
    }

    public Integer getQuantItemVenda() {
        return quantItemVenda;
    }

    public void setQuantItemVenda(Integer quantItemVenda) {
        this.quantItemVenda = quantItemVenda;
    }
    
    public Float getVltTotalProduto() {
        return vltTotalProduto;
    }

    public void setVltTotalProduto(Float vltTotalProduto) {
        this.vltTotalProduto = vltTotalProduto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.getCodItemVenda());
        hash = 83 * hash + Objects.hashCode(this.getProduto());
        hash = 83 * hash + Objects.hashCode(this.getVenda());
        hash = 83 * hash + Objects.hashCode(this.getQuantItemVenda());
        hash = 83 * hash + Objects.hashCode(this.getVltTotalProduto());
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
        final ItemVenda other = (ItemVenda) obj;
        if (!Objects.equals(this.CodItemVenda, other.CodItemVenda)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        if (!Objects.equals(this.quantItemVenda, other.quantItemVenda)) {
            return false;
        }
        if (!Objects.equals(this.vltTotalProduto, other.vltTotalProduto)) {
            return false;
        }
        return true;
    }

    public ItemVenda() {
    }

    
}
