package com.pessoa;

import com.login.Login;
import com.usuario.Usuario;
import com.util.Format;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.ServletUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ServletPessoa", urlPatterns = {"/pessoa"})
public class ServletPessoa extends HttpServlet {

    private static final long serialVersionUID = -3820161213098294259L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = Login.obterSessao(request, response);
        String acao = request.getParameter("acao");
        if(usuario != null){
            if("salvar".equals(acao)) {
                salvar(request, response);
            } else if ("deletar".equals(acao)) {
                deletar(request, response);
            } else {
                index(request, response);
            }
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            PessoaDAO pessoaDao = new PessoaDAO();
            List<Pessoa> pessoas = pessoaDao.listAll();
            request.setAttribute("pessoas", pessoas);
            ServletUtil.render("pessoa/index.jsp", request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void salvar(HttpServletRequest request, HttpServletResponse response) {
        String botaoAcao = request.getParameter("botaoAcao");
        Pessoa pessoa = null;
        PessoaDAO pessoaDao = new PessoaDAO();
        
        try {
            if ("salvar".equals(botaoAcao)) {
                pessoa = new Pessoa();
                popular(request, pessoa);

                if (pessoa.getId() > 0) {
                    pessoaDao.atualizar(pessoa);
                    index(request, response);
                } else {
                    pessoaDao.adicionar(pessoa);
                    index(request, response);
                }
            } else {
                long id = Format.getLong(request.getParameter("id"));
                pessoa = pessoaDao.getPessoa(id);
                request.setAttribute("pessoa", pessoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ServletUtil.render("pessoa/add.upd.pessoa.jsp", request, response);
    }

    protected void deletar(HttpServletRequest request, HttpServletResponse response) {
        try {
            PessoaDAO pessoaDao = new PessoaDAO();
            long id = Format.getLong(request.getParameter("id"));
            pessoaDao.apagar(id);
            
            index(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void popular(HttpServletRequest request, Pessoa pessoa) {
        
        // 'request.getParameter("name do elemento HTML")' "pega" os elementos do HTML pelo name
        
        pessoa.setId(Format.getLong(request.getParameter("id")));
        pessoa.setNome(StringUtils.trimToEmpty(request.getParameter("nome")));
    }
}
