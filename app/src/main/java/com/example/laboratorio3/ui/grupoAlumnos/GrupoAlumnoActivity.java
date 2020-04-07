package com.example.laboratorio3.ui.grupoAlumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.laboratorio3.R;
import com.example.laboratorio3.adapter.GrupoAlumnoAdapter;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.helper.RecyclerItemTouchHelper;
import com.example.laboratorio3.ui.editNota.EditNotaActivity;

public class GrupoAlumnoActivity extends AppCompatActivity implements  RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,  GrupoAlumnoAdapter.GrupoAlumnoAdapterAdapterListener,
        GrupoAlumnoAdapter.GrupoAlumnoClick{

    private RecyclerView mRecyclerView;
    GrupoAlumnoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_alumno);
        mRecyclerView=findViewById(R.id.recyclerGrupoAlumno);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
         mAdapter=new GrupoAlumnoAdapter(Database.getGrupoAlumnos,this,this);
        mRecyclerView.setAdapter(mAdapter);




    }

    @Override
    public void onContactSelected(GrupoAlumno grupoAlumno) {
        Toast.makeText(getApplicationContext(), "Selected: " +grupoAlumno.getAlumno().getCedula(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        GrupoAlumno aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
        //send data to Edit Activity
        Intent intent = new Intent(this, EditNotaActivity.class);
        intent.putExtra("grupoAlumno", aux);
        mAdapter.notifyDataSetChanged(); //restart left swipe view
        startActivity(intent);
    }

    @Override
    public void onItemMove(int source, int target) {

    }

    @Override
    public void onGrupoAlumnoClick(GrupoAlumno clicked) {
        Intent intent = new Intent(this, EditNotaActivity.class);
        intent.putExtra("grupoAlumno", clicked);
        mAdapter.notifyDataSetChanged(); //restart left swipe view
        startActivity(intent);
    }
}
