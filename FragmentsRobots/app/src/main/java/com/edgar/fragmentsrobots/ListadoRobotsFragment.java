package com.edgar.fragmentsrobots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class ListadoRobotsFragment extends Fragment implements View.OnClickListener{

    private final static String ARG_NOMBRE = "ARG_NOMBRE";
    private OnMiPrimerFragmentListener mListener;

    private RecyclerView recyclerView;
    public AdaptadorRobots adaptador;
    private RecyclerView.LayoutManager layoutManager;
    static ArrayList<Robot> robots = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Bundle informacionRecibida = getArguments();

        if (informacionRecibida != null){

        }



    }

    public static ListadoRobotsFragment newInstance(){
        Bundle informacion = new Bundle();


        ListadoRobotsFragment mFragment = new ListadoRobotsFragment();
        mFragment.setArguments(informacion);
        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View vistaLayout = inflater.inflate(R.layout.primer_fragment,container,false);

        getActivity().setTitle("Listado robots");

        //RECYCLERVIEW

        robots = new ArrayList<>();

        robots.add(new Robot("Pepe1","Hierro","2020","BENDER"));
        robots.add(new Robot("Pepe2","Acero","2020","R2D2"));
        robots.add(new Robot("Pepe3","Oro","2020","WALLE"));

        adaptador = new AdaptadorRobots(robots,getActivity());
        adaptador.setOnClickListener(this);
        recyclerView = vistaLayout.findViewById(R.id.rv_robots);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));


        recyclerView = (RecyclerView) vistaLayout.findViewById(R.id.rv_robots);
        adaptador = new AdaptadorRobots(robots, vistaLayout.getContext());
        adaptador.setOnClickListener(this);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(vistaLayout.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(vistaLayout.getContext(),DividerItemDecoration.VERTICAL));


        final ItemTouchHelper.SimpleCallback myCallback  = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                switch (direction){
                    case ItemTouchHelper.RIGHT:
                        //Eliminar
                        robots.remove(viewHolder.getAdapterPosition());
                        adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                        break;
                }

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                int margen = (int)getResources().getDimension(R.dimen.margen);
                Drawable icono;
                Paint pincel = new Paint();
                pincel.setColor(Color.WHITE);
                int sizetext = getResources().getDimensionPixelSize(R.dimen.textsize);
                pincel.setTextSize(sizetext);


                //Hacia la dcha
                if(dX > 0){
                    c.clipRect(viewHolder.itemView.getLeft(),viewHolder.itemView.getTop(),dX,viewHolder.itemView.getBottom());

                    if(dX < recyclerView.getWidth() / 3)
                        c.drawColor(Color.GRAY);
                    else
                        c.drawColor(Color.RED);

                    icono = vistaLayout.getContext().getDrawable(R.drawable.ic_delete);
                    icono.setBounds(new Rect(viewHolder.itemView.getLeft()+margen, viewHolder.itemView.getTop()+margen, viewHolder.itemView.getHeight()-margen, viewHolder.itemView.getBottom()-margen));
                    icono.draw(c);
                    pincel.setTextAlign(Paint.Align.LEFT);
                    c.drawText(getResources().getString(R.string.eliminar),viewHolder.itemView.getHeight(),viewHolder.itemView.getBottom()-viewHolder.itemView.getHeight()/2+sizetext/2,pincel);

                    //Hacia la izqda
                }else if(dX < 0){

                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return vistaLayout;
    }

    @Override
    public void onClick(View view) {

        mListener.onMiPrimerFragmentClick();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnMiPrimerFragmentListener){
            mListener = (OnMiPrimerFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()+"debe implementar OnMiPrimerFragmentListener");
        }
    }

    public interface OnMiPrimerFragmentListener{
        public void onMiPrimerFragmentClick();
    }


}


