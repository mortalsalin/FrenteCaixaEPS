package frentecaixa.bean;

import frentecaixa.model.PedidoCompra;
import frentecaixa.modelDAO.PedidoCompraDAO;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoCompraBEAN {

    private PedidoCompra pedidocompra = new PedidoCompra();
    private PedidoCompraDAO pedidocompraDAO = new PedidoCompraDAO();
    private List<PedidoCompra> listaPedidoCompra;

    public PedidoCompraBEAN() {
    }

    public PedidoCompra getPedidoCompra() {
        return pedidocompra;
    }

    public void setPedidoCompra(PedidoCompra pedidocompra) {
        this.pedidocompra = pedidocompra;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.pedidocompra);
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
        final PedidoCompraBEAN other = (PedidoCompraBEAN) obj;
        if (!Objects.equals(this.pedidocompra, other.pedidocompra)) {
            return false;
        }
        return true;
    }

    public String inserirPedidoCompra(){
        pedidocompraDAO.inserirPedidoCompra(pedidocompra);
        return "consulta_pedidocompra";
    }
    
    public String editarPedidoCompra(){
        pedidocompraDAO.editarPedidoCompra(pedidocompra);
        return "consulta_pedidocompra";
    }
        
    public String excluirPedidoCompra(PedidoCompra c){
        pedidocompraDAO.excluirPedidoCompra(c);
        return "consulta_pedidocompra";
    }

    public List listarPedidoCompra(){
        listaPedidoCompra = pedidocompraDAO.getLista();
        return this.listaPedidoCompra;
    }

    public String carregaPedidoCompra(PedidoCompra c){
        pedidocompra = c;
        return "cadastro_pedidocompra";
    }
    
    public String novoPedidoCompra(){
        //pedidocompra.setCodPedidoCompra(null);
        pedidocompra.setFornecedor(null);
        pedidocompra.setDtPedido(null);
        pedidocompra.setStatus(null);
        pedidocompra.setValorTotal(0.0f);
        return "cadastro_pedidocompra";
    }

    public String confirmarPedidoCompra(){
        if (listaPedidoCompra.contains(pedidocompra)) {
            return editarPedidoCompra();
        }
        return inserirPedidoCompra();
    }
}
