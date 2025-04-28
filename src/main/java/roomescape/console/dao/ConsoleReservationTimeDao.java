package roomescape.console.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import roomescape.time.dao.ReservationTimeDao;
import roomescape.time.domain.ReservationTime;

public class ConsoleReservationTimeDao implements ReservationTimeDao {

    private static Long index = 1L;

    private final List<ReservationTime> reservationTimes;

    public ConsoleReservationTimeDao() {
        this.reservationTimes = new ArrayList<>();
    }

    @Override
    public ReservationTime insert(ReservationTime requestReservationTime) {
        ReservationTime reservationTime = new ReservationTime(index++, requestReservationTime);
        reservationTimes.add(reservationTime);
        return reservationTime;
    }

    @Override
    public List<ReservationTime> findAll() {
        return reservationTimes;
    }

    @Override
    public void delete(long id) {
        if (reservationTimes.size() < id) {
            throw new NoSuchElementException("데이터베이스에 해당 id가 존재하지 않습니다.");
        }
        ReservationTime targetReservationTime = findById(id);
        reservationTimes.remove(targetReservationTime);
    }

    @Override
    public ReservationTime findById(long id) {
        return reservationTimes.get((int)(id - 1));
    }
}
