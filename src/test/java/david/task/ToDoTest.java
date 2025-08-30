package david.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoTest() throws Exception {
        ToDo test = new ToDo("test");
        assertEquals("T | 0 | test", test.toString());

        test.markAsDone();
        assertEquals("T | 1 | test", test.toString());

        test.markAsUndone();
        assertEquals("T | 0 | test", test.toString());

    }

    @Test
    public void DummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}