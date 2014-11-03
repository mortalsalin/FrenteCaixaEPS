package frentecaixa.bean;

import frentecaixa.model.PedidoVenda;
import frentecaixa.modelDAO.PedidoVendaDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named("pedidoVendaBEAN")
@ManagedBean
@SessionScoped
public class PedidoVendaBEAN implements Serializable {

    private PedidoVenda pedidovenda = new PedidoVenda();
    private PedidoVendaDAO pedidovendaDAO = new PedidoVendaDAO();
    private List<PedidoVenda> listaPedidoVenda;

    public PedidoVenda getPedidoVenda() {
        return pedidovenda;
    }

    public void setPedidoVenda(PedidoVenda pedidovenda) {
        this.pedidovenda = pedidovenda;
    }

    public String inserirPedidoVenda(){
        pedidovendaDAO.inserirPedidoVenda(pedidovenda);
        return "consulta_pedidovenda";
    }
    
    public String editarPedidoVenda(){
        pedidovendaDAO.editarPedidoVenda(pedidovenda);
        return "consulta_pedidovenda";
    }
        
    public String excluirPedidoVenda(PedidoVenda c){
        pedidovendaDAO.excluirPedidoVenda(c);
        return "consulta_pedidovenda";
    }

    public List listarPedidoVenda(){
        listaPedidoVenda = pedidovendaDAO.getLista();
        return this.listaPedidoVenda;
    }

    public String carregaPedidoVenda(PedidoVenda c){
        pedidovenda = c;
        return "cadastro_pedidovenda";
    }
    
    public String novoPedidoVenda(){
        pedidovenda.setCodPedidoVenda(null);
        pedidovenda.setCliente(null);
        pedidovenda.setDtPedido(null);
        pedidovenda.setValorTotal(null);
        return "cadastro_pedidovenda";
    }
    
    public String confirmarPedidoVenda()
    {
        if (listaPedidoVenda.contains(pedidovenda)) {
            return editarPedidoVenda();
        }
        return inserirPedidoVenda();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.pedidovenda);
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
        final PedidoVendaBEAN other = (PedidoVendaBEAN) obj;
        if (!Objects.equals(this.pedidovenda, other.pedidovenda)) {
            return false;
        }
        return true;
    }
}
