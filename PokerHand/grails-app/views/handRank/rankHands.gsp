<html>
<head>
	<title>Poker hand ranker</title>
</head>
<body>
	<h1>Poker hand evaluator</h1>
	<g:form action="evaluateHand" >
		<input type="text" name="hand" value="${lastHand}" />
		<g:submitButton name="rankButton" value="Rank it" />
	</g:form>
	${resultOfLastSubmit}
</body>
</html>