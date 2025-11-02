package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import java.util.Collections;
import java.util.List;

public class OutputView {
    public void printPurchaseResult(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        List<Integer> sortedNumbers = new java.util.ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        System.out.println(sortedNumbers);
    }

    public void printStatistics(LottoResult result, double profitRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        printRankResult(Rank.FIFTH, result);
        printRankResult(Rank.FOURTH, result);
        printRankResult(Rank.THIRD, result);
        printRankResult(Rank.SECOND, result);
        printRankResult(Rank.FIRST, result);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    private void printRankResult(Rank rank, LottoResult result) {
        System.out.printf("%d개 일치", rank.getMatchCount());
        if (rank.isMatchBonus()) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.printf(" (%,d원) - %d개%n", rank.getPrize(), result.getCount(rank));
    }

    public void printError(String message) {
        System.out.println(message);
    }
}