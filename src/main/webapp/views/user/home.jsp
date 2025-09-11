<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h2>Danh sách Category (User)</h2>

<table border="1" cellpadding="6" cellspacing="0" width="100%">
  <thead>
  <tr>
    <th>STT</th>
    <th>CategoryID</th>
    <th>CategoryName</th>
    <th>Description</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${listcate}" var="c" varStatus="st">
    <tr>
      <td>${st.index + 1}</td>
      <td>${c.cate_id}</td>
      <td>${c.cate_name}</td>
      <td>${c.description}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
