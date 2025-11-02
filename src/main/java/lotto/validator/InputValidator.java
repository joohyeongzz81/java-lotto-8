package lotto.validator;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    private static final int LOTTO_PRICE = 1000;

    public static int validatePurchaseAmount(String input) {
        int amount = parseNumber(input, "구입 금액은 숫자여야 합니다.");
        validatePositive(amount);
        validateDivisible(amount);
        return amount;
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여야 합니다.");
        }
    }

    private static void validateDivisible(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        
        for (String token : tokens) {
            int number = parseNumber(token.trim(), "당첨 번호는 숫자여야 합니다.");
            numbers.add(number);
        }
        
        return numbers;
    }

    public static int validateBonusNumber(String input) {
        return parseNumber(input, "보너스 번호는 숫자여야 합니다.");
    }

    private static int parseNumber(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] " + errorMessage);
        }
    }
}