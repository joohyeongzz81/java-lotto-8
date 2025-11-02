package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.validator.InputValidator;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputValidatorTest {
    @DisplayName("정상적인 구입 금액을 검증할 수 있다.")
    @Test
    void 정상적인_구입_금액_검증() {
        int amount = InputValidator.validatePurchaseAmount("5000");

        assertThat(amount).isEqualTo(5000);
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("5500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 0 이하면 예외가 발생한다.")
    @Test
    void 구입_금액이_0_이하면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호를 쉼표로 구분하여 파싱할 수 있다.")
    @Test
    void 당첨_번호_파싱() {
        List<Integer> numbers = InputValidator.parseWinningNumbers("1,2,3,4,5,6");

        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호에 공백이 있어도 파싱할 수 있다.")
    @Test
    void 당첨_번호에_공백이_있어도_파싱() {
        List<Integer> numbers = InputValidator.parseWinningNumbers("1, 2, 3, 4, 5, 6");

        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void 당첨_번호가_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.parseWinningNumbers("1,2,a,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상적인 보너스 번호를 검증할 수 있다.")
    @Test
    void 정상적인_보너스_번호_검증() {
        int bonusNumber = InputValidator.validateBonusNumber("7");

        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void 보너스_번호가_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("a"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}