package quecocino

class Calificacion {

    int puntaje
    String comentario

    static belongsTo = [user: Usuario, receta: Receta]

    static constraints = {
        comentario size: 0..255, nullable: true
        puntaje inList: [-1,1], nullable: false
    }

    void borrarCalificacion(){
        if (user.getCalificaciones().contains(this)) user.borrarCalificacion(this)
        receta.borrarCalificacion(this)
    }

}
