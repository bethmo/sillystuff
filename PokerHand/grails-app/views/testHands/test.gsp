<html>
<head>
	<title>Poker hand ranker</title>
</head>
<body>
	<h1>Poker hands from database, with ranks</h1>
	<table border="1">
		<tr>
			<th>Test</td>
			<th>Hand</td>
			<th>Expected</td>
			<th>Actual</td>
		</tr>
		<g:each in="${testInstances}" status="i" var="hand">
			<tr>
				<td>${hand.testResult }</td>
				<td>${hand.hand }</td>
				<td>${hand.expectedDescription }</td>
				<td>${hand.actualDescription }</td>
			</tr>
		</g:each>
	</table>
</body>
</html>