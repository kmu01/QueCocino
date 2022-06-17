package quecocino

class Administrador extends Usuario {

    static constraints = {

    }

    void banearUsuario(Usuario user, int tiempoEnDias){
        user.serBaneado(tiempoEnDias)
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
