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
@Table(name="pedidovenda")
public class PedidoVenda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CodPedidoVenda;
    
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CodCliente", nullable = true)
    private Cliente cliente;
    
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "venda")
    private List<ItemVenda> itemVenda;
    
    @Temporal(TemporalType.DATE)
    private Date dtPedido;
    private Float ValorTotal;


    public Integer getCodPedidoVenda() {
        return CodPedidoVenda;
    }

    public void setCodPedidoVenda(Integer CodPedidoVenda) {
        this.CodPedidoVenda = CodPedidoVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Float getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Float ValorTotal) {
        this.ValorTotal = ValorTotal;
    }
    
    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }
    
    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(List<ItemVenda> itemVenda) {
        this.itemVenda = itemVenda;
    }
    
    @Override
    public int hashCode() {
        int hash = 9;
        hash = 97 * hash + Objects.hashCode(this.CodPedidoVenda);
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
        final PedidoVenda other = (PedidoVenda) obj;
        if (!Objects.equals(this.CodPedidoVenda, other.CodPedidoVenda)) {
            return false;
        }
        return true;
    }
    
    public PedidoVenda() {
    }
    
}
