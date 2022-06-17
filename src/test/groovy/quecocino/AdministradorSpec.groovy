package quecocino

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AdministradorSpec extends Specification implements DomainUnitTest<Administrador> {

    void "Administrador puede banear usuarios"() {
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def juan = new Administrador(nombre: 'Juan', constrasenia: '123', email: 'juan@samplemail.com', perfil: new Perfil())
        juan.banearUsuario(marcelo, 10)

        expect:
        marcelo.estaBaneado()
    }

    void "Administrador puede eliminar receta sin ser autor"() {
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def juan = new Administrador(nombre: 'Juan', constrasenia: '123', email: 'juan@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.agregarReceta(empanadas)
        juan.borrarReceta(empanadas)

        expect:
        marcelo.getPerfil().getRecetas().size() == 0
    }

    void "Administrador puede eliminar calificacion sin ser autor"() {
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def juan = new Administrador(nombre: 'Juan', constrasenia: '123', email: 'juan@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1)
        marcelo.agregarReceta(empanadas)
        marcelo.calificar(opinion, empanadas)
        juan.borrarCalificacion(opinion)

        expect:
        marcelo.getCalificaciones().size() == 0
        empanadas.getCalificaciones().size() == 0
    }
}
