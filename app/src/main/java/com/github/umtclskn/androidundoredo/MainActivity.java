package com.github.umtclskn.androidundoredo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.umtclskn.androidundoredo.memento.UndoRedo;
import com.github.umtclskn.androidundoredo.model.Project;

public class MainActivity extends AppCompatActivity implements UndoRedo.OnUndoRedoEventListener, View.OnClickListener{

    ImageView imgUndo,imgRedo;
    TextView textViewUndo,textViewRedo;
    Button btnSaveProject;
    EditText editTextProjectLog, editTextProjectName;

    private Project currentProject;
    private UndoRedo undoRedo;
    String projectLog = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewRedo = findViewById(R.id.textViewRedo);
        textViewUndo = findViewById(R.id.textViewUndo);
        imgUndo = findViewById(R.id.imageViewUndo);
        imgRedo = findViewById(R.id.imageViewRedo);
        btnSaveProject = findViewById(R.id.buttonSaveProjectID);
        editTextProjectLog = findViewById(R.id.editTextProjectLogID);
        editTextProjectName = findViewById(R.id.textViewProjectNameID);

        imgUndo.setOnClickListener(this);
        imgRedo.setOnClickListener(this);
        btnSaveProject.setOnClickListener(this);

        undoRedo = new UndoRedo(this);
    }

    @Override
    public void onStateSaveFinished() {
        undoButtonConfig();
        redoButtonConfig();
    }

    @Override
    public void onUndoFinished(Project projectOriginator) {
        this.currentProject = projectOriginator;
        undoButtonConfig();
        redoButtonConfig();
    }

    @Override
    public void onRedoFinished(Project projectOriginator) {
        this.currentProject = projectOriginator;
        undoButtonConfig();
        redoButtonConfig();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.buttonSaveProjectID ) {
            String name = editTextProjectName.getText().toString();
            if (currentProject == null){
                currentProject = new Project(1, name);
                undoRedo.setProjectOriginator(currentProject);
            }else{
                currentProject.setName(name);
            }
            undoRedo.setState();
        }else if (viewId == R.id.imageViewUndo || viewId == R.id.textViewUndo){
            if (undoRedo.isUndoIsPossible()){
                undoRedo.undo();
            }
        }else if (viewId == R.id.imageViewRedo || viewId == R.id.textViewRedo){
            if (undoRedo.isRedoIsPossible()){
                undoRedo.redo();
            }
        }

        if (currentProject != null){
            editTextProjectName.setText(currentProject.getName());
            projectLog +=   currentProject.getName() + "\n ";
            editTextProjectLog.setText(projectLog);
        }else{
            editTextProjectName.setText("");
        }

    }

    private void undoButtonConfig(){
        if (undoRedo.isUndoIsPossible()){
            imgUndo.setImageResource(R.drawable.undo_enable);
            textViewUndo.setTextColor(Color.WHITE);
        }else {
            imgUndo.setImageResource(R.drawable.undo_disable);
            textViewUndo.setTextColor(Color.GRAY);
        }
    }

    private void redoButtonConfig(){
        if (undoRedo.isRedoIsPossible()){
            imgRedo.setImageResource(R.drawable.redo_enable);
            textViewRedo.setTextColor(Color.WHITE);
        }else {
            imgRedo.setImageResource(R.drawable.redo_disable);
            textViewRedo.setTextColor(Color.GRAY);
        }
    }

}
