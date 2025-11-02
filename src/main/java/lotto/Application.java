package lotto;

import lotto.domain.*;
import lotto.service.LottoMachine;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        
        int amount = readPurchaseAmountWithRetry(inputView, outputView);
        List<Lotto> lottos = purchaseLottos(amount, outputView);
        WinningNumbers winningNumbers = readWinningNumbersWithRetry(inputView, outputView);
        
        LottoResult result = matchLottos(lottos, winningNumbers);
        outputView.printStatistics(result, result.calculateProfitRate(amount));
    }

    private static int readPurchaseAmountWithRetry(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return InputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private static List<Lotto> purchaseLottos(int amount, OutputView outputView) {
        LottoMachine machine = new LottoMachine();
        List<Lotto> lottos = machine.purchase(amount);
        outputView.printPurchaseResult(lottos.size());
        outputView.printLottos(lottos);
        return lottos;
    }

    private static WinningNumbers readWinningNumbersWithRetry(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                List<Integer> winningNumbers = readWinningNumbers(inputView);
                int bonusNumber = readBonusNumber(inputView);
                return new WinningNumbers(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private static List<Integer> readWinningNumbers(InputView inputView) {
        String input = inputView.readWinningNumbers();
        return InputValidator.parseWinningNumbers(input);
    }

    private static int readBonusNumber(InputView inputView) {
        String input = inputView.readBonusNumber();
        return InputValidator.validateBonusNumber(input);
    }

    private static LottoResult matchLottos(List<Lotto> lottos, WinningNumbers winningNumbers) {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);
            result.add(rank);
        }
        return result;
    }
}