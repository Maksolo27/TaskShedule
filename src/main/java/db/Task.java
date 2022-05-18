package db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Task {
    private String name;
    private String description;
    private Date deadline;
    private boolean isActive;
    private Date createdOn;
    private Date completedOn;
    private String password;

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", isActive=" + isActive +
                ", createdOn=" + createdOn +
                '}';
    }
}