package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.Cotacao;
import frentecaixa.model.ItemCotacao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CotacaoDAO {

    private Session session;
    private Transaction trans;
    private List<Cotacao> listaCotacao;
    private List<ItemCotacao> itens;

    public void inserirCotacao(Cotacao v) {
        session = HibernateUtil.getSessionFactory().openSession();
        trans = session.beginTransaction();
        try {
            session.save(v);
            trans.commit();

        } catch (HibernateException e) {
            System.out.println("Erro ao gravar Cotacao: " + e.getMessage());
        } finally {
            session.close();
        }
     
    }

    public void editarCotacao( Cotacao cotacao, ArrayList<ItemCotacao> itens ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            cotacao.setItemCotacao(itens);
 
            session.update(cotacao);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao editar Cotacao: " + e.getMessage());
        } finally{
            session.close();
        }
    }

    public void excluirCotacao( Cotacao cotacao ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            
            session.delete(cotacao);
            trans.commit();
            
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir Cotacao: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Cotacao> getLista() {
    	session = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = session.createCriteria(Cotacao.class);
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.listaCotacao = cri.list();     
        session.close();
        return listaCotacao;
    }
    
}
