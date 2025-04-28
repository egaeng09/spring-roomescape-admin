package roomescape.console.controller;

public class ExceptionHandler {
    public static void retry(final Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            retry(action);
        }
    }
}
