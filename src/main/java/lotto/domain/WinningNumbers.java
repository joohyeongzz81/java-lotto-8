package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningLotto = new Lotto(winningNumbers);
        validateBonus(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonus(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = winningLotto.countMatch(lotto.getNumbers());
        boolean matchBonus = lotto.contains(bonusNumber);
        return Rank.valueOf(matchCount, matchBonus);
    }
}