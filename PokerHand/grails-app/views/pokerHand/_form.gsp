<%@ page import="pokerhand.PokerHand" %>



<div class="fieldcontain ${hasErrors(bean: pokerHandInstance, field: 'hand', 'error')} required">
	<label for="hand">
		<g:message code="pokerHand.hand.label" default="Hand" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="hand" required="" value="${pokerHandInstance?.hand}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pokerHandInstance, field: 'expectedDescription', 'error')} ">
	<label for="expectedDescription">
		<g:message code="pokerHand.expectedDescription.label" default="Expected Description" />
		
	</label>
	<g:textField name="expectedDescription" value="${pokerHandInstance?.expectedDescription}"/>
</div>

