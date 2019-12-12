package com.example.pmm_proyecto_alexbataller;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerViewContactos extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_NUEVO = 1;
    public static final int REQUEST_CODE_EDIT = 2;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;
    private AdaptadorContactos adaptador;
    private ArrayList<Contacto> contactos;

    private TextView tvNoItems;
    private TextView tvUsername;

    private Button btnContacto;

    private Intent intentModContacto;
    private FloatingActionButton btnAcercaDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_contactos);

        tvUsername = findViewById(R.id.tvUsername);
        tvNoItems = findViewById(R.id.tvNoItems);

        btnAcercaDe = findViewById(R.id.btnAcercaDe);
        btnAcercaDe.setOnClickListener(this);
        btnContacto = findViewById(R.id.btnContacto);
        btnContacto.setOnClickListener(this);

        contactos = new ArrayList<>();

        intentModContacto = new Intent(this, NuevoContacto.class);

        try {
            tvUsername.setText(this.getIntent().getExtras().getString("username"));
        } catch (NullPointerException e) {
            tvUsername.setText("");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rvRobots);
        adaptador = new AdaptadorContactos(contactos, this);
        recyclerView.setAdapter(adaptador);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            inicializarContactos();
        } else {
            Utilidades.solicitarPermiso(Manifest.permission.READ_CONTACTS, getString(R.string.contactos_info), 1, this, this);
        }

        if (recyclerView.getAdapter().getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNoItems.setVisibility(View.GONE);
        }

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("requestCode", REQUEST_CODE_EDIT);
                    bundle.putSerializable("contactoEdit", contactos.get(viewHolder.getAdapterPosition()));
                    bundle.putInt("contactoPos", viewHolder.getAdapterPosition());
                    intentModContacto.putExtras(bundle);
                    startActivityForResult(intentModContacto, REQUEST_CODE_EDIT);
                } else if (direction == ItemTouchHelper.RIGHT) {
                    adaptador.removeContacto(viewHolder.getAdapterPosition());
                    adaptador.notifyItemRemoved(viewHolder.getAdapterPosition());
                    if (recyclerView.getAdapter().getItemCount() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        tvNoItems.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                View itemView = viewHolder.itemView;
                int margin = (int) getResources().getDimension(R.dimen.margin);
                int scaleColor = (int) scale(Math.abs(dX), 0f, itemView.getWidth(), 0f, 255f);
                float scale = scale(Math.abs(dX), 0f, itemView.getWidth(), 0f, 1f);

                Paint pincel = new Paint();
                pincel.setColor(Color.WHITE);
                pincel.setStyle(Paint.Style.FILL);
                pincel.setTextAlign(Paint.Align.CENTER);
                c.drawPaint(pincel);
                pincel.setTextSize(getResources().getDimension(R.dimen.texto_swipe));
                if (dX < 0) {
                    //Editar
                    c.clipRect(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());

                    int color = (255) << 24 | (scaleColor & 0xff) << 16 | (scaleColor & 0xff) << 8 | (0);
                    c.drawColor(color);

                    Drawable editVec = getDrawable(R.drawable.edit);

                    editVec.setBounds(new Rect(itemView.getRight() - itemView.getHeight() + (int) (margin * (1f - scale)),
                            itemView.getTop() + (int) (margin * (1f - scale)),
                            itemView.getRight() - (int) (margin * (1f - scale)),
                            itemView.getBottom() - (int) (margin * (1f - scale))));
                    editVec.draw(c);

                    Rect bounds = new Rect();
                    String editarS = getString(R.string.editar);
                    pincel.getTextBounds(editarS, 0, editarS.length(), bounds);
                    c.drawText(editarS, itemView.getRight() - itemView.getHeight() - (int) (margin * (1f - scale)) - bounds.width(), itemView.getBottom() - itemView.getHeight() / 2 + bounds.height() / 2, pincel);
                } else {
                    //Borrar
                    c.clipRect(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + dX, itemView.getBottom());

                    int color = (255) << 24 | (scaleColor & 0xff) << 16 | (50 & 0xff) << 8 | (50 & 0xff);
                    c.drawColor(color);

                    Drawable deleteVec = getDrawable(R.drawable.delete);

                    deleteVec.setBounds(new Rect(itemView.getLeft() + (int) (margin * (1f - scale)),
                            itemView.getTop() + (int) (margin * (1f - scale)),
                            itemView.getHeight() - (int) (margin * (1f - scale)),
                            itemView.getBottom() - (int) (margin * (1f - scale))));
                    deleteVec.draw(c);

                    Rect bounds = new Rect();
                    String deleteS = getString(R.string.eliminar);
                    pincel.getTextBounds(deleteS, 0, deleteS.length(), bounds);
                    c.drawText(deleteS, itemView.getHeight() + (int) (margin * (1f - scale)) + bounds.width(), itemView.getBottom() - itemView.getHeight() / 2 + bounds.height() / 2, pincel);
                }
            }
        };

        ItemTouchHelper ith = new ItemTouchHelper(callback);
        ith.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnContacto) {
            Intent intent = new Intent(this, NuevoContacto.class);
            intent.putExtra("requestCode", REQUEST_CODE_NUEVO);
            startActivityForResult(intent, REQUEST_CODE_NUEVO);
        }else if(view.getId() == R.id.btnAcercaDe){
            Intent intent = new Intent(this, AcercaDe.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NUEVO && resultCode == RESULT_OK) {
            try {
                Contacto c = (Contacto) data.getSerializableExtra("contactoInser");
                if (null != c) {
                    adaptador.addContacto(c);
                    recyclerView.scrollToPosition(0);
                    adaptador.notifyItemInserted(0);
                    if (recyclerView.getAdapter().getItemCount() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvNoItems.setVisibility(View.GONE);
                    }
                    Snackbar.make(findViewById(R.id.rvRobots), (getString(R.string.nuevo_contacto_snack) + " " + c.getNombre()), Snackbar.LENGTH_SHORT)
                            .setAction(getString(R.string.deshacer_snack), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    adaptador.removeContacto(0);
                                    adaptador.notifyItemRemoved(0);
                                    if (recyclerView.getAdapter().getItemCount() == 0) {
                                        recyclerView.setVisibility(View.GONE);
                                        tvNoItems.setVisibility(View.VISIBLE);
                                    }
                                }
                            }).show();
                }
            } catch (NullPointerException e) {
                Log.d("NULL", e.toString());
            }
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            try {
                Contacto c = (Contacto) data.getSerializableExtra("contactoEdit");
                int contactoPos = data.getIntExtra("contactoPos", 0);
                if (null != c) {
                    contactos.set(contactoPos, c);
                    adaptador.notifyItemChanged(contactoPos);
                }
            } catch (NullPointerException e) {
                Log.d("NULL", e.toString());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                inicializarContactos();
            } else {
                finish();
            }
        }
    }

    private void inicializarContactos() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Contacto c = new Contacto();
                    c.setId(id);
                    c.setNombre(name);
                    c.setCt(ContactType.HOMBRE);

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        c.setTelefono(phoneNumber);
                    }

                    phoneCursor.close();

                    adaptador.addContacto(c);
                    adaptador.notifyItemInserted(0);
                }
            }
        }
    }

    private float scale(float val, float min, float max, float minAllowed, float maxAllowed) {
        return (maxAllowed - minAllowed) * (val - min) / (max - min) + minAllowed;
    }
}
