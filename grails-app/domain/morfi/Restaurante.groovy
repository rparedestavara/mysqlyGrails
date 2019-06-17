package morfi

class Restaurante {

    String nombre
    String ubicacion

    Set<Reserva> reservas = []

    static hasMany = [
      reservas: Reserva,
    ]

    static constraints = {
      nombre nullable: false
      ubicacion nullable: false
    }

    Restaurante(String nombre) {
      this.nombre = nombre
    }
}
