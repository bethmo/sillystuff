
<%@ page import="pokerhand.PokerHand" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pokerHand.label', default: 'PokerHand')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pokerHand" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pokerHand" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="hand" title="${message(code: 'pokerHand.hand.label', default: 'Hand')}" />
					
						<g:sortableColumn property="expectedDescription" title="${message(code: 'pokerHand.expectedDescription.label', default: 'Expected Description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pokerHandInstanceList}" status="i" var="pokerHandInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pokerHandInstance.id}">${fieldValue(bean: pokerHandInstance, field: "hand")}</g:link></td>
					
						<td>${fieldValue(bean: pokerHandInstance, field: "expectedDescription")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pokerHandInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
