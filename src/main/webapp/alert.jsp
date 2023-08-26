<%@page import="entities.Alert"%>
<%
Alert alert = (Alert) session.getAttribute("alert");
if (alert != null) {
	session.removeAttribute("alert");
%>
<div class="<%="alert mt-3 text-center " + alert.getClasses()%>" role="alert">
	<%=alert.getMessage()%>
</div>
<%
}
%>