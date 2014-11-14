package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.PedidoVenda;
import frentecaixa.model.ItemVenda;
import frentecaixa.model.Produto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoVendaDAO {

    private Session session;
    private Transaction trans;
    private List<PedidoVenda> listaPedidoVenda;

    public void inserirPedidoVenda(PedidoVenda v) {
        session = HibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        try {
            List<ItemVenda> carrinhoCompras = v.getItemVenda();
            for (ItemVenda c : carrinhoCompras) {
                ProdutoDAO prodDAO = new ProdutoDAO();
                Produto prod = new Produto();
                prod.setCodProduto(c.getProduto().getCodProduto());
                prod.setNome(c.getProduto().getNome());
                prod.setPreco(c.getProduto().getPreco());
               
                prod.setEstoque(c.getProduto().getEstoque()- c.getQuantItemVenda());
                if(prod.getEstoque() < 0){
                    
                }
                else{
                prodDAO.editarProduto(prod);
                
                }
            }
            session.save(v);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao gravar Venda: " + e.getMessage());
        } finally {
            session.close();
        }
     
    }

    public void editarPedidoVenda( PedidoVenda pedidovenda ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.update(pedidovenda);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally{
            session.close();
        }
    }

    public void excluirPedidoVenda( PedidoVenda pedidovenda ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.delete(pedidovenda);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<PedidoVenda> getLista() {
    	session = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = session.createCriteria(PedidoVenda.class);
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.listaPedidoVenda = cri.list();     
        session.close();
        return listaPedidoVenda;
    }
    
}
