package morfi

@groovy.transform.InheritConstructors
class MuyTempranoAmigoException extends RuntimeException {
}

class Reserva {

    enum Seña {
      SIN_SEÑA,
      PAGA,
      IMPAGA,
    }

    interface Estado {
      void concretar(Reserva reserva)
      void anular(Reserva reserva)
    }

    class Pendiente implements Estado {
      void concretar(Reserva reserva) {
        def ahora = java.time.LocalDateTime.now()
        if (ahora < reserva.fechaHora.minusMinutes(60)) {
          throw new MuyTempranoAmigoException("no se programar")
        }
        reserva.fechaHoraAsistencia = ahora
        reserva.cambiarEstado(new Concretado())
      }
      void anular(Reserva reserva) {
        reserva.cambiarEstado(new Anulada())
      }
    }

    class Concretado implements Estado {
      void concretar(Reserva reserva) {
        throw new IllegalStateException()
      }
      void anular(Reserva reserva) {
        throw new IllegalStateException()
      }
    }

    class Anulada implements Estado {
      void concretar(Reserva reserva) {
        throw new IllegalStateException()
      }
      void anular(Reserva reserva) {
        throw new IllegalStateException()
      }
    }

    Comensal comensal
    Restaurante restaurante
    //   Estado estado = new Pendiente()
    Seña seña = Seña.SIN_SEÑA
    java.time.LocalDateTime fechaHora
    java.time.LocalDateTime fechaHoraAsistencia

    static constraints = {
      fechaHoraAsistencia nullable: true
    }

    Reserva(Comensal comensal, Restaurante restaurante, java.time.LocalDateTime fechaHora) {
      this.comensal = comensal
      this.restaurante = restaurante
      this.fechaHora = fechaHora
    }

    boolean getLlegadaTarde() {
      if (fechaHoraAsistencia) {
        this.fechaHoraAsistencia > this.fechaHora.plusMinutes(15)
      }
    }

    void concretar() {
      this.estado.concretar(this)
    }

    void anular() {
      this.estado.anular(this)
    }

    private void cambiarEstado(Estado nuevo) {
      this.estado = nuevo
    }
}
