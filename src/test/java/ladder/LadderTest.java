package ladder;

import ladder.domain.Ladder;
import ladder.exception.InvalidLadderException;
import ladder.factory.RowFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class LadderTest {

    @Test
    @DisplayName("사다리 참여 인원이 2명 이하일 경우 에러 발생한다")
    public void line_duplicate() {
        assertThatExceptionOfType(InvalidLadderException.class)
            .isThrownBy(() -> {
                new Ladder(5, 1, new RowFactory());
            })
            .withMessageMatching("사다리게임 참여 인원이 부족합니다");
    }

    @Test
    @DisplayName("사다리 높이만큼 다리를 놓을 수 있다")
    public void ladder_height() {
        assertThat(new Ladder(5, 4, new RowFactory()))
            .extracting(Ladder::rows)
            .asList()
            .hasSize(5);
    }

}
