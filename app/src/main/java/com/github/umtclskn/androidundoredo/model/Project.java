package com.github.umtclskn.androidundoredo.model;

import com.github.umtclskn.androidundoredo.memento.ProjectMemento;

public class Project {
    private int id;
    private String name;

    public Project() {
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectMemento createMemento()
    {
        ProjectMemento projectMemento = new ProjectMemento(id, name);
        return projectMemento;
    }

    public void restoreMemento(ProjectMemento projectMemento) {
        if(projectMemento != null){
            this.id = projectMemento.getId();
            this.name = projectMemento.getName();
        }
    }
}
