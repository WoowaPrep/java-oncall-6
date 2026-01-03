package oncall;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        try {
            new EmergencySchedule().assign();
        } finally {
            Console.close();
        }
    }
}
