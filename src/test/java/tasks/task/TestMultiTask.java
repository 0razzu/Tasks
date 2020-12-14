package tasks.task;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestMultiTask {
    static MultiTask task;
    
    
    @BeforeAll
    static void initTask() {
        task = new MultiTask("Using Composite in your code", "To implement this pattern you should perform following steps");
        MultiTask subtask1 = new MultiTask("Make sure", "that your model can be organized in a tree");
        Task subtask11 = new SimpleTask("Break it down", "into simple elements and containers", 3);
        Task subtask12 = new SimpleTask("Check", "that containers can store other containers as well", 1);
        Task subtask2 = new SimpleTask("Declare", "an interface for both simple elements and containers", 1);
        Task subtask3 = new SimpleTask("Create", "a leaf class for simple elements", 1);
        MultiTask subtask4 = new MultiTask("Create", "a container class for complex elements");
        Task subtask41 = new SimpleTask("Declare", "a collection to store other elements", 1);
        Task subtask42 = new SimpleTask("Implement", "interface method so that it can delegate some of its work to leaves", 2);
        Task subtask43 = new SimpleTask("Add", "methods for putting objects into the collection and for removing them", 1);
        
        subtask1.addTasks(subtask11, subtask12);
        subtask4.addTasks(subtask41, subtask42, subtask43);
        task.addTasks(subtask1, subtask2, subtask3, subtask4);
    }
    
    
    @Test
    void testGetLeadTime() {
        assertEquals(10, task.getLeadTime());
        ((SimpleTask) task.getTasks().get(1)).setLeadTime(3);
        assertEquals(12, task.getLeadTime());
    }
}
