import static org.junit.Assert.*;
import org.junit.Test;

public final class ScoreCheckerTest {

    @Test
    public void equivalencePartitioning() {
        String result = ScoreChecker.check(-100, -100, false);
        assertEquals("Scorul nu este valid", result);

        result = ScoreChecker.check(1000, 500, true);
        assertEquals("Scorul nu este valid", result);

        result = ScoreChecker.check(250, -250, false);
        assertEquals("Numărul de jocuri nu este valid", result);

        result = ScoreChecker.check(250, 250, true);
        assertEquals("Numărul de jocuri nu este valid", result);

        result = ScoreChecker.check(7, 2, true);
        assertEquals("Da", result);

        result = ScoreChecker.check(6, 2, false);
        assertEquals("Da", result);

        result = ScoreChecker.check(11, 1, true);
        assertEquals("Nu", result);

        result = ScoreChecker.check(7, 2, false);
        assertEquals("Nu", result);
    }

    @Test
    public void boundaryValueAnalysis() {
        /* targetScore */
        String result = ScoreChecker.check(0, 10, false);
        assertEquals("Scorul nu este valid", result);

        result = ScoreChecker.check(1, 10, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(2, 10, true);
        assertEquals("Da", result);

        result = ScoreChecker.check(498, 100, false);
        assertEquals("Da", result);

        result = ScoreChecker.check(500, 10, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(501, 10, false);
        assertEquals("Scorul nu este valid", result);

        /* maxPlays */

        result = ScoreChecker.check(7, 0, false);
        assertEquals("Numărul de jocuri nu este valid", result);

        result = ScoreChecker.check(7, 1, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(6, 2, false);
        assertEquals("Da", result);

        result = ScoreChecker.check(7, 100, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(7, 101, false);
        assertEquals("Numărul de jocuri nu este valid", result);

        /* allow */

        result = ScoreChecker.check(2, 1, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(2, 1, true);
        assertEquals("Da", result);
    }

    @Test
    public void causeEffectGraphing() {
        String result = ScoreChecker.check(-100, -100, false);
        assertEquals("Scorul nu este valid", result);

        result = ScoreChecker.check(50, -100, false);
        assertEquals("Numărul de jocuri nu este valid", result);

        result = ScoreChecker.check(6, 2, false);
        assertEquals("Da", result);

        result = ScoreChecker.check(1, 1, false);
        assertEquals("Nu", result);
    }

    @Test
    public void modifiedConditionDecisionCoverage() {
        /* D1 */
        String result = ScoreChecker.check(12, 3, true);
        assertEquals("Da", result);

        result = ScoreChecker.check(-10, 10, false);
        assertEquals("Scorul nu este valid", result);

        result = ScoreChecker.check(1345, 32, true);
        assertEquals("Scorul nu este valid", result);

        /* D2 */
        result = ScoreChecker.check(73, 4, true);
        assertEquals("Nu", result);

        result = ScoreChecker.check(12, -23, false);
        assertEquals("Numărul de jocuri nu este valid", result);

        result = ScoreChecker.check(12, 254, true);
        assertEquals("Numărul de jocuri nu este valid", result);

        /* D3 */
        result = ScoreChecker.check(6, 4, true);
        assertEquals("Da", result);

        result = ScoreChecker.check(1, 1, false);
        assertEquals("Nu", result);

        result = ScoreChecker.check(7, 4, false);
        assertEquals("Nu", result);
    }
}
