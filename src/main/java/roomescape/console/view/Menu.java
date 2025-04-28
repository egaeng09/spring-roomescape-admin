package roomescape.console.view;

import java.util.Arrays;

public enum Menu {
    FIRST("1"),
    SECOND("2"),
    THIRD("3"),
    FOURTH("4"),
    FIFTH("5"),
    SIXTH("6"),
    QUIT("Q");

    private final String title;

    Menu(String title) {
        this.title = title;
    }

    public static Menu of(String input) {
        return Arrays.stream(values())
                .filter(menu -> menu.title.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메뉴를 잘못 입력하였습니다."));
    }
}
