package com.example.laboratorio3.ui.editNota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.ui.grupoAlumnos.GrupoAlumnoActivity;

public class EditNotaActivity extends AppCompatActivity {

    private ImageButton btnEdit;
    private TextView cedulaText;
    private TextView nombreText;
    private EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_edit_nota);
      fillText();
      btnEdit=(ImageButton)findViewById(R.id.saveBtn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNota();
            }
        });
    }

    private void fillText(){
        cedulaText=findViewById(R.id.cedulaText);
        nombreText=findViewById(R.id.nombreText);
        nota=findViewById(R.id.notaText);
        GrupoAlumno grupoAlumno=(GrupoAlumno) getIntent().getSerializableExtra("grupoAlumno");
        cedulaText.setText(grupoAlumno.getAlumno().getCedula());
        nombreText.setText(grupoAlumno.getAlumno().getNombre());
        nota.setText(""+grupoAlumno.getNota());
    }

    private void setNota(){
        nota=findViewById(R.id.notaText);
        GrupoAlumno grupoAlumno=(GrupoAlumno) getIntent().getSerializableExtra("grupoAlumno");
        grupoAlumno.setNota(Float.parseFloat(nota.getText().toString()));
        //Log.v("EDIT NOTA: ",nota.getText().toString());
        for(GrupoAlumno gruA:Database.getGrupoAlumnos){
            if(gruA.getAlumno().getCedula().equals(grupoAlumno.getAlumno().getCedula())){
                gruA.setNota(grupoAlumno.getNota());
            }
        }
        Intent grupoAlumnoAcitivity= new Intent(getApplicationContext(), GrupoAlumnoActivity.class);
        startActivity(grupoAlumnoAcitivity);

    }
}
