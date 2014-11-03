package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.PedidoCompra;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoCompraDAO {

    private Session session;
    private Transaction trans;
    private List<PedidoCompra> listaPedidoCompra;

    public List<PedidoCompra> getLista(){
        session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(PedidoCompra.class);
        this.listaPedidoCompra = crit.list();
        return listaPedidoCompra;
    }
    
    public void inserirPedidoCompra( PedidoCompra pedidovenda ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.save(pedidovenda);
            trans.commit();
        } catch ( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void editarPedidoCompra( PedidoCompra pedidovenda ){
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

    public void excluirPedidoCompra( PedidoCompra pedidovenda ){
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
}
