package com.usuario;

import com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class UsuarioDao {
    
    public Usuario logarUsuario(String username, String password) throws SQLException{
        Connection conn = Conexao.getInstance();
        PreparedStatement ps = null;
        ResultSet result = null;
        Usuario usuario = null;
        try{
            String sql = "SELECT id, username, password FROM tb_usuario WHERE username=? AND password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            result = ps.executeQuery();
            if(result.next()){
                usuario = new Usuario();
                usuario.setId(result.getLong("id"));
                usuario.setUsername(result.getString("username"));
                usuario.setPassword(result.getString("password"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            if(ps!= null){
                ps.close();
            }
            if(result != null){
                result.close();
            }
        }
        return usuario;
    }
    
    public void cadastrarUsuario(Usuario usuario) throws SQLException{
        Connection conn = Conexao.getInstance();
        try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO tb_usuario (id, username, password) VALUES (?, ?, ?)")){
            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getUsername());
            stmt.setString(3, usuario.getPassword());
            stmt.execute();
        }
    }
    
    public void alterarUsuario(Usuario usuario) throws SQLException{
        Connection conn = Conexao.getInstance();
        try(PreparedStatement stmt = conn.prepareStatement("UPDATE tb_usuario SET id=?, username=?, password=?")){
            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getUsername());
            stmt.setString(3, usuario.getPassword());
            stmt.executeUpdate();
        }
    }
    
    public Usuario getUsuario(long id) throws SQLException{
        Usuario usuario = null;
        Connection conn = Conexao.getInstance();
        try(PreparedStatement ps = conn.prepareStatement("SELECT id, username, password FROM b_usuario WHERE id = ?")){
            try(ResultSet result = ps.executeQuery()){
                if(result.next()){
                    usuario = new Usuario();
                    usuario.setId(result.getLong("id"));
                    usuario.setUsername(result.getString("username"));
                    usuario.setPassword(result.getString("password"));
                }
            }
        }
        return usuario;
    }
    
    
    public List<Usuario> listarUsuarios() throws SQLException{
        List<Usuario> list = new ArrayList<>();
        Connection conn = Conexao.getInstance();
        try(PreparedStatement ps = conn.prepareStatement("SELECT id, username, password FROM b_usuario")){
            try(ResultSet result = ps.executeQuery()){
                while(result.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(result.getLong("id"));
                    usuario.setUsername(result.getString("username"));
                    usuario.setPassword(result.getString("password"));
                    list.add(usuario);
                }
            }
        }
        return list;
    }
    
}
