
<%@ page import="pokerhand.PokerHand" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pokerHand.label', default: 'PokerHand')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pokerHand" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pokerHand" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pokerHand">
			
				<g:if test="${pokerHandInstance?.hand}">
				<li class="fieldcontain">
					<span id="hand-label" class="property-label"><g:message code="pokerHand.hand.label" default="Hand" /></span>
					
						<span class="property-value" aria-labelledby="hand-label"><g:fieldValue bean="${pokerHandInstance}" field="hand"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pokerHandInstance?.expectedDescription}">
				<li class="fieldcontain">
					<span id="expectedDescription-label" class="property-label"><g:message code="pokerHand.expectedDescription.label" default="Expected Description" /></span>
					
						<span class="property-value" aria-labelledby="expectedDescription-label"><g:fieldValue bean="${pokerHandInstance}" field="expectedDescription"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pokerHandInstance?.id}" />
					<g:link class="edit" action="edit" id="${pokerHandInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
