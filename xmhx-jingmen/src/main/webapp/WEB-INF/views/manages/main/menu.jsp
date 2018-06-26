<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<dl class="leftbar">
	<c:if test="${not empty menus}">
		<c:forEach items="${menus}" var="menuObj">
			<dd class="${menuObj.menu_name}"><a href="javascript:add('${menuObj.menu_name}','${menuObj.menu_url}');">${menuObj.menu_name}</a></dd>
		</c:forEach>
	</c:if>
</dl>