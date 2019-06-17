package morfi

import grails.gorm.transactions.Transactional

@Transactional
class ReservaService {

    def reservar(Long comensalId, Long restauranteId, String fecha) {
      Restaurante restaurante = Restaurante.get(restauranteId)
      Comensal comensal = Comensal.get(comensalId)
      assert restaurante != null
      assert comensal != null

      comensal.reservar(restaurante, java.time.LocalDateTime.now().plusMinutes(new Random().nextInt())).save(failOnError: true)
    }

    def concretarReserva(Long reservaId) {
      Reserva.get(reservaId).concretar()
    }
}
