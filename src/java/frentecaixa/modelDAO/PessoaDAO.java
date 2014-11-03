package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.Pessoa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PessoaDAO {

    private Session session;
    private Transaction trans;
    private List<Pessoa> listaPessoa;

    public List<Pessoa> getLista(){
        session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(Pessoa.class);
        this.listaPessoa = crit.list();
        return listaPessoa;
    }
    
    public void inserirCliente( Pessoa cliente ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.save(cliente);
            trans.commit();
        } catch ( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void editarCliente( Pessoa cliente ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.update(cliente);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally{
            session.close();
        }
    }

    public void excluirCliente( Pessoa cliente ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.delete(cliente);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public Pessoa pesquisaByCodPessoa(Integer codPessoa) {
		return (Pessoa) session.load(Pessoa.class, codPessoa);
	}
}
