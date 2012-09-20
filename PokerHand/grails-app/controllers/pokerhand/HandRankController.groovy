package pokerhand

class HandRankController {
	
	def index = {
		redirect (action: rankHands)
	}
	
	def rankHands = {
		def rawHands = PokerHand.list()
		[lastHand: params.hand, resultOfLastSubmit: params.hand ? new PokerRank(params.hand).description : ""]
	}
	
	def evaluateHand = {
		redirect(action: "rankHands", params: [hand: params.hand])
	}
}
