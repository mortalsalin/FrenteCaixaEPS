package frentecaixa.modelDAO;

import frentecaixa.hibernate.HibernateUtil;
import frentecaixa.model.ItemCompra;
import frentecaixa.model.PedidoCompra;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ItemCompraDAO {

    private Session sessao;
    private Transaction trans;
    private List<ItemCompra> list;

    public void remover(ItemCompra p) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        try {
            sessao.delete(p);
            trans.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao deletar ItemCompra: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<ItemCompra> getList(PedidoCompra compra) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria cri = sessao.createCriteria(ItemCompra.class).add(Restrictions.eq("compra", compra));
        cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.list = cri.list();
        sessao.close();
        return list;
    }
    
    public void AtualizaEstoqueItens(ItemCompra item, String tipo)
    {
        ProdutoDAO prodDAO = new ProdutoDAO();
        if (null != tipo)switch (tipo) {
            case "diminuir":
                item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantItemCompra());
                break;
            case "aumentar": 
                item.getProduto().setEstoque(item.getProduto().getEstoque() + item.getQuantItemCompra());
                break;
        }
        prodDAO.editarProduto(item.getProduto());

    }
    
}
