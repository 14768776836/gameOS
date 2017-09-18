<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloWorld page</title>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
</head>
<body>
hello!this is web socket connection
</body>
<script>
 var sock = new SockJS('http://localhost:8080/gameWeb/gameWs?a=a1');
 sock.onopen = function() {
     console.log('open');
     sock.send('test');
 };

 sock.onmessage = function(e) {
     console.log('message', e.data);
     sock.close();
 };

 sock.onclose = function() {
     console.log('close');
 };

 sock.onerror = function() {
    console.log('error');
 };
</script>
</html>