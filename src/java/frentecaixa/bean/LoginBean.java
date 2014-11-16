
package frentecaixa.bean;

import frentecaixa.modelDAO.FuncionarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean {
    private String login;
    private String senha;
    private boolean logged = false;
    private FuncionarioDAO funcDAO = new FuncionarioDAO();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public FuncionarioDAO getFuncDAO() {
        return funcDAO;
    }

    public void setFuncDAO(FuncionarioDAO funcDAO) {
        this.funcDAO = funcDAO;
    }
    
    public String logar() {
        if (((login.equals("admin")) & (senha.equals("sysdba"))) || (funcDAO.logar(login,senha))) {
            this.setLogged(true);
            return "index";   
        } 
        else {
            this.setLogged(false);
            return "login";
        }
    }
}
