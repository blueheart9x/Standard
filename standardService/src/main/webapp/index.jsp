<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EP SERVICES</title>
    </head>
    <body style="text-align: center;">
        <%= javax.servlet.http.HttpUtils.getRequestURL(request)%>
        <% String errorCode = request.getParameter("errorCode");
                if( errorCode == null ) { %>
                <h2>EP SERVICES</h2>
        <%} else if( "400".equals(errorCode) ) { %>
                <h2>Bad Request.</h2>
        <%} else if( "404".equals(errorCode) ) { %>
                <h2>Not Found.</h2>
        <%} else if( "405".equals(errorCode) ) { %>
                <h2>Method Not Allowed.</h2>
        <%} else if( "500".equals(errorCode) ) { %>
                <h2>Internal Server Error.</h2>
        <%} else if( "502".equals(errorCode) ) { %>
                <h2>Bad Gateway.</h2>
        <%} %>
    </body>
</html>
