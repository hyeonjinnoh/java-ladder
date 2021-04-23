package step2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static step2.domain.LadderType.EMPTY;
import static step2.domain.LadderType.LEFT;
import static step2.domain.LadderType.RIGHT;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.LadderType;
import step2.domain.Line;
import step2.domain.LineStrategy;

public class LineTest {

    @Test
    @DisplayName("사다리 생성전략 기본값이 EMPTY")
    void createLineByEmpty() {
        Line line = new Line(4, new LineStrategy() {
            @Override
            public LadderType next() {
                return EMPTY;
            }

            @Override
            public LadderType next(LadderType prev) {
                if (prev == RIGHT) {
                    return LEFT;
                }
                return EMPTY;
            }
        });
        assertFirstPoint(EMPTY, line.points().get(0));
        assertOtherPoints(EMPTY, line.points());
    }

    @Test
    @DisplayName("사다리 생성전략 기본값이 RIGHT")
    void createLineByRight() {
        Line line = new Line(4, new LineStrategy() {
            @Override
            public LadderType next() {
                return RIGHT;
            }

            @Override
            public LadderType next(LadderType prev) {
                if (prev == RIGHT) {
                    return LEFT;
                }
                return RIGHT;
            }
        });
        assertFirstPoint(RIGHT, line.points().get(0));
        assertOtherPoints(RIGHT, line.points());
    }

    @Test
    @DisplayName("사다리타입이 RIGHT이면 col이 1증가")
    void increaseColByRIGHT() {
        Line line = new Line(4, new LineStrategy() {
            @Override
            public LadderType next() {
                return RIGHT;
            }

            @Override
            public LadderType next(LadderType prev) {
                return this.next();
            }
        });

        assertEquals(2, line.next(1));
    }

    @Test
    @DisplayName("사다리타입이 LEFT이면 col이 1감소")
    void increaseColByLEFT() {
        Line line = new Line(4, new LineStrategy() {
            @Override
            public LadderType next() {
                return LEFT;
            }

            @Override
            public LadderType next(LadderType prev) {
                return this.next();
            }
        });

        assertEquals(0, line.next(1));
    }

    @Test
    @DisplayName("사다리타입이 EMPTY이면 col이 그대로")
    void increaseColByEMPTY() {
        Line line = new Line(4, new LineStrategy() {
            @Override
            public LadderType next() {
                return EMPTY;
            }

            @Override
            public LadderType next(LadderType prev) {
                return this.next();
            }
        });

        assertEquals(1, line.next(1));
    }

    private void assertFirstPoint(LadderType expected, LadderType firstPoint) {
        assertEquals(expected, firstPoint);
    }

    private void assertOtherPoints(LadderType expected, List<LadderType> others) {
        for (int i = 0; i < others.size() - 1; ++i) {
            assertNextPoint(expected, others.get(i), others.get(i + 1));
        }
    }

    private void assertNextPoint(LadderType expected, LadderType prev, LadderType next) {
        if (prev == RIGHT) {
            assertEquals(LEFT, next);
            return;
        }
        assertEquals(expected, next);
    }
}
