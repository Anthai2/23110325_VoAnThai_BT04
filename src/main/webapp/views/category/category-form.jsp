<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
<%@ include file="../topbar/topbar.jsp" %>

<c:set var="isEdit" value="${not empty item and item.cate_id > 0}"/>
<div class="page">
    <div class="container">
        <div class="card" style="max-width:720px;margin:0 auto;">
            <h2>${isEdit ? 'Cập nhật danh mục' : 'Thêm danh mục'}</h2>

            <form class="form" method="post"
                  action="${pageContext.request.contextPath}/category/${isEdit ? 'update' : 'store'}">
                <c:if test="${isEdit}">
                    <input type="hidden" name="id" value="${item.cate_id}"/>
                </c:if>

                <div class="input-group">
                    <label class="label" for="cate_name">Tên danh mục</label>
                    <input type="text" id="cate_name" name="cate_name" required
                           value="${isEdit ? item.cate_name : ''}" placeholder="Nhập tên danh mục"/>
                </div>

                <div class="input-group">
                    <label class="label" for="description">Mô tả</label>
                    <textarea id="description" name="description" rows="4"
                              placeholder="Mô tả ngắn...">${isEdit ? item.description : ''}</textarea>
                </div>

                <div class="row" style="gap:12px;">
                    <button type="submit" class="btn">${isEdit ? 'Lưu thay đổi' : 'Tạo mới'}</button>
                    <a class="btn" href="${pageContext.request.contextPath}/category/list"
                       style="text-decoration:none;display:inline-block;">Hủy</a>
                </div>
            </form>
        </div>
    </div>
</div>