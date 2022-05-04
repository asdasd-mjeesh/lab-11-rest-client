<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<body>

<h2>Ky.</h2>

<table>
    <tr>
        <th>id</th>
        <th>Title</th>
        <th>Cost</th>
        <th>Shelf life</th>
        <th>Producer</th>
    </tr>

    <c:forEach var="product" items="${currentPageProducts}">

        <c:url var="updateButton" value="/updateInfo">
            <c:param name="prId" value="${product.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteProduct">
            <c:param name="prId" value="${product.id}"/>
        </c:url>

        <tr>

            <td>${product.id}</td>


            <td>${product.title}</td>
            <td>${product.cost}</td>
            <td>${product.shelfLife}</td>
            <td><a href="producers/${product.producer.id}"> ${product.producer.name} </a></td>

            <td>
                <input type="button" value="Update"
                       onclick="window.location.href = '${updateButton}' "/>

                <input type="button" value="Delete"
                       onclick="window.location.href = '${deleteButton}' "/>
            </td>
        </tr>



    </c:forEach>
</table>

<input type="button" value="Add new product"
       onclick="window.location.href = 'newProduct' "/>


</body>

</html>