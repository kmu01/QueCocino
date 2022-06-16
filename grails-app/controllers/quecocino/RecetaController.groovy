package quecocino

class RecetaController {
    static scaffold = Receta
    static allowedMethods = [index: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']
    def index() { }
}
