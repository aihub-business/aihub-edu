package courses;

import capital.aihub.courses.Course1DemoFruits;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Course1DemoFruitsTest {

    @Test
    void testFilterLongFruits() {
        List<String> fruits = List.of("jablko", "banán", "pomeranč", "hruška", "kiwi");
        List<String> expectedLongFruits = List.of("jablko", "pomeranč", "hruška");
        List<String> actualLongFruits = Course1DemoFruits.filterLongFruits(fruits);
        assertEquals(expectedLongFruits, actualLongFruits);
    }

    @Test
    void testFilterLongFruitsEmpty() {
        List<String> fruits = List.of();
        List<String> expectedLongFruits = List.of();
        List<String> actualLongFruits = Course1DemoFruits.filterLongFruits(fruits);
        assertEquals(expectedLongFruits, actualLongFruits);
    }

    @Test
    void testFilterLongFruitsNoLongFruits() {
        List<String> fruits = List.of("mango", "kiwi");
        List<String> expectedLongFruits = List.of();
        List<String> actualLongFruits = Course1DemoFruits.filterLongFruits(fruits);
        assertEquals(expectedLongFruits, actualLongFruits);
    }
}
