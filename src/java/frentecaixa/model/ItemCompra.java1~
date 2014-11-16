package frentecaixa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCompra implements Serializable{
    @Id
    @GeneratedValue
    private Integer CodItemCompra;
    
    @ManyToOne
    private Produto produto;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PedidoCompra compra;
    
    private Integer quantItemCompra;
    private Float vltTotalProduto;
    
     public Integer getCodItemCompra() {
        return CodItemCompra;
    }

    public void setCodItemCompra(Integer CodItemCompra) {
        this.CodItemCompra = CodItemCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PedidoCompra getCompra() {
        return compra;
    }

    public void setCompra(PedidoCompra compra) {
        this.compra = compra;
    }

    public Integer getQuantItemCompra() {
        return quantItemCompra;
    }

    public void setQuantItemCompra(Integer quantItemCompra) {
        this.quantItemCompra = quantItemCompra;
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
        hash = 83 * hash + Objects.hashCode(this.getCodItemCompra());
        hash = 83 * hash + Objects.hashCode(this.getProduto());
        hash = 83 * hash + Objects.hashCode(this.getCompra());
        hash = 83 * hash + Objects.hashCode(this.getQuantItemCompra());
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
        final ItemCompra other = (ItemCompra) obj;
        if (!Objects.equals(this.CodItemCompra, other.CodItemCompra)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        if (!Objects.equals(this.quantItemCompra, other.quantItemCompra)) {
            return false;
        }
        if (!Objects.equals(this.vltTotalProduto, other.vltTotalProduto)) {
            return false;
        }
        return true;
    }

    public ItemCompra() {
    }

    
}
