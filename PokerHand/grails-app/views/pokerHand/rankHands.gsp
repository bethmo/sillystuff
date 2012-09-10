<html>
<head>
	<title>Poker hand ranker</title>
</head>
<body>
	<h1>Poker hands from database, with ranks</h1>
	<table border="1">
		<g:each in="${hands}" status="i" var="hand">
			<tr>
				<td>${hand.hand }</td>
				<td>${hand.rank }</td>
			</tr>
		</g:each>
	</table>
</body>
</html>