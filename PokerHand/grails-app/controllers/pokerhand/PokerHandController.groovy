package pokerhand

import org.springframework.dao.DataIntegrityViolationException

class PokerHandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pokerHandInstanceList: PokerHand.list(params), pokerHandInstanceTotal: PokerHand.count()]
    }

    def create() {
        [pokerHandInstance: new PokerHand(params)]
    }

    def save() {
        def pokerHandInstance = new PokerHand(params)
        if (!pokerHandInstance.save(flush: true)) {
            render(view: "create", model: [pokerHandInstance: pokerHandInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), pokerHandInstance.id])
        redirect(action: "show", id: pokerHandInstance.id)
    }

    def show(Long id) {
        def pokerHandInstance = PokerHand.get(id)
        if (!pokerHandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "list")
            return
        }

        [pokerHandInstance: pokerHandInstance]
    }

    def edit(Long id) {
        def pokerHandInstance = PokerHand.get(id)
        if (!pokerHandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "list")
            return
        }

        [pokerHandInstance: pokerHandInstance]
    }

    def update(Long id, Long version) {
        def pokerHandInstance = PokerHand.get(id)
        if (!pokerHandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pokerHandInstance.version > version) {
                pokerHandInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pokerHand.label', default: 'PokerHand')] as Object[],
                          "Another user has updated this PokerHand while you were editing")
                render(view: "edit", model: [pokerHandInstance: pokerHandInstance])
                return
            }
        }

        pokerHandInstance.properties = params

        if (!pokerHandInstance.save(flush: true)) {
            render(view: "edit", model: [pokerHandInstance: pokerHandInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), pokerHandInstance.id])
        redirect(action: "show", id: pokerHandInstance.id)
    }

    def delete(Long id) {
        def pokerHandInstance = PokerHand.get(id)
        if (!pokerHandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "list")
            return
        }

        try {
            pokerHandInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pokerHand.label', default: 'PokerHand'), id])
            redirect(action: "show", id: id)
        }
    }
}
