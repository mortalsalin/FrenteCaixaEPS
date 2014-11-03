package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.PedidoVenda;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoVendaDAO {

    private Session session;
    private Transaction trans;
    private List<PedidoVenda> listaPedidoVenda;

    public List<PedidoVenda> getLista(){
        session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(PedidoVenda.class);
        this.listaPedidoVenda = crit.list();
        return listaPedidoVenda;
    }
    
    public void inserirPedidoVenda( PedidoVenda pedidovenda ){
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
}
