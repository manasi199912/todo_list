package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.dashboard.Task_subTask.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import kotlinx.android.synthetic.*

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase data;
    DBhandler dbHandler;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBhandler(this);
        final Todo todo = new Todo();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton4);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);//= new LayoutInflater(this);
        View view = inflater.inflate(R.layout.dialog_board, null);
        final EditText eText = view.findViewById(R.id.ed_text);
        //AlertDialog builder = dialog.create();
        fab.setOnClickListener(new AlertDialog.Builder[]
                {
                        dialog.setPositiveButton("ADD", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (TextUtils.isEmpty(eText.getText().toString()))
                                {
                                    todo.getName = eText.getText().toString();
                                    dbHandler.addList(todo);
                                }
                            }
                        }),
                         dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener(){
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {

                             }
                         }),

                        dialog.show()
                })
    }
}
