package tasks.iterator;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.task.MultiTask;
import tasks.task.SimpleTask;
import tasks.task.Task;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class TestMultiTaskIterator {
    static MultiTask emptyTask1;
    static MultiTask emptyTask2;
    static MultiTask emptyTask3;
    static MultiTask task1;
    static MultiTask task2;
    static MultiTask task3;
    
    
    @BeforeAll
    static void initTasks() {
        emptyTask1 = new MultiTask("", "");
        emptyTask2 = new MultiTask("", "", List.of(emptyTask1, emptyTask1, emptyTask1));
        emptyTask3 = new MultiTask("", "",
                List.of(emptyTask2, emptyTask1, emptyTask2, new MultiTask(
                        "", "", List.of(emptyTask1, emptyTask2, emptyTask1))));
        
        Task simpleTask1 = new SimpleTask("1", "", 0);
        Task simpleTask2 = new SimpleTask("2", "", 0);
        Task simpleTask3 = new SimpleTask("3", "", 0);
        Task simpleTask4 = new SimpleTask("4", "", 0);
        Task simpleTask5 = new SimpleTask("5", "", 0);
        
        task1 = new MultiTask("", "", List.of(simpleTask1, simpleTask2, simpleTask3, simpleTask4, simpleTask5));
        task2 = new MultiTask("", "", List.of(task1, task1, emptyTask1, simpleTask1, emptyTask1));
        task3 = new MultiTask("", "", List.of(
                simpleTask1, simpleTask5, task1, task2, new MultiTask("", "", List.of(
                        simpleTask1, task2)),
                simpleTask3));
    }
    
    
    @Test
    void testEmptyTask1() {
        MultiTaskIterator iterator = emptyTask1.iterator();
        
        assertAll(
                () -> assertFalse(iterator.hasNext()),
                () -> assertThrows(NoSuchElementException.class, iterator::next)
        );
    }
    
    
    @Test
    void testEmptyTask2() {
        MultiTaskIterator iterator = emptyTask2.iterator();
        
        assertAll(
                () -> assertFalse(iterator.hasNext()),
                () -> assertThrows(NoSuchElementException.class, iterator::next)
        );
    }
    
    
    @Test
    void testEmptyTask3() {
        MultiTaskIterator iterator = emptyTask3.iterator();
        
        assertAll(
                () -> assertFalse(iterator.hasNext()),
                () -> assertThrows(NoSuchElementException.class, iterator::next)
        );
    }
    
    
    @Test
    void testTask1() {
        StringBuilder sb = new StringBuilder(5);
        for (Task t: task1)
            sb.append(t.getHeader());
        
        assertEquals("12345", sb.toString());
    }
    
    
    @Test
    void testTask2() {
        StringBuilder sb = new StringBuilder(12);
        for (Task t: task2)
            sb.append(t.getHeader());
        
        assertEquals("12345123451", sb.toString());
    }
    
    
    @Test
    void testTask3() {
        StringBuilder sb = new StringBuilder(12);
        for (Task t: task3)
            sb.append(t.getHeader());
        
        assertEquals("1512345123451234511123451234513", sb.toString());
    }
}
