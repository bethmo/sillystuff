package pokerhand

class Card {
	public String name;
	public int rank;
	public int suitRank;
	
	static String ranks = "AKQJT98765432"
	static List<String> rankNames = ["Ace", "King", "Queen", "Jack", "Ten", "Nine", "Eight", "Seven", "Six", "Five", "Four", "Three", "Two"]
	static List<String> pluralRankNames = ["Aces", "Kings", "Queens", "Jacks", "Tens", "Nines", "Eights", "Sevens", "Sixes", "Fives", "Fours", "Threes", "Twos"]
	static String suits = "SHCD"
	
	public Card(String newName) {
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
	
	public rankName = {
		rankNames[rank]
	}
	
	
	public pluralRankName = {
		pluralRankNames[rank]
	}
	
}
