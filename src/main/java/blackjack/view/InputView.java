package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + lineSeparator();
    private static final String INPUT_BETTING_MONEY = lineSeparator() + "%s의 배팅 금액은?" + lineSeparator();
    private static final String INPUT_INVALID_ANSWER = "[ERROR] y 또는 n만 입력 가능합니다.";
    private static final String INVALID_MONEY = "[ERROR] 투입 금액은 양의 정수여야 합니다.";
    private static final String USERNAME_SEPARATOR = ", |,";
    private static final String AGREE = "y";
    private static final String DISAGREE = "n";

    public static List<String> inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        return Arrays.stream(scanner.nextLine().split(USERNAME_SEPARATOR, -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean inputMoreCard(String name) {
        System.out.printf(INPUT_MORE_CARD_FORMAT, name);
        return isContinue(scanner.nextLine());
    }

    private static boolean isContinue(String userInput) {
        if (userInput.equals(AGREE)) {
            return true;
        }
        if (userInput.equals(DISAGREE)) {
            return false;
        }
        throw new IllegalArgumentException(INPUT_INVALID_ANSWER);
    }

    public static int inputBettingMoney(String name) {
        System.out.printf(INPUT_BETTING_MONEY, name);
        return validateNonIntegerAndConvert(scanner.nextLine());
    }

    private static int validateNonIntegerAndConvert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MONEY);
        }
    }
}
