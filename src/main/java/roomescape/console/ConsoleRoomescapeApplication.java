package roomescape.console;

import roomescape.console.controller.ConsoleController;

public class ConsoleRoomescapeApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ConsoleController consoleController = appConfig.consoleController();
        consoleController.run();
    }
}
