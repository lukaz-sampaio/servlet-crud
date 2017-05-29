<%-- 
    Document   : index
    Created on : 31/07/2016, 15:59:41
    Author     : lucas
--%>

<%@include file="../common/openheader.jsp"%>
<title> ::Pessoa:: </title>
<%@include file="../common/closeheader.jsp"%>

<br>
<a href="${pageContext.request.contextPath}/pessoa?acao=salvar"> Novo registro</a>
<br><br><br>

<table border="1px" style="border-style: solid">
    <tr>
        <th> Id </th>
        <th> Nome </th>
        <th> Op&ccedil;&otilde;es </th>
    </tr>
    <c:forEach var="pessoa" items="${pessoas}">
    <tr>
        <td>${pessoa.id}</td>
        <td>${pessoa.nome}</td>
        <td>
            <a href="pessoa?acao=salvar&id=${pessoa.id}"> Editar </a>
            <a href="pessoa?acao=deletar&id=${pessoa.id}"> Deletar </a>
        </td>
    </tr>
    </c:forEach>

</table>

<%@include file="../common/footer.jsp"%>
