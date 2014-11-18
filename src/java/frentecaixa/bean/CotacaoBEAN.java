package frentecaixa.bean;

import frentecaixa.model.Cotacao;
import frentecaixa.model.ItemCotacao;
import frentecaixa.modelDAO.CotacaoDAO;
import frentecaixa.model.ItemCotacao;
import frentecaixa.modelDAO.ItemCotacaoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CotacaoBEAN {

    private Cotacao cotacao = new Cotacao();
    private CotacaoDAO cotacaoDAO = new CotacaoDAO();
    private List<Cotacao> listaCotacao;
    private ItemCotacao itemCotacao;
    private ArrayList<ItemCotacao> carrinhoCompras;
    private String mensagem = "";
    
    @PostConstruct
    private void init() {
        setCarrinhoCompras((ArrayList<ItemCotacao>) new ArrayList());
        setItemCotacao(new ItemCotacao());
    }

    public CotacaoBEAN() {
        init();
    }
    
    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }

    public ItemCotacao getItemCotacao() {
        return itemCotacao;
    }

    public void setItemCotacao(ItemCotacao itemCotacao) {
        this.itemCotacao = itemCotacao;
    }

    public ArrayList<ItemCotacao> getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(ArrayList<ItemCotacao> carrinhoCompras) {
        this.carrinhoCompras = carrinhoCompras;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String inserirCotacao(){
        cotacao.setItemCotacao(carrinhoCompras);
        cotacaoDAO.inserirCotacao(cotacao);
        this.limparCampos();
        return "consulta_cotacao";
    }
    
    public String editarCotacao(){
        cotacaoDAO.editarCotacao(cotacao, carrinhoCompras);
        return "consulta_cotacao";
    }
        
    public String excluirCotacao(Cotacao c){
        cotacaoDAO.excluirCotacao(c);
        return "consulta_cotacao";
    }

    public List listarCotacao(){
        listaCotacao = cotacaoDAO.getLista();
        return this.listaCotacao;
    }

    public String carregaCotacao(Cotacao c){
        limparCampos();
        setCotacao(c);
        ItemCotacaoDAO itemDAO = new ItemCotacaoDAO();
        
        if (listaCotacao.contains(cotacao))
        {
            carrinhoCompras.addAll(itemDAO.getList(c));
        }
        return "cadastro_cotacao";
    }
    
    public String novoCotacao(){
        this.limparCampos();
        cotacao.setCodCotacao(null);
        cotacao.setDtCotacao(null);
        cotacao.setProduto(null);
        return "cadastro_cotacao";
    }

    public String confirmarCotacao(){
        if (listaCotacao.contains(cotacao)) {
            return editarCotacao();
        }
        return inserirCotacao();
    }
    
    public String adicionarAoCarrinho() {
        setMensagem("");
        
        boolean jaInseriu = false;
        for (ItemCotacao c : getCarrinhoCompras())
        {
           if (getItemCotacao().getFornecedor() == c.getFornecedor())
           {
               jaInseriu = true;
               break;
            }
        }

        if (getItemCotacao().getFornecedor() == null || getItemCotacao().getVlrFornecedor() == null) {
            setMensagem("Todos os campos precisam ser preenchidos");
        } 
        else if (jaInseriu){
            setMensagem("Este fornecedor já foi inserido!");
        }
        else {
            this.getItemCotacao().setVlrFornecedor(getItemCotacao().getVlrFornecedor());
            this.getItemCotacao().setCotacao(getCotacao());
            this.getCarrinhoCompras().add(getItemCotacao());
            }
        this.setItemCotacao(new ItemCotacao());
        return "cadastro_cotacao";
    }
    
    public String removerCarrinho(ItemCotacao itemCotacao) {
        System.out.println("Removendo...");
        ItemCotacaoDAO itemDAO = new ItemCotacaoDAO();
        carrinhoCompras.remove(itemCotacao);
        
        if (listaCotacao.contains(cotacao))
        {
            List<ItemCotacao> itensCotacao = itemDAO.getList(cotacao);
            if (itensCotacao.contains(itemCotacao)){
                itemDAO.remover(itemCotacao);
            }
        }
        return "cadastro_cotacao";
    }
    
    public String limparCampos() {
        setItemCotacao(new ItemCotacao());
        setCotacao(new Cotacao());
        setMensagem("");
        getCarrinhoCompras().clear();
        return "cadastro_cotacao";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.cotacao);
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
        final CotacaoBEAN other = (CotacaoBEAN) obj;
        return Objects.equals(this.cotacao, other.cotacao);
    }
    
}
