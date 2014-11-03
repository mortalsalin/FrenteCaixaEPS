package frentecaixa.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

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
    @Temporal(TemporalType.DATE)
    private Date dtPedido;
    private Float ValorTotal;

    public Integer getCodPedidoVenda() {
        return CodPedidoVenda;
    }

    public void setCodPedidoVenda(Integer pCodigo) {
        this.CodPedidoVenda = pCodigo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente pCliente) {
        this.cliente = pCliente;
    }
    
      public Float getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(Float pValorTotal) {
        this.ValorTotal = pValorTotal;
    }
    
    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date pData) {
        this.dtPedido = pData;
    }

    @Override
    public int hashCode() {
        int hash = 4;
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
}
