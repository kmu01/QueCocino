package quecocino
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.CREATED

class UsuarioController {
    static scaffold = Usuario
    static allowedMethods = [index: 'GET', save: 'POST', update: 'PUT', delete: 'DELETE']
    def index() { }
}
