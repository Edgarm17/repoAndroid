package com.example.pmm_proyecto_alexbataller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class NuevoContacto extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombre;
    private EditText etId;
    private EditText etPhone;
    private Spinner spinnerType;
    private Button btnCrear;
    private Intent intent;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        intent =  new Intent(this , RecyclerViewContactos.class);

        etNombre = findViewById(R.id.etNombre);
        etId = findViewById(R.id.etId);
        etPhone = findViewById(R.id.etPhone);
        spinnerType = findViewById(R.id.spinnerType);
        rbMale = findViewById(R.id.rbHombre);
        rbFemale = findViewById(R.id.rbMujer);

        rbMale.setOnClickListener(this);
        rbFemale.setOnClickListener(this);

        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundleRequest = getIntent().getExtras();

        if(bundleRequest.getInt("requestCode") == RecyclerViewContactos.REQUEST_CODE_EDIT){
            try {
                Contacto c = (Contacto) bundleRequest.getSerializable("contactoEdit");
                etNombre.setText(c.getNombre());
                etId.setText(c.getId());
                etPhone.setText(String.valueOf(c.getTelefono()));
                spinnerType.setSelection(c.getCt().ordinal(), true);
                btnCrear.setText(R.string.editar_contacto);
            }catch (NullPointerException e){
                Log.d("NULL", e.toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCrear) {
            Bundle bundleRequest = getIntent().getExtras();
            ContactType rt;
            switch (spinnerType.getSelectedItem().toString()) {
                case "MUJER":
                    rt = ContactType.MUJER;
                    break;
                default:
                    rt = ContactType.HOMBRE;
                    break;
            }
            Contacto c = new Contacto(etNombre.getText().toString(), etPhone.getText().toString(), etId.getText().toString());
            c.setCt(rt);

            if (bundleRequest.getInt("requestCode") == RecyclerViewContactos.REQUEST_CODE_NUEVO) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contactoInser", c);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            } else if (bundleRequest.getInt("requestCode") == RecyclerViewContactos.REQUEST_CODE_EDIT) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contactoEdit", c);
                int contactoPos = bundleRequest.getInt("contactoPos");
                bundle.putInt("contactoPos", contactoPos);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }else if(v.getId() == R.id.rbHombre){
            spinnerType.setSelection(0);

        }else if(v.getId() == R.id.rbMujer){
            spinnerType.setSelection(1);
        }
    }
}
