package com.github.umtclskn.androidundoredo.memento;

public class ProjectMemento {
    private int id;
    private String name;

    public ProjectMemento() {
    }

    public ProjectMemento(int id, String name) {
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
}
