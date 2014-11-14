package frentecaixa.bean;

import frentecaixa.model.PedidoVenda;
import frentecaixa.modelDAO.PedidoVendaDAO;
import frentecaixa.model.ItemVenda;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoVendaBEAN {

    private PedidoVenda pedidovenda = new PedidoVenda();
    private PedidoVendaDAO pedidovendaDAO = new PedidoVendaDAO();
    private List<PedidoVenda> listaPedidoVenda;
    private ItemVenda itemVenda;
    private ArrayList<ItemVenda> carrinhoCompras;
    private String mensagem = "";
    private Float valorTotal = 0.f;
    
    @PostConstruct
    private void init() {
        setCarrinhoCompras((ArrayList<ItemVenda>) new ArrayList());
        setItemVenda(new ItemVenda());
    }

    public PedidoVendaBEAN() {
        init();
    }
    
    public PedidoVenda getPedidovenda() {
        return pedidovenda;
    }

    public void setPedidovenda(PedidoVenda pedidovenda) {
        this.pedidovenda = pedidovenda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public ArrayList<ItemVenda> getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(ArrayList<ItemVenda> carrinhoCompras) {
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
    
    public String inserirPedidoVenda(){
        pedidovenda.setValorTotal(valorTotal);
        pedidovenda.setItemVenda(carrinhoCompras);
        pedidovendaDAO.inserirPedidoVenda(pedidovenda);
        this.limparCampos();
        return "consulta_pedidovenda";
    }
    
    public String editarPedidoVenda(){
        pedidovendaDAO.editarPedidoVenda(getPedidovenda());
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
        setPedidovenda(c);
        return "cadastro_pedidovenda";
    }
    
    public String novoPedidoVenda(){
        getPedidovenda().setCodPedidoVenda(null);
        getPedidovenda().setCliente(null);
        getPedidovenda().setDtPedido(null);
        getPedidovenda().setValorTotal(0.0f);
        return "cadastro_pedidovenda";
    }

    public String confirmarPedidoVenda(){
        if (listaPedidoVenda.contains(getPedidovenda())) {
            return editarPedidoVenda();
        }
        return inserirPedidoVenda();
    }
    
    public String adicionarAoCarrinho() {
        setMensagem("");
        if (getItemVenda().getProduto() == null || getItemVenda().getQuantItemVenda() == null) {
            setMensagem("Todos os campos precisam ser preenchidos");
        } else {
            this.getItemVenda().setVltTotalProduto(getItemVenda().getQuantItemVenda() * getItemVenda().getProduto().getPreco());
            this.getItemVenda().setVenda(getPedidovenda());
            if (getItemVenda().getProduto().getEstoque() - getItemVenda().getQuantItemVenda() < 0) {
                setMensagem("O produto: " + getItemVenda().getProduto().getNome() + " não contém " + getItemVenda().getQuantItemVenda() + " peças no estoque");
            } else {
                this.getCarrinhoCompras().add(getItemVenda());
                this.setValorTotal((Float) (this.getValorTotal() + getItemVenda().getVltTotalProduto()));
            }
        }
        this.setItemVenda(new ItemVenda());
        return "cadastro_pedidovenda";
    }
    
    public String limparCampos() {
        setItemVenda(new ItemVenda());
        setPedidovenda(new PedidoVenda());
        setMensagem("");
        getCarrinhoCompras().clear();
        this.setValorTotal((Float) 0.f);
        return "cadastro_pedidovenda";
    }
    
    public String removerCarrinho(ItemVenda itemVenda) {
        System.out.println("Removendo...");
        getCarrinhoCompras().remove(itemVenda);
        setValorTotal((Float) (getValorTotal() - itemVenda.getVltTotalProduto()));
        return "cadastro_pedidovenda";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.getPedidovenda());
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
