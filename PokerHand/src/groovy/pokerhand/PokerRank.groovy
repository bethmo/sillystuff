package pokerhand

class PokerRank {
	
	String hand;
	String description;
	
	private List<Card> cards = new ArrayList<Card>();
	
	PokerRank(newHand) {
		// Ordered from best to worst, so priorities can be changed or new combinations easily added
		// Each function returns the description of the hand if it applies, or null
		List handTypeFunctions = [ royalFlush, straightFlush, fourOfKind, fullHouse, flush, straight, threeOfKind, twoPair, onePair, highCard ]
	
		hand = newHand
		List<String> rawCards = newHand.split(" ")
		if (rawCards.size() != 5) {
			description = "Invalid hand [" + newHand + "] -- must contain five cards."
			return
		}
		try {
			cards = rawCards.collect { new Card(it) }
		} catch (Exception e) {
			description = e.message;
			return
		}
		if (cards.unique { it.name }.size() < 5) {
			description = "Invalid hand [" + newHand + "] -- contains duplicate cards."
			return
		}
		
		cards.sort { it.rank } // Sorts from highest value to lowest value (rank is inverse of value)
		
		description = handTypeFunctions.findResult { it() }
		
//		for (int i=0; !rank && i < handTypeFunctions.size(); i++) {
//			rank= handTypeFunctions[i]();
//			if (rank) { rank += " " + i }
//		}
		
	}
	
	private royalFlush = {
		((cards[1].rank == 1) && isFlush() && highRankNameIfStraight() != null) ? "Royal Flush" : null
	}
	
	private straightFlush = {
		if (!isFlush()) {
			return null
		}
		String highCard = highRankNameIfStraight()
		return (highCard != null) ? "Straight flush, $highCard high" : null
	}
	
	private fourOfKind = {
		def unMatched = cards.findAll { it.rank != cards[2].rank}
		unMatched.size() == 1 ? "Four ${cards[2].pluralRankName}" : null
	}
	
	private fullHouse = {
		def notMatchingHighest = cards.findAll { it.rank != cards[0].rank}
		if (notMatchingHighest.size() == 2) {
			return (notMatchingHighest[0].rank == notMatchingHighest[1].rank) ? "${cards[0].pluralRankName} full of ${notMatchingHighest[0].pluralRankName}" : null
		}
		if (notMatchingHighest.size() == 3) {
			return ranksAreEqual(notMatchingHighest, (0..2)) ? "${notMatchingHighest[0].pluralRankName} full of ${cards[0].pluralRankName}" : null
		}
		null
	}
	
	private flush = {
		isFlush() ? "Flush, ${cards[0].rankName} high" : null
	}
	
	private straight = {
		String highCard = highRankNameIfStraight()
		return (highCard != null) ? "Straight, $highCard high" : null
	}
	
	private threeOfKind = {
		if (ranksAreEqual(cards, (0..2))) {
			return "Three ${cards[0].pluralRankName}"
		}
		if (ranksAreEqual(cards, (1..3))) {
			return "Three ${cards[1].pluralRankName}"
		}
		return ranksAreEqual(cards, (2..4)) ? "Three ${cards[2].pluralRankName}" : null
	}
	
	private twoPair = {
		if (cards[0].rank == cards[1].rank) {
			return ((cards[2].rank == cards[3].rank) || (cards[3].rank == cards[4].rank)) ? "Two pair, ${cards[0].pluralRankName} and ${cards[3].pluralRankName}" : null
		}
		return ((cards[1].rank == cards[2].rank) && (cards[3].rank == cards[4].rank)) ? "Two pair, ${cards[1].pluralRankName} and ${cards[3].pluralRankName}" : null
	}
	
	private onePair = {
		String pairedRankName = (0..3).findResult { cards[it].rank == cards[it+1].rank ? cards[it].pluralRankName : null }
		return pairedRankName == null ? null : "Pair of $pairedRankName"
	}
	
	private highCard = {
		"${cards[0].rankName} high"
	}
	
	private boolean isFlush() {
		cards[1..4].every { it.suitRank == cards[0].suitRank }
	}
	
	private String highRankNameIfStraight() {
		if ((1..4).every { cards[it].rank == cards[it-1].rank + 1}) {
			return cards[0].rankName 
		}
		// Special case: A, 5, 4, 3, 2 is considered a 5-high straight
		if ((cards[0].rank == 0) && (1..4).every { cards[it].rank == it + 8}) {
			return cards[1].rankName
		}
		return null
	}
	
	private boolean ranksAreEqual(cardArray, indexes) {
		if (indexes.size() < 2) {
			return true
		}
		return indexes[1..indexes.size()-1].every { cardArray[it].rank == cardArray[0].rank}
	}
	
}
