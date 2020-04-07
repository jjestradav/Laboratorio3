package com.example.laboratorio3.ui.cursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.entity.Curso;
import com.example.laboratorio3.ui.grupos.GruposActivity;

import java.util.ArrayList;
import java.util.List;

public class CursosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        Button cursosBtn=(Button) findViewById(R.id.cursosBtn);
       populateSpinner();
        cursosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
                onClickAction();
            }
        });

    }


    private void populateSpinner(){

        int codigoCiclo=getIntent().getIntExtra("ciclo",-1);
       // List<Curso> list=new ArrayList<>();
        List<Curso> list= Database.cursosPorProfesor(Database.currentUser,codigoCiclo);
        ArrayAdapter<Curso> spinnerArrayAdapter = new ArrayAdapter<Curso>(
                this, android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner cursosSpinner=  findViewById(R.id.spinnerCursos);
        cursosSpinner.setAdapter(spinnerArrayAdapter);
    }



    private void onClickAction(){
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCursos);
        Curso curso= (Curso)spinner.getSelectedItem();
        Intent GruposActivity= new Intent(getApplicationContext(), GruposActivity.class);
        GruposActivity.putExtra("curso", curso.getCodigo());
        startActivity(GruposActivity);
    }
}
