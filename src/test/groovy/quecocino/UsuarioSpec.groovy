package quecocino

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UsuarioSpec extends Specification implements DomainUnitTest<Usuario> {

    void "Las recetas y calificaciones se crean apropiadamente"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1)
        marcelo.agregarReceta(empanadas)
        marcelo.calificar(opinion, empanadas)

        expect:
        marcelo.getPerfil().getRecetas().size() == 1
        marcelo.getCalificaciones().size() == 1
        empanadas.getCalificaciones().size() == 1
    }

    void "Usuario no puede calificar 2 veces la misma receta"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1)
        def opinion2 = new Calificacion(puntaje: -1)
        marcelo.calificar(opinion, empanadas)

        when:
        marcelo.calificar(opinion2, empanadas)
        then:
        Exception e = thrown()
    }

    void "Usuario puede eliminar calificacion suya"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1)
        marcelo.agregarReceta(empanadas)
        marcelo.calificar(opinion, empanadas)
        marcelo.borrarCalificacion(opinion)

        expect:
        marcelo.getCalificaciones().size() == 0
        empanadas.getCalificaciones().size() == 0
    }

    void "Usuario puede eliminar receta suya"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.agregarReceta(empanadas)
        marcelo.borrarReceta(empanadas)
        expect:
        marcelo.getPerfil().getRecetas().size() == 0
    }

    void "Usuario no puede eliminar una calificacion que no es suya"() {
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com')
        def opinion = new Calificacion(puntaje: 1, comentario: 'muy rico!11')

        when : // Obtain object with an incomplete unsaved instance.
        marcelo.borrarCalificacion(opinion)
        then:
        Exception e = thrown()
    }

    void "Usuario baneado no puede calificar"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com')
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        def opinion = new Calificacion(puntaje: 1, comentario: 'muy rico!11')
        marcelo.serBaneado(20)

        when : // Obtain object with an incomplete unsaved instance.
        marcelo.calificar(opinion, empanadas)
        then:
        Exception e = thrown()
    }

    void "Usuario baneado no puede publicar una receta"(){
        setup:
        def marcelo = new Usuario(nombre: 'Marcelo', constrasenia: '123', email: 'marcelo@samplemail.com', perfil: new Perfil())
        def empanadas = new Receta(id: 123, titulo: 'empanada', descripcion: 'empanada')
        marcelo.serBaneado(20)

        when : // Obtain object with an incomplete unsaved instance.
        marcelo.agregarReceta(empanadas)
        then:
        Exception e = thrown()
    }

}
