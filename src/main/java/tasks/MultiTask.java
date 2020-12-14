package tasks;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class MultiTask implements Task {
    private String header;
    private String body;
    private List<Task> tasks;
    
    
    public MultiTask(String header, String body) {
        this(header, body, new ArrayList<>());
    }
    
    
    public void addTask(SimpleTask task) {
        tasks.add(task);
    }
    
    
    @Override
    public int getLeadTime() {
        int leadTime = 0;
        
        for (Task task: tasks)
            leadTime += task.getLeadTime();
        
        return leadTime;
    }
}
