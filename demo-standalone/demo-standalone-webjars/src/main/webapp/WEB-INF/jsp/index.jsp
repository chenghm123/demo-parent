<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<base href="${basePath}">
<head>
    <title>index</title>
    <script src="resources/jquery/1.12.4/jquery.js"></script>
    <script>
        $(function () {
            var $body = $("body");
            $body.css("color", "red");
            $body.text("jquery load success");
        });
    </script>
</head>
<body></body>
</html>
