package roomescape.console.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;
import roomescape.time.dto.ReservationTimeRequest;
import roomescape.time.dto.ReservationTimeResponse;

public class ConsoleView {

    private static final String ENTER_MENU_MESSAGE = """
            %n메뉴를 선택해주세요.
            1. 예약 가능 시간 목록 조회
            2. 예약 가능 시간 추가
            3. 예약 가능 시간 삭제
            4. 예약 내역 조회
            5. 예약 추가
            6. 예약 삭제
            Q. 프로그램 종료%n
            """;
    private static final String ENTER_ADD_TIME = "추가를 원하는 시간을 입력해주세요. (ex. 12:00)";
    private static final String ENTER_NAME = "예약자의 성함을 입력해주세요.";
    private static final String ENTER_RESERVATION_DATE = "예약을 원하는 날짜를 입력해주세요. (ex. 2024.10.12)";
    private static final String ENTER_RESERVATION_TIME = "%n예약을 원하는 시간의 번호를 입력해주세요. (ex. 1)";
    private static final String ENTER_DELETE_TIME = "%n삭제를 원하는 시간의 번호를 입력해주세요. (ex. 1)";
    private static final String ENTER_DELETE_RESERVATION = "%n삭제를 원하는 예약 내역의 번호를 입력해주세요. (ex. 1)";
    private static final String DONE_MESSAGE = "%n작업이 완료되었습니다.%n";

    private final Scanner scanner;

    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    public Menu enterMenu() {
        System.out.printf(ENTER_MENU_MESSAGE);
        return Menu.of(enter());
    }

    public void printDoneMessage() {
        System.out.printf(DONE_MESSAGE);
    }

    public void printReservationTimes(List<ReservationTimeResponse> reservationTimeResponses) {
        System.out.println("다음은 예약 가능한 시간 목록입니다.");
        reservationTimeResponses.forEach(reservationTimeResponse -> {
            System.out.printf("id. %d - %s%n", reservationTimeResponse.id(), reservationTimeResponse.startAt());
        });
    }

    public ReservationTimeRequest requestReservationTime() {
        try {
            System.out.println(ENTER_ADD_TIME);
            LocalTime time = LocalTime.parse(enter());
            return new ReservationTimeRequest(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 시간 형식입니다.");
        }
    }

    public long enterDeleteTimeId() {
        return enterId(ENTER_DELETE_TIME);
    }

    public void printReservations(List<ReservationResponse> reservationResponses) {
        System.out.println("다음은 예약 내역입니다.");
        reservationResponses.forEach(reservationResponse -> {
            System.out.printf("id. %d - %s 님 %s %s %n", reservationResponse.id(), reservationResponse.name(),
                    reservationResponse.date(), reservationResponse.time().startAt());
        });
    }

    public ReservationRequest requestReservation(List<ReservationTimeResponse> reservationTimeResponses) {
        String name = enterName();
        LocalDate date = enterReservationDate();
        printReservationTimes(reservationTimeResponses);
        long time = enterId(ENTER_RESERVATION_TIME);
        return new ReservationRequest(name, date, time);
    }

    public long enterDeleteReservationId() {
        return enterId(ENTER_DELETE_RESERVATION);
    }

    private long enterId(String message) {
        try {
            System.out.printf(message);
            return Long.parseLong(enter());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 ID입니다.");
        }
    }

    private LocalDate enterReservationDate() {
        try {
            System.out.println(ENTER_RESERVATION_DATE);
            return LocalDate.parse(enter(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 시간 형식입니다.");
        }
    }

    private String enterName() {
        System.out.println(ENTER_NAME);
        return enter();
    }

    private String enter() {
        return scanner.nextLine();
    }
}
