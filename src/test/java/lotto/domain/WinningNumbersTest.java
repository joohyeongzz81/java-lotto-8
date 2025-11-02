package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {
    @DisplayName("정상적인 당첨 번호와 보너스 번호로 생성할 수 있다.")
    @Test
    void 정상적인_당첨_번호_생성() {
        assertThatCode(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("6개 모두 일치하면 1등이다.")
    @Test
    void 일등_당첨() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank rank = winningNumbers.match(lotto);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다.")
    @Test
    void 이등_당첨() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winningNumbers.match(lotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하지 않으면 3등이다.")
    @Test
    void 삼등_당첨() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        Rank rank = winningNumbers.match(lotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("일치하는 번호가 없으면 낙첨이다.")
    @Test
    void 낙첨() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(10, 11, 12, 13, 14, 15));

        Rank rank = winningNumbers.match(lotto);

        assertThat(rank).isEqualTo(Rank.NONE);
    }
}