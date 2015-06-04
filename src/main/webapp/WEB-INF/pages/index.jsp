<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Test</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1>DataArt JSP Test - Ignacio Mezzadra</h1>
	<div style="width: 100%;">
		<!-- LEFT PART -->
		<div style="float: left; width: 30%;">
			<table border="1" cellpadding="3">
				<caption>
					<h2>List of Groups</h2>
				</caption>
				<tr>
					<th>ID</th>
					<th><a
						href="<c:url value="/?&groupId=${selectedGroup}&groupSort=${nextGroupSort}&productOrderField=PRODUCT_NAME&productSort=ASC"/>">Name</a></th>					
				</tr>
				<c:forEach var="group" items="${groups}">
					<tr>
						<td><c:out value="${group.id}" /></td>
						<td><a
							href="<c:url value="/?&groupId=${group.id}&groupSort=ASC&productOrderField=PRODUCT_NAME&productSort=ASC"/>">${group.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- RIGHT PART-->
		<div style="float: rigth; width: 70%;">
			<table border="1" cellpadding="3">
				<caption>
					<h2>List of Products for Group ${selectedGroup}</h2>
				</caption>
				<tr>
					<th>ID</th>
					<th><a
						href="<c:url value="/?&groupId=${selectedGroup}&groupSort=ASC&productOrderField=PRODUCT_NAME&productSort=${nextProductSort}"/>">Name</a></th>
					<th><a
						href="<c:url value="/?&groupId=${selectedGroup}&groupSort=ASC&productOrderField=PRODUCT_PRICE&productSort=${nextProductSort}"/>">Price</a></th>
				</tr>
				<c:forEach var="product" items="${products}">
					<tr>
						<td><c:out value="${product.id}" /></td>
						<td><c:out value="${product.name}" /></td>
						<td><c:out value="${product.price}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>