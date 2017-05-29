package com.login;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.usuario.Usuario;
import com.usuario.UsuarioDao;

/**
 *
 * @author lucas
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/"})
//@WebServlet(name = "ServletLogin", urlPatterns = {"/login"})
public class ServletLogin extends HttpServlet {

    private static final long serialVersionUID = -1827728623083918138L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("login");
        if ("entrar".equals(acao)) {
            logar(request, response);
        }
    }

    private void logar(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            UsuarioDao usuarioDao = new UsuarioDao();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password"); // Não estou gerando hash é só pra mostrar a idéia

            Usuario usuario = usuarioDao.logarUsuario(username, password);
            session.setAttribute("usuario", usuario);

            response.sendRedirect("pessoa");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
