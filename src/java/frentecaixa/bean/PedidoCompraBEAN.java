package frentecaixa.bean;

import frentecaixa.model.ItemCompra;
import frentecaixa.model.ItemCompra;
import frentecaixa.model.ItemCompra;
import frentecaixa.model.PedidoCompra;
import frentecaixa.model.PedidoCompra;
import frentecaixa.modelDAO.ItemCompraDAO;
import frentecaixa.modelDAO.ItemCompraDAO;
import frentecaixa.modelDAO.PedidoCompraDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoCompraBEAN {

    private PedidoCompra pedidocompra = new PedidoCompra();
    private PedidoCompraDAO pedidocompraDAO = new PedidoCompraDAO();
    private List<PedidoCompra> listaPedidoCompra;
    private ItemCompra itemCompra;
    private ArrayList<ItemCompra> carrinhoCompras;
    private String mensagem = "";
    private Float valorTotal = 0.f;
    
    @PostConstruct
    private void init() {
        setCarrinhoCompras((ArrayList<ItemCompra>) new ArrayList());
        setItemCompra(new ItemCompra());
    }

    public PedidoCompraBEAN() {
    }

    public PedidoCompra getPedidoCompra() {
        return pedidocompra;
    }

    public void setPedidoCompra(PedidoCompra pedidocompra) {
        this.pedidocompra = pedidocompra;
    }
    
    public ItemCompra getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(ItemCompra itemCompra) {
        this.itemCompra = itemCompra;
    }

    public ArrayList<ItemCompra> getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(ArrayList<ItemCompra> carrinhoCompras) {
        this.carrinhoCompras = carrinhoCompras;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String inserirPedidoCompra(){
        pedidocompra.setItemCompra(carrinhoCompras);
        pedidocompra.setValorTotal(valorTotal);
        pedidocompraDAO.inserirPedidoCompra(pedidocompra);
        this.limparCampos();
        return "consulta_pedidocompra";
    }
    
    public String editarPedidoCompra(){
        pedidocompraDAO.editarPedidoCompra(pedidocompra, carrinhoCompras);
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
        limparCampos();
        setPedidoCompra(c);
        ItemCompraDAO itemDAO = new ItemCompraDAO();
        
        if (listaPedidoCompra.contains(pedidocompra))
        {
            carrinhoCompras.addAll(itemDAO.getList(c));
            for (ItemCompra item : carrinhoCompras) {
                this.setValorTotal((Float) (this.getValorTotal() + item.getVltTotalProduto()));
            }
        }
        return "cadastro_pedidocompra";
    }
    
    public String novoPedidoCompra(){
        this.limparCampos();
        pedidocompra.setCodPedidoCompra(null);
        pedidocompra.setFornecedor(null);
        pedidocompra.setDtPedido(null);
        pedidocompra.setValorTotal(0.0f);
        return "cadastro_pedidocompra";
    }

    public String confirmarPedidoCompra(){
        if (listaPedidoCompra.contains(pedidocompra)) {
            return editarPedidoCompra();
        }
        return inserirPedidoCompra();
    }
    
    public String adicionarAoCarrinho() throws SQLException {
        setMensagem("");
        if (getItemCompra().getProduto() == null || getItemCompra().getQuantItemCompra() == null) {
            setMensagem("Todos os campos precisam ser preenchidos");
        } else 
        {
            ItemCompraDAO itemDAO = new ItemCompraDAO();
            itemDAO.setaPrecoCotacao(itemCompra, pedidocompra.getFornecedor());
            this.getItemCompra().setVltTotalProduto(getItemCompra().getQuantItemCompra() * getItemCompra().getProduto().getPreco());
            this.getItemCompra().setCompra(getPedidoCompra());
            this.getCarrinhoCompras().add(getItemCompra());
            this.setValorTotal((Float) (this.getValorTotal() + getItemCompra().getVltTotalProduto()));
            itemDAO.AtualizaEstoqueItens(getItemCompra(), "aumentar");
            
        }
        this.setItemCompra(new ItemCompra());
        return "cadastro_pedidocompra";
    }
    
    public String removerCarrinho(ItemCompra itemCompra) {
        System.out.println("Removendo...");
        this.setValorTotal((Float) (this.getValorTotal() - itemCompra.getVltTotalProduto()));
        ItemCompraDAO itemDAO = new ItemCompraDAO();
        itemDAO.AtualizaEstoqueItens(itemCompra, "diminuir");
        carrinhoCompras.remove(itemCompra);
        
        if (listaPedidoCompra.contains(pedidocompra))
        {
            List<ItemCompra> itensCompra = itemDAO.getList(pedidocompra);
            if (itensCompra.contains(itemCompra)){
                itemDAO.remover(itemCompra);
            }
        }
        return "cadastro_pedidocompra";
    }
    
    public String limparCampos() {
        setItemCompra(new ItemCompra());
        setPedidoCompra(new PedidoCompra());
        setMensagem("");
        getCarrinhoCompras().clear();
        this.setValorTotal((Float) 0.f);
        return "cadastro_pedidocompra";
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
}
