package service;

import model.command.Command;
import model.command.CommandHistory;

public class CommandService {
    private final CommandHistory history;

    public CommandService(){
        history = new CommandHistory();
    }
    public void execute(Command c){
        c.execute();
        history.push(c);
    }
    public void undo(){
        history.undo();
    }
    public void redo (){
        history.redo();
    }

    public boolean canUndo(){
        return history.canUndo();
    }
    public boolean canRedo(){
        return history.canUndo();
    }

}
