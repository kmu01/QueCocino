package quecocino

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RecetaSpec extends Specification implements DomainUnitTest<Receta> {

    void "Las opiniones se agregan correctamente"() {
        setup:
        def opinion = new Calificacion(puntaje: 1, comentario: 'muy rico!11')
        def opinion2 = new Calificacion(puntaje: -1, comentario: 'se me prendio fuego la casa')
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        empanadas.agregarCalificacion(opinion)
        empanadas.agregarCalificacion(opinion2)

        expect:
        empanadas.getCalificaciones().size() == 2
    }

    void "Al borrar la receta se eliminan las calificaciones referidas a esta"() {
        setup:
        def opinion = new Calificacion(puntaje: 1, comentario: 'muy rico!11')
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.agregarReceta(empanadas)
        marcelo.calificar(opinion, empanadas)
        empanadas.borrarReceta()

        expect:
        marcelo.getCalificaciones().size() == 0
    }
}
