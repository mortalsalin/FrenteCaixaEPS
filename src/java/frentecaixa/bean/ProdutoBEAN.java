package frentecaixa.bean;

import frentecaixa.model.Produto;
import frentecaixa.modelDAO.ProdutoDAO;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProdutoBEAN {

    private Produto produto = new Produto();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<Produto> listaProduto;

    public ProdutoBEAN() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto pProduto) {
        this.produto = pProduto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.produto);
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
        final ProdutoBEAN other = (ProdutoBEAN) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

    public String inserirProduto(){
        produtoDAO.inserirProduto(produto);
        return "consulta_produto";
    }
    
    public String editarProduto(){
        produtoDAO.editarProduto(produto);
        return "consulta_produto";
    }
        
    public String excluirProduto(Produto c){
        produtoDAO.excluirProduto(c);
        return "consulta_produto";
    }

    public List listarProduto(){
        listaProduto = produtoDAO.getLista();
        return this.listaProduto;
    }

    public String carregaProduto(Produto c){
        produto = c;
        return "cadastro_produto";
    }
    
    public String novoProduto(){
        produto.setCodProduto(null);
        produto.setNome(null);
        produto.setEstoque(0.0f);
        produto.setPreco(0.0f);
        return "cadastro_produto";
    }

    public String confirmarProduto(){
        if (listaProduto.contains(produto)) {
            return editarProduto();
        }
        return inserirProduto();
    }
}
