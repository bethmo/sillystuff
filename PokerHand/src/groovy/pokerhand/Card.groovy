package pokerhand

class Card {
	String name;
	int rank; // A=0, K=1, Q=2, etc. (rank is inverse of card face value)
	int suitRank;
	
	String getRankName() {
		rankNames[rank]
	}
	
	String getPluralRankName() {
		pluralRankNames[rank]
	}
	
	int getFaceValue() {
		14 - rank
	}
	
	private static String ranks = "AKQJT98765432"
	private static List<String> rankNames = ["Ace", "King", "Queen", "Jack", "Ten", "Nine", "Eight", "Seven", "Six", "Five", "Four", "Three", "Two"].asImmutable()
	private static List<String> pluralRankNames = ["Aces", "Kings", "Queens", "Jacks", "Tens", "Nines", "Eights", "Sevens", "Sixes", "Fives", "Fours", "Threes", "Twos"].asImmutable()
	private static String suits = "SHCD"
	
	Card(String newName) {
		name = newName.toUpperCase().replace("10", "T");
		if (name.length() != 2) {
			throw new java.lang.Exception("Invalid card '" + newName + "' -- must be rank followed by suit, e.g. AH, 10S, QC.")
		}
		
		rank = ranks.indexOf(name.substring(0, 1));
		if (rank < 0)  {
			throw new java.lang.Exception("Invalid rank in card '" + newName + "' -- rank must be one of " + ranks + " or 10.")
		}
		
		suitRank = suits.indexOf(name.substring(1));
		if (suitRank < 0)  {
			throw new java.lang.Exception("Invalid suit in card [" + newName + "] -- suit must be one of " + suits + ".")
		}
	}
	
}
