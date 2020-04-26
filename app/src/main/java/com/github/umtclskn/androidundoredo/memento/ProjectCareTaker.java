package com.github.umtclskn.androidundoredo.memento;

import java.util.Stack;

public class ProjectCareTaker {
    private Stack<ProjectMemento> UndoStack = new Stack<>();
    private Stack<ProjectMemento> RedoStack = new Stack<>();

    public ProjectMemento getUndoMemento(){
        if (UndoStack.size() >= 1){
            RedoStack.push(UndoStack.pop());
            if(UndoStack.size() > 0){
                return UndoStack.peek();
            }
        }
        return null;
    }

    public ProjectMemento getRedoMemento(){
        if (RedoStack.size() != 0){
            ProjectMemento m = RedoStack.pop();
            UndoStack.push(m);
            return m;
        }
        return null;
    }

    public void addMementoToStack(ProjectMemento projectMemento){
        if (projectMemento != null){
            UndoStack.push(projectMemento);
            RedoStack.clear();
        }
    }

    public boolean isUndoPossible(){
        if (UndoStack.size() >= 1){
            return true;
        }
        return false;
    }


    public boolean isRedoPossible(){
        if (RedoStack.size() >= 1){
            return  true;
        }
        return false;
    }
}
