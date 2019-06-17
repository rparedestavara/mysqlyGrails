package morfi

class ReservaCommand {
  Long comensal
  Long restaurante
  String fecha

  static constraints = {
    comensal nullable: false
    restaurante nullable: false
    fecha nullable: false
  }
}

class ReservaController {

    def reservaService

    def index() {
      [
        restaurantes: Restaurante.list(),
        comensales: Comensal.list(),
      ]
    }

    def datosDePrueba() {
      new Restaurante(nombre: 'El Obrero', ubicacion: 'ubicacion').save(failOnError: true)
      new Restaurante(nombre: 'La tacita', ubicacion: 'ubicacion').save(failOnError: true)

      new Comensal('nombreDeUsuario 1', 'nombre', 'apellido', 'email@email.com').save(failOnError: true)
      new Comensal('nombreDeUsuario 2', 'nombre', 'apellido', 'email@email.com').save(failOnError: true)
      new Comensal('nombreDeUsuario 3', 'nombre', 'apellido', 'email@email.com').save(failOnError: true)
      new Comensal('nombreDeUsuario 4', 'nombre', 'apellido', 'email@email.com').save(failOnError: true)

      render "todo ok"
    }

    def reservar(ReservaCommand cmd) {
      if (!cmd.hasErrors()) {
        assert reservaService != null
        def reserva = reservaService.reservar(cmd.comensal, cmd.restaurante, 'fecha')
        render "OK ${reserva}"
      } else {
        render "hay errores ${cmd.errors}"
      }
    }
}
