<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://displaytag.sf.net"  prefix="display"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css" media="all">
            @import url("css/screen.css");
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body style="width: 800px"    >
        <h1>Miniblog!</h1>
 <span style="color: red">${USUARIO}</span>
        <span style="color: red">${ERRO_BEAN} </span>
        <span style="color: green">${SUCCESS_BEAN} </span>
        <form action="ControleBlog" method="GET">
            <textarea rows="3" cols="40" value=""  name="mensagem" size="120" 
                      style="height: 100%"></textarea>
            <br> <input type="submit" value="ENVIAR" />
        </form>
        <br/> <a href="ControleBlog" > Atualizar mensagens </a>
        <display:table  name="${BEAN_LISTA}" >
            <display:column property="id"  />
            <display:column property="usuario"  />
            <display:column property="mensagem" style="width: 85%;" />
            <display:column property="dataMensagem" title="Data"
                            format="{0,date,dd/MM/yyyy}" style="width: 10%;" />
            <display:column property="horaMensagem" title="Hora"
                            format="{0,date,HH:mm}" />
  
            <display:column  href="ControleBlog" 
                             paramId="idchave" paramProperty="id" title="Excluir">              
                <img alt="Excluir" src="img/exclui.jpeg" height="16px" width="16px">
            </display:column>
        </display:table>    

    </body>
</html>
