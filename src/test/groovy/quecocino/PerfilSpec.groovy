package quecocino

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PerfilSpec extends Specification implements DomainUnitTest<Perfil> {

    void "Al agregar una nueva receta, esta se guarda correctamente en el perfil"(){
        setup:
        def marcelop = new Perfil()
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: marcelop)
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.agregarReceta(empanadas)

        expect:
        marcelop.getRecetas().size() == 1
    }

    void "La receta se borra correctamente"(){
        setup:
        def marcelop = new Perfil()
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: marcelop)
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.agregarReceta(empanadas)
        marcelop.borrarReceta(empanadas)

        expect:
        marcelop.getRecetas().size() == 0
    }

    void "Eliminar un perfil elimina las recetas, y las calificaciones de estas"() {
        setup:
        def marcelop = new Perfil()
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: marcelop)
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1)
        marcelo.agregarReceta(empanadas)
        marcelo.calificar(opinion, empanadas)
        marcelo.borrarPerfil()

        expect:
        marcelo.getPerfil().getRecetas().size() == 0
        marcelo.getCalificaciones().size() == 0
    }

    void "Perfil no puede eliminar una receta que no le pertenece"(){
        setup:
        def marcelop = new Perfil()
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')

        when : // Obtain object with an incomplete unsaved instance.
        marcelop.borrarReceta(empanadas)
        then:
        Exception e = thrown()
    }
}
