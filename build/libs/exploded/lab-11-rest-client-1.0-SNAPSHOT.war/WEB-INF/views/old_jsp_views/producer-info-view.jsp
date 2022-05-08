<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>

<h2>Producer info</h2>


id: ${pickedProducer.id}
<br>
Name: ${pickedProducer.name}
<br>
Contact(s)
<ul>
    <c:forEach var="contact" items="${pickedProducer.contacts}">
        <li>
                ${contact.number}
        </li>
    </c:forEach>
</ul>


<input type="button" value="Show all products" onclick="window.location.href = 'allProducts/id${pickedProducer.id}/page0'"/>
<input type="button" value="Update" onclick="window.location.href = 'addNewEmployee' "/>
<input type="button" value="Delete" onclick="window.location.href = 'addNewEmployee' "/>

</body>

</html>