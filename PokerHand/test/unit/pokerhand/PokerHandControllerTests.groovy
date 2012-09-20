package pokerhand



import org.junit.*
import grails.test.mixin.*

@TestFor(PokerHandController)
@Mock(PokerHand)
class PokerHandControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pokerHand/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pokerHandInstanceList.size() == 0
        assert model.pokerHandInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pokerHandInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pokerHandInstance != null
        assert view == '/pokerHand/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pokerHand/show/1'
        assert controller.flash.message != null
        assert PokerHand.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pokerHand/list'

        populateValidParams(params)
        def pokerHand = new PokerHand(params)

        assert pokerHand.save() != null

        params.id = pokerHand.id

        def model = controller.show()

        assert model.pokerHandInstance == pokerHand
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pokerHand/list'

        populateValidParams(params)
        def pokerHand = new PokerHand(params)

        assert pokerHand.save() != null

        params.id = pokerHand.id

        def model = controller.edit()

        assert model.pokerHandInstance == pokerHand
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pokerHand/list'

        response.reset()

        populateValidParams(params)
        def pokerHand = new PokerHand(params)

        assert pokerHand.save() != null

        // test invalid parameters in update
        params.id = pokerHand.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pokerHand/edit"
        assert model.pokerHandInstance != null

        pokerHand.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pokerHand/show/$pokerHand.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pokerHand.clearErrors()

        populateValidParams(params)
        params.id = pokerHand.id
        params.version = -1
        controller.update()

        assert view == "/pokerHand/edit"
        assert model.pokerHandInstance != null
        assert model.pokerHandInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pokerHand/list'

        response.reset()

        populateValidParams(params)
        def pokerHand = new PokerHand(params)

        assert pokerHand.save() != null
        assert PokerHand.count() == 1

        params.id = pokerHand.id

        controller.delete()

        assert PokerHand.count() == 0
        assert PokerHand.get(pokerHand.id) == null
        assert response.redirectedUrl == '/pokerHand/list'
    }
}
