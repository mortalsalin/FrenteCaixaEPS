
package frentecaixa.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import frentecaixa.model.Produto;
 
@FacesConverter("produtoConverter")
public class produtoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Produto) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Produto) {
            Produto entity= (Produto) value;
            if (entity != null && entity instanceof Produto && entity.getCodProduto()!= null) {
                uiComponent.getAttributes().put( entity.getCodProduto().toString(), entity);
                return entity.getCodProduto().toString();
            }
        }
        return "";
    }
}
