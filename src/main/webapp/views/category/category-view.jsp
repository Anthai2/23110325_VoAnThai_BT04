<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
<%@ include file="../topbar/topbar.jsp" %>

<div class="page">
    <div class="container">
        <div class="card" style="max-width:720px;margin:0 auto;">
            <h2>Chi tiết danh mục</h2>
            <div class="row" style="gap:24px; align-items:flex-start;">
                <div style="flex:1">
                    <p><strong>Mã:</strong> ${item.cate_id}</p>
                    <p><strong>Tên danh mục:</strong> <c:out value="${item.cate_name}"/></p>
                    <p><strong>Mô tả:</strong> <c:out value="${item.description}"/></p>
                </div>
            </div>
            <div class="row" style="gap:12px; margin-top:16px;">
                <a class="btn" href="${pageContext.request.contextPath}/category/edit?id=${item.cate_id}">Sửa</a>
                <a class="btn" href="${pageContext.request.contextPath}/category/list">Quay lại danh sách</a>
            </div>
        </div>
    </div>
</div>