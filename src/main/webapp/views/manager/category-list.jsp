<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>


<a href="${pageContext.request.contextPath}/admin/category/add">Add Category</a>
<table border="1" width="100%">
  <tr>
    <th>STT</th>
    <th>Images</th>
    <th>CategoryID</th>
    <th>CategoryName</th>
    <th>Status</th>
    <th>Action</th>
  </tr>

  <c:forEach items="${listcate}" var="cate" varStatus="STT">
    <tr>
      <td>${STT.index + 1}</td>

      <td>
        <c:choose>
          <!-- Ảnh là URL đầy đủ -->
          <c:when test="${fn:startsWith(cate.images, 'http')}">
            <c:set var="imgUrl" value="${cate.images}" />
          </c:when>
          <!-- Ảnh nội bộ: /image?fname=... -->
          <c:otherwise>
            <c:url var="imgUrl" value="/image">
              <c:param name="fname" value="${cate.images}" />
            </c:url>
          </c:otherwise>
        </c:choose>

        <img height="150" width="200" src="${imgUrl}" alt="${cate.categoryname}" />
      </td>

      <td>${cate.categoryid}</td>
      <td>${cate.categoryname}</td>

      <td>
        <c:choose>
          <c:when test="${cate.status == 1}">
            <span>Hoạt động</span>
          </c:when>
          <c:otherwise>
            <span>Khóa</span>
          </c:otherwise>
        </c:choose>
      </td>

      <td>
        <c:url var="editUrl" value="/admin/category/edit">
          <c:param name="id" value="${cate.categoryid}" />
        </c:url>
        <c:url var="delUrl" value="/admin/category/delete">
          <c:param name="id" value="${cate.categoryid}" />
        </c:url>

        <a href="${editUrl}" class="center">Sửa</a> |
        <a href="${delUrl}" class="center">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
