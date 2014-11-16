package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.Funcionario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class FuncionarioDAO {

    private Session session;
    private Transaction trans;
    private List<Funcionario> listaFuncionario;

    public List<Funcionario> getLista(){
        session = HibernateUtil.getSessionFactory().openSession();

        Criteria crit = session.createCriteria(Funcionario.class);
        this.listaFuncionario = crit.list();
        return listaFuncionario;
    }
    
    public void inserirFuncionario( Funcionario funcionario ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.save(funcionario);
            trans.commit();
        } catch ( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void editarFuncionario( Funcionario funcionario ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.update(funcionario);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally{
            session.close();
        }
    }

    public void excluirFuncionario( Funcionario funcionario ){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();

            session.delete(funcionario);
            trans.commit();
        } catch( Exception e ){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
     public boolean logar(String login, String senha) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = session.createCriteria(Funcionario.class)
                .add(Restrictions.eq("loginFuncionario", login))
                .add(Restrictions.eq("senhaFuncionario", senha));
        //session.close();
        if (!cri.list().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
    
}
