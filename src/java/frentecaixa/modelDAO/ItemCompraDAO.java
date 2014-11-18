package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.Fornecedor;
import frentecaixa.model.ItemCompra;
import frentecaixa.model.PedidoCompra;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ItemCompraDAO {

    private Session sessao;
    private Transaction trans;
    private List<ItemCompra> list;

    public void remover(ItemCompra p) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        try {
            sessao.delete(p);
            trans.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao deletar ItemCompra: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<ItemCompra> getList(PedidoCompra compra) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = sessao.createCriteria(ItemCompra.class).add(Restrictions.eq("compra", compra));
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.list = cri.list();
        sessao.close();
        return list;
    }
    
    public void AtualizaEstoqueItens(ItemCompra item, String tipo)
    {
        ProdutoDAO prodDAO = new ProdutoDAO();
        if (null != tipo)switch (tipo) {
            case "diminuir":
                item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantItemCompra());
                break;
            case "aumentar": 
                item.getProduto().setEstoque(item.getProduto().getEstoque() + item.getQuantItemCompra());
                break;
        }
        prodDAO.editarProduto(item.getProduto());

    }
    
    public void setaPrecoCotacao(ItemCompra item, Fornecedor fornecedor) throws SQLException 
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/frentecaixa?zeroDateTimeBehavior=convertToNull","root","sysdba");;
        String SQL =
            "SELECT "+
            "	MIN(ItemCotacao.vlrFornecedor) as vlrFornecedor "+
            "FROM ItemCotacao, Cotacao "+
            "WHERE Cotacao.CodCotacao = ItemCotacao.cotacao_CodCotacao "+
            "  AND Cotacao.codProduto = '"+item.getProduto().getCodProduto().toString()+"'"+
            "  AND ItemCotacao.fornecedor_CodFornecedor = '"+fornecedor.getCodPessoa().toString()+"'";
        
        try
        {
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery(SQL);

            Float precoCompra = 0.f;
            if ( rs != null && rs.next())
            {  
                precoCompra = rs.getFloat("vlrFornecedor");
            }

            if ((precoCompra > 0.f) && (precoCompra < item.getProduto().getPreco()))
            {
                /*Se existir cotação para o produto inserido e para o fornecedor selecionado, 
                 Atualiza o preço do produto para o menor preço cotado*/
                item.getProduto().setPreco(precoCompra);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
           con.close(); 
        }
        
        
     
     }
    
    
}
