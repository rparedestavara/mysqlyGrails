package morfi

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RestauranteSpec extends Specification implements DomainUnitTest<Restaurante> {

    def setup() {
    }

    def cleanup() {
    }

    void "test cuando un comensal intenta reservar y previamente tuvo una llegada tarde a otra reserva la nueva reserva requiere pago de seña"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva1 = comensal.reservar(restaurante, java.time.LocalDateTime.now().minusMinutes(20))
        reserva1.concretar()

        Reserva reserva2 = comensal.reservar(restaurante, java.time.LocalDateTime.now())

      then:
        reserva2.seña == Reserva.Seña.IMPAGA
    }

    void "test no puedo concretar una hora antes"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva = comensal.reservar(restaurante, java.time.LocalDateTime.now().plusMinutes(65))
        reserva.concretar()
      then:
        MuyTempranoAmigoException ex = thrown()
    }

    void "test 30 minutos antes puedo concretar la reserva"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva = comensal.reservar(restaurante, java.time.LocalDateTime.now().plusMinutes(30))
        reserva.concretar()
      then:
        reserva.fechaHoraAsistencia != null
    }

    void "test una reserva anulada ya no se puede concretar"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva = comensal.reservar(restaurante, java.time.LocalDateTime.now())
        reserva.anular()
        reserva.concretar()
      then:
        IllegalStateException ex = thrown()
    }

    void "test una reserva concretada ya no se puede anular"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva = comensal.reservar(restaurante, java.time.LocalDateTime.now())
        reserva.concretar()
        reserva.anular()
      then:
        IllegalStateException ex = thrown()
    }

    void "test luego de concretar una reserva se coloca la fecha y hora de asistencia"() {
      when:
        Comensal comensal = new Comensal('pablo.cosso', 'Pablo', 'Cosso', 'pablo@cosso.com')
        Restaurante restaurante = new Restaurante('El Obrero')

        Reserva reserva = comensal.reservar(restaurante, java.time.LocalDateTime.now())
        reserva.concretar()
      then:
        reserva.fechaHoraAsistencia != null
    }
}
