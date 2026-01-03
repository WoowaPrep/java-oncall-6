package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();


    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
