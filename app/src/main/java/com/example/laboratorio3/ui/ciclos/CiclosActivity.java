package com.example.laboratorio3.ui.ciclos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.laboratorio3.NavDrawerActivity;
import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.entity.Ciclo;
import com.example.laboratorio3.ui.cursos.CursosActivity;

import java.util.List;

public class CiclosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclos);

        Button ciclosButton = (Button)findViewById(R.id.ciclosBtn);
        Log.v("HOLA",""+ciclosButton);
        this.populateSpinner();

        ciclosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
               onClickAction();
            }
        });


    }


    private void populateSpinner(){

        List<Ciclo> list= Database.getCurrentCiclo();
        ArrayAdapter<Ciclo> spinnerArrayAdapter = new ArrayAdapter<Ciclo>(
                this, android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner ciclosSpinner=  findViewById(R.id.spinnerCiclos);
        ciclosSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void onClickAction(){
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCiclos);
         Ciclo ciclo= (Ciclo)spinner.getSelectedItem();
        Intent cursosActivity= new Intent(getApplicationContext(), CursosActivity.class);
        cursosActivity.putExtra("ciclo", ciclo.getCodigo());
        startActivity(cursosActivity);
    }
}
