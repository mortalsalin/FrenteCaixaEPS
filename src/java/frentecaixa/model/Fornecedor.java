package frentecaixa.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn( name = "CodFornecedor", referencedColumnName = "CodPessoa" )
@Table(name="fornecedor")
public class Fornecedor extends Pessoa
{
    private static final long serialVersionUID = 1L;
    private String CNPJ ;

    public String getCNPJ()
    {
        return CNPJ;
    }

    public void setCNPJ(String pCNPJ) 
    {
        this.CNPJ = pCNPJ;
    }
    
 
   
    
}