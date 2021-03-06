package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.PedidoCompra;
import frentecaixa.model.ItemCompra;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoCompraDAO {

    private Session session;
    private Transaction trans;
    private List<PedidoCompra> listaPedidoCompra;
    private List<ItemCompra> itens;

    public void inserirPedidoCompra(PedidoCompra v) {
        session = HibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        try {
            session.save(v);
            trans.commit();

        } catch (HibernateException e) {
            System.out.println("Erro ao gravar Compra: " + e.getMessage());
        } finally {
            session.close();
        }
     
    }

    public void editarPedidoCompra( PedidoCompra pedidocompra, ArrayList<ItemCompra> itens ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            pedidocompra.setItemCompra(itens);
            Float auxVlrTotal = 0.f;
            for (ItemCompra item : itens){
                auxVlrTotal = auxVlrTotal + item.getVltTotalProduto();
            }
            pedidocompra.setValorTotal(auxVlrTotal);
            
            session.update(pedidocompra);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao editar Compra: " + e.getMessage());
        } finally{
            session.close();
        }
    }

    public void excluirPedidoCompra( PedidoCompra pedidocompra ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            ItemCompraDAO itemDAO = new ItemCompraDAO();
            itens = itemDAO.getList(pedidocompra);
            for (ItemCompra item : itens){
                itemDAO.AtualizaEstoqueItens(item, "diminuir");
            }

            session.delete(pedidocompra);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir Compra: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<PedidoCompra> getLista() {
    	session = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = session.createCriteria(PedidoCompra.class);
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.listaPedidoCompra = cri.list();     
        session.close();
        return listaPedidoCompra;
    }
    
}
