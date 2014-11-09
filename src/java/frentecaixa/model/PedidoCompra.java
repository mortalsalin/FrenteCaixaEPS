package frentecaixa.model;

import frentecaixa.modelDAO.FornecedorDAO;
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
@Table(name="pedidocompra")
public class PedidoCompra implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CodPedidoCompra;
    @ManyToOne(targetEntity = Fornecedor.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CodFornecedor", nullable = true)
    Fornecedor fornecedor;
    private String Status;
    @Temporal(TemporalType.DATE)
    private Date dtPedido;
    private Float ValorTotal;

    public Integer getCodPedidoCompra() {
        return CodPedidoCompra;
    }

    public void setCodPedidoCompra(Integer pCodigo) {
        this.CodPedidoCompra = pCodigo;
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
    
    public String getStatus() {
        return Status ;
    }
    
    public void setStatus(String pStatus){
        this.Status = pStatus;
    }

    public void setFornecedor(Fornecedor forn)
    {
        this.fornecedor = forn;
    }
    
    public Fornecedor getFornecedor()
    {
        return this.fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 4;
        hash = 97 * hash + Objects.hashCode(this.CodPedidoCompra);
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
        final PedidoCompra other = (PedidoCompra) obj;
        if (!Objects.equals(this.CodPedidoCompra, other.CodPedidoCompra)) {
            return false;
        }
        return true;
    }
}
