package com.example.filemanager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView testo;
    Button leggere, scrivere, leggiRaw, leggiAssets;
    EditText editTxt,testoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxt = findViewById(R.id.editTxt);
        testoFile = findViewById(R.id.textArea);
        testo = findViewById(R.id.testo);
        leggere = findViewById(R.id.btnLeggi);
        scrivere = findViewById(R.id.btnScrivi);
        leggiRaw = findViewById(R.id.rawRes);
        leggiAssets = findViewById(R.id.assetsFolder);
        GestioneFile gf = new GestioneFile();
        Context context = getApplicationContext();
        String nomeFile=editTxt.getText().toString();

        leggere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nomeFile=editTxt.getText().toString();
                testo.setText(gf.leggiFile(nomeFile, context));
            }
        });

        scrivere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contenuto = testoFile.getText().toString();
                String nomeFile=editTxt.getText().toString();
                String str = gf.scriviFile(nomeFile,contenuto,context);
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });

        leggiRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testo.setText(gf.leggiFileRes(getApplicationContext()));
            }
        });

        leggiAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testo.setText(gf.leggiFileAssets(getApplicationContext()));
            }
        });

    }
}


