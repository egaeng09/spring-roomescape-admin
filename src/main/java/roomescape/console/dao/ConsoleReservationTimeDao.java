package roomescape.console.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import roomescape.time.dao.ReservationTimeDao;
import roomescape.time.domain.ReservationTime;

public class ConsoleReservationTimeDao implements ReservationTimeDao {

    private final AtomicLong index;
    private final List<ReservationTime> reservationTimes;

    public ConsoleReservationTimeDao() {
        this.index = new AtomicLong(1L);
        this.reservationTimes = new ArrayList<>();
    }

    @Override
    public ReservationTime insert(ReservationTime requestReservationTime) {
        if (isContain(requestReservationTime.getStartAt())) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 시간입니다.");
        }

        ReservationTime reservationTime = new ReservationTime(index.getAndIncrement(), requestReservationTime);
        reservationTimes.add(reservationTime);
        return reservationTime;
    }

    @Override
    public List<ReservationTime> findAll() {
        return reservationTimes;
    }

    @Override
    public void delete(long id) {
        ReservationTime targetReservationTime = findById(id);
        reservationTimes.remove(targetReservationTime);
    }

    @Override
    public ReservationTime findById(long id) {
        return reservationTimes.stream()
                .filter(reservationTime -> reservationTime.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 시간을 찾을 수 없습니다."));
    }

    public boolean isContain(LocalTime time) {
        return reservationTimes.stream()
                .anyMatch(reservationTime -> reservationTime.getStartAt().equals(time));
    }
}
