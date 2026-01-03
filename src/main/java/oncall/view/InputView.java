package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final static String NEW_LINE = System.lineSeparator();
    private final static String MONTH_START_DAY_INPUT_MESSAGE =
            "비상 근무를 배정할 월과 시작 요일을 입력하세요>";

    public String readMonthStartDay() {
        System.out.print(MONTH_START_DAY_INPUT_MESSAGE);
        return Console.readLine();
    }

    private String readLine() {
        return Console.readLine();
    }

    private void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
