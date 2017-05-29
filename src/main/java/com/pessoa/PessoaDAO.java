package com.pessoa;

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
public class PessoaDAO {
    
    public void adicionar(Pessoa pessoa) throws SQLException{
        PreparedStatement statement = null;
        Connection conecta = Conexao.getInstance();
        try{
            // Nao precisa adicionar o 'id' porque ele e auto incrementavel
            statement = conecta.prepareStatement("INSERT INTO tb_pessoa (nome) VALUES (?)");
            statement.setString(1, pessoa.getNome());
            statement.execute();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(statement != null){
                statement.close();
            }
        }
    }
    
    public void atualizar(Pessoa pessoa) throws SQLException{
        PreparedStatement statement = null;
        Connection conecta = Conexao.getInstance();
        try{
            // Aqui ja precisa do 'id' por causa da condição 'WHERE'
            statement = conecta.prepareStatement("UPDATE tb_pessoa SET nome=? WHERE id=?");
            statement.setString(1, pessoa.getNome());
            statement.setLong(2, pessoa.getId());
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(statement != null){
                statement.close();
            }
        }
    }
    
    public void apagar(long id) throws SQLException{
        PreparedStatement statement = null;
        Connection conecta = Conexao.getInstance();
        try{
            statement = conecta.prepareStatement("DELETE FROM tb_pessoa WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(statement != null){
                statement.close();
            }
        }
    }
    
    public Pessoa getPessoa(long id) throws SQLException{
        Connection conecta = Conexao.getInstance();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pessoa pessoa = null;
        try{
            /* É melhor passar o nome das colunas do que passar o '*' (asterisco)
             * porque além de ser uma melhor prática não sobrecarrega o banco com
             * muitas consultas. Aqui não faz muita diferença porque são apenas
             * 3 colunas, mas e se fosse uma consulta em uma tabela com muitas 
             * colunas e que não precisasse de todas?
             */
            statement = conecta.prepareStatement("SELECT id, nome FROM tb_pessoa WHERE id=?");
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                pessoa = new Pessoa();
                pessoa.setId(resultSet.getLong("id"));
                pessoa.setNome(resultSet.getString("nome"));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
        return pessoa;
    }
    
    public List<Pessoa> listAll() throws SQLException{
        Connection conecta = Conexao.getInstance();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pessoa pessoa = null;
        List<Pessoa> listarPessoas = new ArrayList<>();
        try{
            /* É melhor passar o nome das colunas do que passar o '*' (asterisco)
             * porque além de ser uma melhor prática não sobrecarrega o banco com
             * muitas consultas. Aqui não faz muita diferença porque são apenas
             * 3 colunas, mas e se fosse uma consulta em uma tabela com muitas 
             * colunas e que não precisasse de todas?
             */
            statement = conecta.prepareStatement("SELECT id, nome FROM tb_pessoa ORDER BY id ASC");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                pessoa = new Pessoa();
                pessoa.setId(resultSet.getLong("id"));
                pessoa.setNome(resultSet.getString("nome"));
                listarPessoas.add(pessoa);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
        return listarPessoas;
    }
}
