<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 导航侧栏 -->
<aside class="main-sidebar">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p> <security:authentication property="principal.username"></security:authentication> </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/main.jsp">
                    <i class="fa fa-dashboard"></i> <span>首页</span>
                </a>
            </li>
            <c:forEach items="${menus}" var="menu">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cogs"></i>
                    <span>${menu.permissionName}</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <c:forEach items="${menu.children}" var="child">
                    <li>
                        <a href="${pageContext.request.contextPath}${child.url}">
                            <i class="fa fa-circle-o"></i> ${child.permissionName}
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </li>
            </c:forEach>
        </ul>
    </section>
</aside>
<!-- 导航侧栏 /-->
