package quecocino

class Receta {

    String titulo
    String descripcion
    int id
    def etiquetas = []

    static hasMany = [calificaciones: Calificacion]

    static belongsTo = [perfil: Perfil]


    static constraints = {
        id blank: false, unique: true
        titulo size: 1..100, blank: false
        descripcion blank: false
    }

    void agregarCalificacion(Calificacion c){
        this.addToCalificaciones(c)
    }

    void borrarCalificacion(Calificacion c){
        calificaciones.remove(c)
    }

    void borrarReceta(){
        for (i in calificaciones) i.borrarCalificacion()
        if (perfil.getRecetas().contains(this)) perfil.borrarReceta(this)
    }
}
