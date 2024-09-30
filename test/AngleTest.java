import org.junit.jupiter.api.Test;
import ru.groza1337.Angle;
import ru.groza1337.TypeOfAngle;

import static org.junit.jupiter.api.Assertions.*;

public class AngleTest {

    @Test
    public void testAdd() {
        Angle angle1 = new Angle(30, false);
        Angle angle2 = new Angle(60, false);
        Angle result = angle1.add(angle2);
        assertEquals(90, result.getAngleInDegrees(), 0.001);
    }

    @Test
    public void testSubtract() {
        Angle angle1 = new Angle(60, false);
        Angle angle2 = new Angle(30, false);
        Angle result = angle1.subtract(angle2);
        assertEquals(30, result.getAngleInDegrees(), 0.001);
    }

    @Test
    public void testCopy() {
        Angle angle1 = new Angle(45, false);
        Angle copy = angle1.copy();
        assertEquals(45, copy.getAngleInDegrees());
    }

    @Test
    public void testTypeOfAngle() {
        Angle angle1 = new Angle(90, false);
        assertEquals(TypeOfAngle.Square, angle1.determineTypeOfAngle());
    }

    @Test
    public void testCompare() {
        Angle angle1 = new Angle(30, false);
        Angle angle2 = new Angle(60, false);
        assertTrue(angle1.compare(angle2) < 0);
    }
}
