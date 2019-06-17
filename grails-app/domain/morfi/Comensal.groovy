package morfi

class Comensal {

    String nombreDeUsuario
    String nombre
    String apellido
    String email

    Set<Reserva> reservas = []

    static hasMany = [
      reservas: Reserva,
    ]

    static constraints = {
      nombreDeUsuario nullable: false, blank: false
      nombre nullable: false, blank: false
      apellido nullable: false, blank: false
      email nullable: false, blank: false, email: true
    }

    Comensal(String nombreDeUsuario, String nombre, String apellido, String email) {
      this.nombreDeUsuario = nombreDeUsuario
      this.nombre = nombre
      this.apellido = apellido
      this.email = email
    }

    Reserva reservar(Restaurante restaurante, java.time.LocalDateTime fechaHora) {
      final boolean requiereSeña = this.reservas.any { Reserva reserva ->
        reserva.llegadaTarde
      }
      Reserva reserva = new Reserva(this, restaurante, fechaHora)
      if (requiereSeña) {
        reserva.seña = Reserva.Seña.IMPAGA
      }
      this.reservas << reserva
      reserva
    }
}
