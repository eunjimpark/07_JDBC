<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZOO List</title>
</head>

<body>

<h1>Zoo List</h1>
<hr><hr>

<h3>전체 마리수 : ${zoo.getcount} 마리 </h3>
<hr>

<table border="1">
    <c:forEach items="${zooList}" var="zoo" varStatus="vs">
        <tr>
            <th>울타리번호</th>
            <th>위치</th>
            <th>종</th>
            <th>마리수</th>
            <th>수정버튼</th>
            <th>삭제버튼</th>
        </tr>

        <tr>
        <td>${zoo.zooNo}</td>
        <td>${zoo.zooLocation}</td>
        <td>${zoo.zooAnimal}</td>
        <td>${zoo.animalCount}</td>
        <td><button type="button" class="update-btn">수정</button></td>
        <td><button type="button" class="delete-btn">삭제</button></td>
        </tr>

    </c:forEach>

</table>


	<c:if test="${not empty message}" >

		<script>
			const message = "${message}";
			alert(message);
		</script>

	    <c:remove var="message" scope="session" />
		
	</c:if>

	<script src="/views/main.js"></script>

</body>
</html>