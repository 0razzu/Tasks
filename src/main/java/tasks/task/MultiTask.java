package tasks.task;


import lombok.AllArgsConstructor;
import lombok.Data;
import tasks.iterator.MultiTaskIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Data
public class MultiTask implements Task, Iterable<SimpleTask> {
    private String header;
    private String body;
    private List<Task> tasks;
    
    
    public MultiTask(String header, String body) {
        this(header, body, new ArrayList<>());
    }
    
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    
    public void addTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
    
    
    public void addTasks(Task... tasks) {
        addTasks(Arrays.asList(tasks));
    }
    
    
    @Override
    public int getLeadTime() {
        int leadTime = 0;
        
        for (Task task: tasks)
            leadTime += task.getLeadTime();
        
        return leadTime;
    }
    
    
    @Override
    public MultiTaskIterator iterator() {
        return new MultiTaskIterator(this);
    }
}
