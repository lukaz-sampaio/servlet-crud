package com.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.usuario.Usuario;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
public class Login {

    private Login(){}

    public static Usuario obterSessao(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession sessao = request.getSession();
            Usuario usuario = (Usuario) sessao.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect(request.getContextPath());
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // depois implementar padrão ACL
}
