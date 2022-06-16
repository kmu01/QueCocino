package quecocino

class AdministradorController {
    static scaffold = Administrador
    static allowedMethods = [index: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']
    def index() { }
}
