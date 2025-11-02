package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {
    @DisplayName("구입 금액만큼 로또를 발행한다.")
    @Test
    void 구입_금액만큼_로또를_발행한다() {
        LottoMachine machine = new LottoMachine();

        List<Lotto> lottos = machine.purchase(5000);

        assertThat(lottos).hasSize(5);
    }

    @DisplayName("발행된 로또는 6개의 번호를 가진다.")
    @Test
    void 발행된_로또는_6개의_번호를_가진다() {
        LottoMachine machine = new LottoMachine();

        List<Lotto> lottos = machine.purchase(1000);

        assertThat(lottos.get(0).getNumbers()).hasSize(6);
    }

    @DisplayName("발행된 로또 번호는 1~45 범위 내에 있다.")
    @Test
    void 발행된_로또_번호는_범위_내에_있다() {
        LottoMachine machine = new LottoMachine();

        List<Lotto> lottos = machine.purchase(1000);
        List<Integer> numbers = lottos.get(0).getNumbers();

        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }
}