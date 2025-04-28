package roomescape.console.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import roomescape.reservation.dao.ReservationDao;
import roomescape.reservation.domain.Reservation;

public class ConsoleReservationDao implements ReservationDao {

    private static Long index = 1L;

    private final List<Reservation> reservations;

    public ConsoleReservationDao() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public Reservation insert(Reservation requestReservation) {
        Reservation reservation = new Reservation(index++, requestReservation);
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return reservations;
    }

    @Override
    public void delete(long id) {
        if (reservations.size() < id) {
            throw new NoSuchElementException("데이터베이스에 해당 id가 존재하지 않습니다.");
        }
        Reservation targetReservation = findById(id);
        reservations.remove(targetReservation);
    }

    private Reservation findById(long id) {
        return reservations.get((int)(id - 1));
    }
}
