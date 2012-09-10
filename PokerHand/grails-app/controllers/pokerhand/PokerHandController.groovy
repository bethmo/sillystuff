package pokerhand

class PokerHandController {

	def index = {
		redirect (action: rankHands)
	}
	
	def rankHands = {
		def rawHands = PokerHand.list()
		[hands:rawHands.collect { new PokerRank(it.hand)}]
	}
}
