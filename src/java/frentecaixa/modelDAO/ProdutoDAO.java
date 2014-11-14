package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.Produto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ProdutoDAO {

    private Session session;
    private Transaction trans;
    private List<Produto> listaProduto;

    public List<Produto> getLista(){
        session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(Produto.class);
        this.listaProduto = crit.list();
        return listaProduto;
    }
    
    public void inserirProduto( Produto produto ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.save(produto);
            trans.commit();
        } catch ( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void editarProduto( Produto produto ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.update(produto);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally{
            session.close();
        }
    }

    public void excluirProduto( Produto produto ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.delete(produto);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Produto> listar(String s) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cri = session.createCriteria(Produto.class);
            cri.addOrder(Order.asc("nome"));
            cri.add(Restrictions.like("nome", s + "%"));
            return cri.list();
        } finally {
            session.close();
        }
    }
}
