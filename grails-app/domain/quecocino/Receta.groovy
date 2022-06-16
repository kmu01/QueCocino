package quecocino

class Receta {

    String titulo
    String descripcion
    int id
    def ingredientes = []
    def etiquetas = []
    private def calificaciones = []

    static belongsTo = [perfil: Perfil]


    static constraints = {
        id blank: false, unique: true
        titulo size: 1..100, blank: false
        descripcion blank: false
    }

    void agregarCalificacion(Calificacion c){
        calificaciones.add(c)
    }

    void borrarCalificacion(Calificacion c){
        calificaciones.remove(c)
    }

    borrarReceta(){
        for (i in calificaciones){
            i.borrarCalificacion()
        }
        perfil.borrarReceta(this)
    }
}
