package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.ItemCotacao;
import frentecaixa.model.Cotacao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ItemCotacaoDAO {

    private Session sessao;
    private Transaction trans;
    private List<ItemCotacao> list;

    public void remover(ItemCotacao p) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        try {
            sessao.delete(p);
            trans.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao deletar ItemCotacao: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<ItemCotacao> getList(Cotacao cotacao) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = sessao.createCriteria(ItemCotacao.class).add(Restrictions.eq("cotacao", cotacao));
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.list = cri.list();
        sessao.close();
        return list;
    }
    
}
