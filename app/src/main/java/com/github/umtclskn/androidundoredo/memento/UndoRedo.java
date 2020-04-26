package com.github.umtclskn.androidundoredo.memento;

import com.github.umtclskn.androidundoredo.model.Project;

public class UndoRedo {

    public interface OnUndoRedoEventListener{
        public void onUndoFinished(Project projectOriginator);
        public void onRedoFinished(Project projectOriginator);
        public void onStateSaveFinished();
    }

    private ProjectCareTaker projectCareTaker;
    private Project projectOriginator;
    private OnUndoRedoEventListener onUndoRedoEventListener;

    public UndoRedo() {

    }

    public UndoRedo(OnUndoRedoEventListener onUndoRedoEventListener) {
        this.onUndoRedoEventListener = onUndoRedoEventListener;
        this.projectCareTaker = new ProjectCareTaker();
    }

    public void setState() {
        ProjectMemento memento = projectOriginator.createMemento();
        projectCareTaker.addMementoToStack(memento);
        if(onUndoRedoEventListener != null)
        {
            onUndoRedoEventListener.onStateSaveFinished();
        }
    }


    public void undo() {
        ProjectMemento memento = null;
        memento = projectCareTaker.getUndoMemento();
        if (memento == null){
            this.projectOriginator = new Project();
        }else {
            this.projectOriginator.restoreMemento(memento);
        }
        onUndoRedoEventListener.onUndoFinished(projectOriginator);
    }

    public void redo() {
        ProjectMemento memento = null;
        memento = projectCareTaker.getRedoMemento();
        if (memento == null){
            this.projectOriginator = new Project();
        }else{
            this.projectOriginator.restoreMemento(memento);
        }
        onUndoRedoEventListener.onRedoFinished(projectOriginator);
    }

    public boolean isUndoIsPossible(){
        return projectCareTaker.isUndoPossible();
    }

    public boolean isRedoIsPossible(){
        return  projectCareTaker.isRedoPossible();
    }

    public Project getProjectOriginator() {
        return projectOriginator;
    }

    public void setProjectOriginator(Project projectOriginator) {
        this.projectOriginator = projectOriginator;
    }
}
