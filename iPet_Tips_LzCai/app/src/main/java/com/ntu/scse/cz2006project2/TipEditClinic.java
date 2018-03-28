package com.ntu.scse.cz2006project2;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TipEditClinic extends AppCompatActivity {

//    Button button = findViewById(R.id.tipSubmitButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_edit_clinic);

    }


    public void buttonGetTip(View view) {
        EditText title = findViewById(R.id.editTipTitle);
        EditText content = findViewById(R.id.editTipContent);
        String titleText = title.getText().toString();
        String contentText = content.getText().toString();
        Snackbar.make(view, "Your title is \""+ titleText
                        + "\", and your content is \"" + contentText +"\"",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
