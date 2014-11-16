package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.PedidoVenda;
import frentecaixa.model.ItemVenda;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoVendaDAO {

    private Session session;
    private Transaction trans;
    private List<PedidoVenda> listaPedidoVenda;
    private List<ItemVenda> itens;

    public void inserirPedidoVenda(PedidoVenda v) {
        session = HibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        try {
            session.save(v);
            trans.commit();

        } catch (HibernateException e) {
            System.out.println("Erro ao gravar Venda: " + e.getMessage());
        } finally {
            session.close();
        }
     
    }

    public void editarPedidoVenda( PedidoVenda pedidovenda, ArrayList<ItemVenda> itens ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            pedidovenda.setItemVenda(itens);
            Float auxVlrTotal = 0.f;
            for (ItemVenda item : itens){
                auxVlrTotal = auxVlrTotal + item.getVltTotalProduto();
            }
            pedidovenda.setValorTotal(auxVlrTotal);
            
            session.update(pedidovenda);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao editar Venda: " + e.getMessage());
        } finally{
            session.close();
        }
    }

    public void excluirPedidoVenda( PedidoVenda pedidovenda ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            ItemVendaDAO itemDAO = new ItemVendaDAO();
            itens = itemDAO.getList(pedidovenda);
            for (ItemVenda item : itens){
                itemDAO.AtualizaEstoqueItens(item, "aumentar");
            }

            session.delete(pedidovenda);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir Venda: " + e.getMessage());
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
