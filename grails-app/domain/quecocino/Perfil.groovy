package quecocino

class Perfil {

    String URL_imagen
    String descripcion

    static belongsTo = [user: Usuario]

    static hasMany = [recetas: Receta]

    static constraints = {
        descripcion size: 0..500
    }

    void actualizarPerfil(String imagen, String desc){
        this.URL_imagen = imagen
        this.descripcion = desc
    }

    void agregarReceta(Receta r){
        this.addToRecetas(r)
    }

    void borrarReceta(Receta r){
        if (recetas.find{it == r}) {
            this.recetas.remove(r)
            r.borrarReceta()
        }
        else throw new Exception("Esta receta no fue creada por este usuario")
    }

    void borrarPerfil(){
        for (i in recetas){
            i.borrarReceta()
        }
    }

}
