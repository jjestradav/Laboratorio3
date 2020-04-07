package com.example.laboratorio3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratorio3.R;
import com.example.laboratorio3.entity.GrupoAlumno;

import java.util.List;

public class GrupoAlumnoAdapter extends RecyclerView.Adapter<GrupoAlumnoAdapter.GrupoAlumnoViewHolder> {
    private List<GrupoAlumno> items;
    private GrupoAlumnoAdapterAdapterListener listerner;
    final private GrupoAlumnoClick onClick;
    public GrupoAlumnoAdapter(List<GrupoAlumno> list,GrupoAlumnoAdapterAdapterListener listener,GrupoAlumnoClick listen){
        this.items=list;
        this.listerner=listener;
        onClick=listen;
    }

    public interface GrupoAlumnoClick{
        void onGrupoAlumnoClick(GrupoAlumno clicked);
    }
    @NonNull
    @Override
    public GrupoAlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_grupo_alumno_row, parent, false);
        return new GrupoAlumnoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GrupoAlumnoViewHolder holder, int position) {
        GrupoAlumno grupoAlumno = items.get(position);
        holder.bind(grupoAlumno);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public GrupoAlumno getSwipedItem(int index) {
        return items.get(index);
    }

    public class GrupoAlumnoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView mTextViewGrupoAlumno;

        public GrupoAlumnoViewHolder(View itemView){
            super(itemView);
            mTextViewGrupoAlumno=itemView.findViewById(R.id.grupoAlmnoText);
            itemView.setOnClickListener(this);

        }

        public void bind(GrupoAlumno grupoAlumno){
            mTextViewGrupoAlumno.setText(grupoAlumno.mostrarEstudiante());
        }

        @Override
        public void onClick(View v) {
           int item=getAdapterPosition();
           GrupoAlumno selected=getSwipedItem(item);

            onClick.onGrupoAlumnoClick(selected);
        }
    }


    public interface GrupoAlumnoAdapterAdapterListener {
        void onContactSelected(GrupoAlumno grupoAlumno);
    }
}
