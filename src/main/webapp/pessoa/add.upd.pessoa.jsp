<%-- 
    Document   : add.upd.pessoa
    Created on : 31/07/2016, 16:00:03
    Author     : lucas
--%>

<%@include file="../common/openheader.jsp"%>
<title> ::Pessoa:: </title>
<%@include file="../common/closeheader.jsp"%>

<form action="${pageContext.request.contextPath}/pessoa" method="post">
    <table>
        <tr>
            <td>
                <label for="id">Id: </label>
                <input id="id" name="id" readonly="readonly" value="${pessoa.id}" size="3"/>
            </td>
            <td>
                <label for="nome">Nome: </label>
                <input id="nome" name="nome" type="text" value="${pessoa.nome}" size="30"/>
            </td>
        </tr>
    </table>
    <br>
    <input type="hidden" name="acao" value="salvar">
    <button name="botaoAcao" value="salvar">Ok</button>
</form>

<%@include file="../common/footer.jsp"%>
