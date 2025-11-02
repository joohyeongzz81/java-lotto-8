package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Rank;

import static org.assertj.core.api.Assertions.*;

class RankTest {
    @DisplayName("6개 일치하면 1등이다.")
    @Test
    void 일등_판정() {
        Rank rank = Rank.valueOf(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등이다.")
    @Test
    void 이등_판정() {
        Rank rank = Rank.valueOf(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("5개 일치하면 3등이다.")
    @Test
    void 삼등_판정() {
        Rank rank = Rank.valueOf(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000);
    }

    @DisplayName("4개 일치하면 4등이다.")
    @Test
    void 사등_판정() {
        Rank rank = Rank.valueOf(4, false);

        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000);
    }

    @DisplayName("3개 일치하면 5등이다.")
    @Test
    void 오등_판정() {
        Rank rank = Rank.valueOf(3, false);

        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("2개 이하 일치하면 낙첨이다.")
    @Test
    void 낙첨_판정() {
        Rank rank = Rank.valueOf(2, false);

        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0);
    }

    @DisplayName("보너스 볼이 일치해도 5개 미만이면 등수에 영향이 없다.")
    @Test
    void 보너스_볼_영향_없음() {
        Rank rank = Rank.valueOf(4, true);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }
}