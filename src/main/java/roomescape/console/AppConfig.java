package roomescape.console;

import roomescape.console.controller.ConsoleController;
import roomescape.console.dao.ConsoleReservationDao;
import roomescape.console.dao.ConsoleReservationTimeDao;
import roomescape.console.view.ConsoleView;
import roomescape.reservation.dao.ReservationDao;
import roomescape.reservation.service.ReservationService;
import roomescape.reservation.service.utils.ReservationMapper;
import roomescape.time.dao.ReservationTimeDao;
import roomescape.time.service.ReservationTimeService;
import roomescape.time.service.utils.ReservationTimeMapper;

public class AppConfig {

    public ConsoleController consoleController() {
        ReservationDao reservationDao = new ConsoleReservationDao();
        ReservationTimeDao reservationTimeDao = new ConsoleReservationTimeDao();

        ReservationTimeMapper reservationTimeMapper = new ReservationTimeMapper();
        ReservationMapper reservationMapper = new ReservationMapper(reservationTimeMapper);

        ReservationService reservationService = new ReservationService(reservationDao, reservationTimeDao, reservationMapper);
        ReservationTimeService reservationTimeService = new ReservationTimeService(reservationTimeDao, reservationTimeMapper);

        ConsoleView consoleView = new ConsoleView();

        return new ConsoleController(reservationService, reservationTimeService, consoleView);
    }
}
