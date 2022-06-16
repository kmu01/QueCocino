package quecocino

class Perfil {

    String URL_imagen
    String descripcion
    private def recetas = []

    static belongsTo = [user: Usuario]

    static constraints = {
        descripcion size: 0..500
    }

    Perfil(String imagen = null, String desc = null){
        this.URL_imagen = imagen
        this.descripcion = desc
    }

    actualizarPerfil(String imagen, String desc){
        this.URL_imagen = imagen
        this.descripcion = desc
    }

    agregarReceta(Receta r){
        this.recetas.add(r)
    }

    borrarReceta(Receta r){
        if (recetas.contains(r)){
            this.recetas.remove(r)
            r.borrarReceta()
        }
    }

    borrarPerfil(){
        for (i in recetas){
            i.borrarReceta()
        }
    }

}
