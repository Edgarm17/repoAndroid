package com.simarro.practicaclasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AdaptadorAlumnos adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Alumno> alumnos;
    private EditText etDni;
    private EditText etNombre;
    private RadioButton rbSexoHombre;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Button btnInsertar;

    public void cargarDatos(){

        alumnos.clear();
        for (int i =1;i<10;i++){
            alumnos.add(new Alumno("1234567"+i,"Nombre"+i,(i%2==0)?'H':'M'));

        }
        swipeRefreshLayout.setRefreshing(false);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        alumnos=new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.rv_alumnos);
        adaptador = new AdaptadorAlumnos(alumnos, this);
        recyclerView.setAdapter(adaptador);
        adaptador.setOnClickListener(this);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        etDni=findViewById(R.id.et_dni);
        etNombre=findViewById(R.id.et_nombre);
        rbSexoHombre =findViewById(R.id.rb_hombre);
        btnInsertar = findViewById(R.id.btn_insertar);
        btnInsertar.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        cargarDatos();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
                adaptador.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.colorAccent);
        //swipeRefreshLayout.setEnabled(false);
        ItemTouchHelper.SimpleCallback myCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               switch (direction){
                   //Eliminar
                   case ItemTouchHelper.RIGHT:
                       alumnos.remove(viewHolder.getAdapterPosition());
                       adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                       break;
                   case ItemTouchHelper.LEFT:
                       //Editar
                       adaptador.notifyDataSetChanged();
                       break;
               }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Paint pincel = new Paint();
                pincel.setColor(Color.WHITE);
                int sizetext = (int)getResources().getDimension(R.dimen.textsize);
                pincel.setTextSize(sizetext);

                //Derecha

                if (dX>0){
                    c.clipRect(viewHolder.itemView.getLeft(),viewHolder.itemView.getTop(),dX,viewHolder.itemView.getBottom());
                    if (dX < recyclerView.getWidth() / 3){
                        c.drawColor(Color.GRAY);
                    }else{
                        c.drawColor(Color.RED);
                    }
                    int margen = (int)getResources().getDimension(R.dimen.margen);
                    Drawable delete = getDrawable(R.drawable.ic_delete);
                    delete.setBounds(viewHolder.itemView.getLeft()+margen,viewHolder.itemView.getTop()+margen, viewHolder.itemView.getHeight()-margen,viewHolder.itemView.getBottom()-margen);
                    delete.draw(c);
                    pincel.setTextAlign(Paint.Align.LEFT);
                    c.drawText(getResources().getString(R.string.Eliminar),
                            viewHolder.itemView.getHeight(),viewHolder.itemView.getBottom()-viewHolder.itemView.getHeight()/2+sizetext/2,pincel);
                }else if (dX < 0){
                    c.clipRect(viewHolder.itemView.getRight(),viewHolder.itemView.getTop(),dX,viewHolder.itemView.getBottom());
                    if (Math.abs(dX) < recyclerView.getWidth() / 3){
                        c.drawColor(Color.GRAY);
                    }else{
                        c.drawColor(Color.BLUE);
                    }
                    int margen = (int)getResources().getDimension(R.dimen.margen);
                    Drawable delete = getDrawable(R.drawable.ic_edit);
                    delete.setBounds(viewHolder.itemView.getRight()-viewHolder.itemView.getHeight()+margen,viewHolder.itemView.getTop()+margen, viewHolder.itemView.getRight()-margen,viewHolder.itemView.getBottom()-margen);
                    delete.draw(c);
                    pincel.setTextAlign(Paint.Align.RIGHT);
                    c.drawText(getResources().getString(R.string.Editar),
                            viewHolder.itemView.getRight()-viewHolder.itemView.getHeight(),viewHolder.itemView.getBottom()-viewHolder.itemView.getHeight()/2+sizetext/2,pincel);
                }



                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };



        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.btn_insertar){
            char sexo = (rbSexoHombre.isChecked()) ? 'H': 'M';
            Alumno nuevoAlumno = new Alumno(etDni.getText().toString(),etNombre.getText().toString(),sexo);
            alumnos.add(nuevoAlumno);

            adaptador.notifyDataSetChanged();

        }else{
            int posicion = recyclerView.getChildAdapterPosition(view);
            Alumno aSeleccionado = alumnos.get(posicion);
            Toast.makeText(MainActivity.this,"Alumno"+aSeleccionado.getNombre(),Toast.LENGTH_LONG).show();
        }






    }
}
