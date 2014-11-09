package frentecaixa.model;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedidoCompra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CodItemPedidoCompra")
    int CodItemPedidoCompra;
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CodProduto", nullable = true)
    Produto Produto;
    @ManyToOne(targetEntity = PedidoCompra.class, fetch = FetchType.EAGER)
    @JoinColumn(name="CodPedidoCompra", nullable = true)
    PedidoCompra PedidoCompra;
    private Float VlrUnit, VlrTotal, Qtde;
    
    public int getCodItemPedidoCompra() {
        return CodItemPedidoCompra;
    }

    public void setCodItemPedidoCompra(int pCodigo) {
        this.CodItemPedidoCompra = pCodigo;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto pProduto) {
        this.Produto = pProduto;
    }


    public PedidoCompra getPedidoCompra() {
        return PedidoCompra;
    }

    public void setPedidoCompra(PedidoCompra pPedidoCompra) {
        this.PedidoCompra = pPedidoCompra;
    }
    
    public Float getVlrUnit(){
        return this.VlrUnit;
    }
    
    public void setVlrUnit(Float pVlrUnit){
        this.VlrUnit = pVlrUnit;
    }
    
    public Float getQtde(){
        return this.Qtde;
    }
    
    public void setQtde(Float pQtde){
        this.Qtde = pQtde;
    }
    
     public Float getVlrTotal(){
        return this.VlrTotal;
    }
    
    public void setVlrTotal(Float pVlrTotal){
        this.VlrTotal = pVlrTotal;
    }

}
