<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<form:form action="saveProduct" modelAttribute="product">

    Title <form:input path="title"/>
    <form:errors path="title"/>
    <br><br>
    Cost <form:input path="cost"/>
    <br><br>
    Shelf life <form:input path="shelfLife"/>
    <br><br>

    Producer <form:select path="producer">
    <form:options items="${allProducers}"/>
    </form:select>

<%--    Producer <select name="producer">--%>

<%--        <c:forEach items="${allProducers}" var="producer">--%>
<%--             <option value="${producer.id}">${producer.name}</option>>--%>
<%--        </c:forEach>--%>

<%--    </select>--%>


    <br><br>

    <input type="submit" value="OK">

</form:form>

</html>