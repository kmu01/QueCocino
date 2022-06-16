package quecocino

class PerfilController {
    static scaffold = Perfil
    static allowedMethods = [index: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']
    def index() { }
}
