package pokerhand

class PokerRank {
	
	public String hand;
	public String rank;
	
	List<Card> cards = new ArrayList<Card>();
	
	public PokerRank(newHand) {
		// Ordered from best to worst, so priorities can be changed or new combinations easily added
		// Each function returns the description of the rank if it applies, or null
		List rankFunctions = [ royalFlush, straightFlush, fourOfKind, fullHouse, flush, straight, threeOfKind, twoPair, onePair, highCard ]
	
		hand = newHand
		List<String> rawCards = newHand.split(" ")
		if (rawCards.size() != 5) {
			rank = "Invalid hand [" + newHand + "] -- must contain five cards."
			return
		}
		try {
			rawCards.each { cards.add(new Card(it))  }
		} catch (Exception e) {
			rank = e.message;
			return
		}
		if (cards.unique { it.name }.size() < 5) {
			rank = "Invalid hand [" + newHand + "] -- contains duplicate cards."
			return
		}
		
		cards.sort { it.rank }
		rank = rankFunctions.findResult { it() }
	}
	
	def isFlush = {
		cards[1..4].every { it.suitRank == cards[0].suitRank }
	}
	
	def highRankNameIfStraight = {
		if ((1..4).every { cards[it].rank == cards[it-1].rank + 1}) {
			return cards[0].rankName() 
		}
		// Special case: A, 5, 4, 3, 2 is considered a 5-high straight
		if ((cards[0].rank == 0) && (1..4).every { cards[it].rank == it + 8}) {
			return cards[1].rankName()
		}
		return null
	}
	
	boolean ranksAreEqual(cardArray, indexes) {
		if (indexes.size() < 2) {
			return true
		}
		return indexes[1..indexes.size()-1].every { cardArray[it].rank == cardArray[0].rank}
	}
	
	def royalFlush = {
		((cards[1].rank == 1) && isFlush() && highRankNameIfStraight() != null) ? "Royal Flush" : null
	}
	
	def straightFlush = {
		if (!isFlush()) {
			return null
		}
		String highCard = highRankNameIfStraight()
		return (highCard != null) ? "Straight flush, " + highCard + " high" : null
	}
	
	def fourOfKind = {
		def copiedCards = new ArrayList(cards)
		copiedCards.removeAll { it.rank == cards[2].rank }
		copiedCards.size() == 1 ? "Four " + cards[2].pluralRankName() : null
	}
	
	def fullHouse = {
		def copiedCards = new ArrayList(cards)
		copiedCards.removeAll { it.rank == cards[0].rank }
		if (copiedCards.size() == 2) {
			return (copiedCards[0].rank == copiedCards[1].rank) ? cards[0].pluralRankName() + " full of " + copiedCards[0].pluralRankName() : null
		}
		if (copiedCards.size() == 3) {
			return ranksAreEqual(copiedCards, (0..2)) ? copiedCards[0].pluralRankName() + " full of " + cards[0].pluralRankName() : null
		}
		null
	}
	
	def flush = {
		isFlush() ? "Flush, " + cards[0].rankName() + " high" : null
	}
	
	def straight = {
		String highCard = highRankNameIfStraight()
		return (highCard != null) ? "Straight, " + highCard + " high" : null
	}
	
	def threeOfKind = {
		if (ranksAreEqual(cards, (0..2))) {
			return "Three " + cards[0].pluralRankName()
		}
		if (ranksAreEqual(cards, (1..3))) {
			return "Three " + cards[1].pluralRankName()
		}
		return ranksAreEqual(cards, (2..4)) ? "Three " + cards[2].pluralRankName() : null
	}
	
	def twoPair = {
		if (cards[0].rank == cards[1].rank) {
			return ((cards[2].rank == cards[3].rank) || (cards[3].rank == cards[4].rank)) ? "Two pair, " + cards[0].pluralRankName() + " and " + cards[3].pluralRankName() : null
		}
		return ((cards[1].rank == cards[2].rank) && (cards[3].rank == cards[4].rank)) ? "Two pair, " + cards[1].pluralRankName() + " and " + cards[3].pluralRankName() : null
	}
	
	def onePair = {
		String pairedRankName = (0..3).findResult { cards[it].rank == cards[it+1].rank ? cards[it].pluralRankName() : null }
		return pairedRankName == null ? null : "Pair of " + pairedRankName
	}
	
	def highCard = {
		cards[0].rankName() + " high"
	}
	
}
