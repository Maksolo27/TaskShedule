package ui;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Commands {

    ADD_TASK("/add"),
    REMOVE_TASK("/rm"),
    PRINT_ALL("/print"),
    UPDATE_TASK("/update"),
    MARK_AS_COMPLETED("/mark"),
    PRINT_COMPLETED("/print_compl"),
    HELP("/help"),
    END_APP("/exit")
    ;

    private String command;

    public static Commands getCommand(String command){
        for (int i = 0; i < Commands.values().length; i++) {
            if(Commands.values()[i].command.equals(command)){
                return Commands.values()[i];
            }
        }
        return null;
    }

}
