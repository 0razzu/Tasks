package tasks.task;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class SimpleTask implements Task {
    private String header;
    private String body;
    private int leadTime;
}
