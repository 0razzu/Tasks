package tasks;


public interface Task {
    void setHeader(String header);
    void setBody(String body);
    
    String getHeader();
    String getBody();
    int getLeadTime();
}
