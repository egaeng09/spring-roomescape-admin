package roomescape.console.controller;

import java.util.List;
import roomescape.console.ExceptionHandler;
import roomescape.console.view.ConsoleView;
import roomescape.console.view.Menu;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;
import roomescape.reservation.service.ReservationService;
import roomescape.time.dto.ReservationTimeRequest;
import roomescape.time.dto.ReservationTimeResponse;
import roomescape.time.service.ReservationTimeService;

public class ConsoleController {

    private final ReservationService reservationService;
    private final ReservationTimeService reservationTimeService;
    private final ConsoleView consoleView;

    public ConsoleController(ReservationService reservationService, ReservationTimeService reservationTimeService,
                             ConsoleView consoleView) {
        this.reservationService = reservationService;
        this.reservationTimeService = reservationTimeService;
        this.consoleView = consoleView;
    }

    public void run() {
        ExceptionHandler.retry(this::selectMenu);
    }

    private void selectMenu() {
        Menu menu = consoleView.enterMenu();

        switch (menu) {
            case FIRST -> findTimes();
            case SECOND -> addTime();
            case THIRD -> deleteTime();
            case FOURTH -> findReservations();
            case FIFTH -> addReservation();
            case SIXTH -> deleteReservation();
        }
    }

    private void findTimes() {
        List<ReservationTimeResponse> reservationTimeResponses = reservationTimeService.findAllTimes();
        consoleView.printReservationTimes(reservationTimeResponses);
        selectMenu();
    }

    private void addTime() {
        ReservationTimeRequest reservationTimeRequest = consoleView.requestReservationTime();
        reservationTimeService.addTime(reservationTimeRequest);
        consoleView.printDoneMessage();
        selectMenu();
    }

    private void deleteTime() {
        long id = consoleView.enterDeleteTimeId();
        reservationTimeService.deleteTimeById(id);
        consoleView.printDoneMessage();
        selectMenu();
    }

    private void findReservations() {
        List<ReservationResponse> reservationResponses = reservationService.findAllReservations();
        consoleView.printReservations(reservationResponses);
        selectMenu();
    }

    private void addReservation() {
        List<ReservationTimeResponse> reservationTimeResponses = reservationTimeService.findAllTimes();
        ReservationRequest reservationRequest = consoleView.requestReservation(reservationTimeResponses);
        reservationService.addReservation(reservationRequest);
        consoleView.printDoneMessage();
        selectMenu();
    }

    private void deleteReservation() {
        long id = consoleView.enterDeleteReservationId();
        reservationService.deleteReservationById(id);
        consoleView.printDoneMessage();
        selectMenu();
    }
}
