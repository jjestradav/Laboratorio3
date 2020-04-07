package com.example.laboratorio3.ui.grupos;

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
import com.example.laboratorio3.entity.Grupo;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.ui.cursos.CursosActivity;
import com.example.laboratorio3.ui.grupoAlumnos.GrupoAlumnoActivity;

import java.util.List;

public class GruposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        Button btnGrupos=(Button)findViewById(R.id.gruposBtn);
        populateSpinner();
        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
                onClickAction();
            }
        });
    }



    private void populateSpinner(){

        int codigoCurso=getIntent().getIntExtra("curso",-1);
        List<Grupo> list= Database.gruposPorProfesor(Database.currentUser,codigoCurso);
        ArrayAdapter<Grupo> spinnerArrayAdapter = new ArrayAdapter<Grupo>(
                this, android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner cursosSpinner=  findViewById(R.id.spinnerGrupos);
        cursosSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void onClickAction(){
        Intent GrupoAlumno= new Intent(getApplicationContext(), GrupoAlumnoActivity.class);
        startActivity(GrupoAlumno);
    }

}
