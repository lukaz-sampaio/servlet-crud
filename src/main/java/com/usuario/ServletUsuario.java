package com.usuario;

import com.util.Format;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.ServletUtil;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    private static final long serialVersionUID = 327108065737926304L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if("salvar".equals(acao)){
            salvar(request, response);
        } else {
            index(request, response);
        }
    }

    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            UsuarioDao model = new UsuarioDao();
            List<Usuario> list = model.listarUsuarios();
            request.setAttribute("list", list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ServletUtil.render("", request, response);
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String acao = request.getParameter("botao_acao");
            Usuario usuario = null;
            UsuarioDao model = new UsuarioDao();
            boolean ok = false;
            if ("ok".equals(acao)) {
                usuario = new Usuario();
                popular(request, usuario);
                if (validar(usuario, request)) {
                    criarOuAtualizar(usuario);
                    ok = true;
                }
            } else {
                long id = Format.getLong(request.getParameter("id"));
                usuario = model.getUsuario(id);
                request.setAttribute("object", usuario);
            }
            
            if(ok){
                index(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServletUtil.render("", request, response);
    }

    public void popular(HttpServletRequest request, Usuario usuario) {
        usuario.setId(Long.parseLong((request.getParameter("id") != null && !request.getParameter("id").isEmpty()) ? request.getParameter("id") : "0"));
        usuario.setUsername((request.getParameter("username") != null && !request.getParameter("username").isEmpty()) ? request.getParameter("username") : "");
        usuario.setPassword((request.getParameter("password") != null && !request.getParameter("password").isEmpty()) ? request.getParameter("password") : "");
    }

    public boolean validar(Usuario usuario, HttpServletRequest request) {
        boolean ok = false;
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            request.setAttribute("", "Nome de usuário obrigatório!");
            ok = true;
        }

        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            request.setAttribute("", "Senha obrigatória!");
            ok = true;
        }
        return ok;
    }

    private void criarOuAtualizar(Usuario usuario) {
        try {
            UsuarioDao model = new UsuarioDao();
            if (usuario.getId() > 0) {
                model.alterarUsuario(usuario);
            } else {
                model.cadastrarUsuario(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
