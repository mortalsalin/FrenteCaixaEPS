package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.ItemVenda;
import frentecaixa.model.PedidoVenda;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ItemVendaDAO {

    private Session sessao;
    private Transaction trans;
    private List<ItemVenda> list;

    public String cadastrar(ArrayList<ItemVenda> carrinhoCompras, PedidoVenda venda) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        String mensagem = "";
        try {
            trans.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao gravar ItemVenda: " + e.getMessage());
        }
        System.out.println(mensagem);
        return mensagem;

    }

    public void remover(ItemVenda p) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        try {

            sessao.delete(p);
            trans.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao deletar ItemVenda: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<ItemVenda> getList(PedidoVenda venda) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = sessao.createCriteria(ItemVenda.class).add(Restrictions.eq("venda", venda));
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.list = cri.list();
        sessao.close();
        return list;
    }
    
}
