package quecocino

import grails.gorm.transactions.Transactional

@Transactional
class EstimarPuntuacionTotalService {


    def estimarPuntaje(Receta receta) {

        //TODO: Hacer una query a las opiniones de la receta

    }

    def estimarPuntaje(Usuario usuario) {

        //TODO: Hacer una query a las recetas del usuario, y de allí obtener el puntaje de cada receta

    }
}
