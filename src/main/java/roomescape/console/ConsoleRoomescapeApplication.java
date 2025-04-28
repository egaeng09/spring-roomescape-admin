package roomescape.console;

import roomescape.console.controller.ConsoleController;

public class ConsoleRoomescapeApplication {

    public static void main(String[] args) {
        ConsoleConfig consoleConfig = new ConsoleConfig();
        ConsoleController consoleController = consoleConfig.consoleController();
        consoleController.run();
    }
}
