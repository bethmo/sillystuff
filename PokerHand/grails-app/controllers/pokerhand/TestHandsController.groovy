package pokerhand

class TestHandsController {

    def index() {
		redirect (action: test)
	}
	
    def test = {
		def allTests = PokerHand.list().collect{
			def actualDescription = new PokerRank(it.hand).description
			new Expando(hand:it.hand, expectedDescription:it.expectedDescription, actualDescription:actualDescription, testResult:it.expectedDescription == actualDescription ? "OK" : "Bad")
		}
		
        [testInstances: allTests]
    }
}
