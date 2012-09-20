package pokerhand

class PokerHand {
	String hand
	String expectedDescription
	
    static constraints = {
		hand(blank: false)
		expectedDescription()
    }
}
