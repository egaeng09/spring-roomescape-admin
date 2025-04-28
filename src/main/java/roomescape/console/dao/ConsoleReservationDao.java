package roomescape.console.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import roomescape.reservation.dao.ReservationDao;
import roomescape.reservation.domain.Reservation;

public class ConsoleReservationDao implements ReservationDao {

    private final AtomicLong index;
    private final List<Reservation> reservations;

    public ConsoleReservationDao() {
        this.index = new AtomicLong(1L);
        this.reservations = new ArrayList<>();
    }

    @Override
    public Reservation insert(Reservation requestReservation) {
        Reservation reservation = new Reservation(index.getAndIncrement(), requestReservation);
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return reservations;
    }

    @Override
    public void delete(long id) {
        Reservation targetReservation = findById(id);
        reservations.remove(targetReservation);
    }

    private Reservation findById(long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 예약을 찾을 수 없습니다."));
    }
}
