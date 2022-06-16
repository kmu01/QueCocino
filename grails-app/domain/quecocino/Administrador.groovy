package quecocino

class Administrador extends Usuario {

    Boolean puedeDarAdmin

    static constraints = {
        puedeDarAdmin nullable: false
    }

    void banearUsuario(Usuario user){
        user.serBaneado()
    }

    @Override
    void borrarCalificacion(Calificacion c) {
        c.borrarCalificacion()
    }

    @Override
    void borrarReceta(Receta r) {
        r.borrarReceta()
    }
}
