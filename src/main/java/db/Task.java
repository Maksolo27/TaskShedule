package db;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private int id;
    private String name;
    private String description;
    private Date deadline;
    private boolean isActive;
    private Date createdOn;
    private Date completedOn;
    private String password;
}