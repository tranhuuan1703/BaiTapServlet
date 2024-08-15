<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="myForm" action="test" method="get" onsubmit="enableInput()">
        <label for="myInput">Enter your value:</label>
        <input type="text" id="myInput" name="myInput" value="Some Value" disabled>
        <button type="submit">Submit</button>
    </form>

    <script>
        function enableInput() {
            document.getElementById("myInput").disabled = false;
        }
    </script>
</body>
</html>