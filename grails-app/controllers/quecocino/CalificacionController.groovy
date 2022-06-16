package quecocino

class CalificacionController {
    static scaffold = Calificacion
    static allowedMethods = [index: 'GET', save: 'POST', delete: 'DELETE']
    def index() { }
}
