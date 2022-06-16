package quecocino

import java.time.LocalDate

class Usuario {

    String nombre
    String email
    String contrasenia
    private def calificaciones = []
    private LocalDate baneadoHasta
    private Perfil perfil

    static constraints = {
        nombre size: 5..15, blank: false, unique: true
        contrasenia size: 5..15, blank: false, password: true
        email email: true, blank: false, unique: true
    }

    boolean estaBaneado(){
        return LocalDate.now() < baneadoHasta
    }

    void serBaneado(long cantidadDeDias){
        this.baneadoHasta = LocalDate.now().plusDays(cantidadDeDias)
    }

    void crearReceta(Receta r){
        if (estaBaneado()) throw new Exception("Usuario baneado");
        perfil.agregarReceta(r)
    }

    void calificar(Calificacion c, Receta r){
        if (estaBaneado()) throw new Exception("Usuario baneado");
        if (calificaciones.find(c.getReceta() == r)) throw new Exception("Usuario baneado");
        r.agregarCalificacion(c)
        calificaciones.add(c)
    }

    void actualizarPerfil(String url_imagen, String desc){
        perfil.actualizarPerfil(url_imagen, desc)
    }

    void borrarReceta(Receta r){
        perfil.borrarReceta(r)
    }

    void borrarPerfil(){
        this.perfil.borrarPerfil()
        this.perfil = new Perfil()
    }

    void borrarCalificacion(Calificacion c){
        if (this.calificaciones.contains(c)){
            this.calificaciones.remove(c)
            c.borrarCalificacion()
        }
    }
}
