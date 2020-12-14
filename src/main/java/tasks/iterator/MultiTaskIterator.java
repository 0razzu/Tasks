package tasks.iterator;


import tasks.task.MultiTask;
import tasks.task.SimpleTask;
import tasks.task.Task;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class MultiTaskIterator implements Iterator<SimpleTask> {
    private int current;
    private final List<Task> tasks;
    private MultiTaskIterator currentIterator;
    
    
    public MultiTaskIterator(MultiTask task) {
        tasks = task.getTasks();
    }
    
    
    @Override
    public boolean hasNext() {
        while (current < tasks.size()) {
            Task currentTask = tasks.get(current);
            
            if (currentTask instanceof SimpleTask)
                return true;
            
            else if (currentTask instanceof MultiTask) {
                if (currentIterator == null)
                    currentIterator = new MultiTaskIterator((MultiTask) currentTask);
                
                if (currentIterator.hasNext())
                    return true;
                
                else {
                    currentIterator = null;
                    current++;
                }
            }
        }
        
        return false;
    }
    
    
    @Override
    public SimpleTask next() {
        if (hasNext())
            return currentIterator == null?
                    (SimpleTask) tasks.get(current++) :
                    currentIterator.next();
        
        throw new NoSuchElementException();
    }
}
