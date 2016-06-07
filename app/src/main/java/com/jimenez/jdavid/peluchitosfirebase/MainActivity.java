package com.jimenez.jdavid.peluchitosfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eId, eNombre, eCantidad, eValor;
    Button bAgregar, bBuscar, bEliminar, bActualizar, bInventario, bVender, bGanancias;
    TextView tId, tNombre, tCantidad, tValor, tInventario, tGanancias;

    Integer cont=0;
    String aux, nom, val, can, codigo;
    Firebase firebd, firebdv;

    private static final String FIREBASEV_URL="https://peluchitosfirebaseve.firebaseio.com/";
    private static final String FIREBASE_URL="https://peluchitosfirebase.firebaseio.com/";
    private Firebase firebasedatos;
    private Firebase firebasedatosv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eId=(EditText)findViewById(R.id.eId);
        eNombre=(EditText)findViewById(R.id.eNombre);
        eCantidad=(EditText)findViewById(R.id.eCantidad);
        eValor=(EditText)findViewById(R.id.eValor);

        bAgregar =(Button)findViewById(R.id.bAgregar);
        bBuscar=(Button)findViewById(R.id.bBuscar);
        bEliminar=(Button)findViewById(R.id.bEliminar);
        bActualizar=(Button)findViewById(R.id.bActualizar);
        bInventario=(Button)findViewById(R.id.bInventario);
        bVender=(Button)findViewById(R.id.bVender);
        bGanancias=(Button)findViewById(R.id.bGanancias);

        tId=(TextView)findViewById(R.id.tId);
        tNombre=(TextView)findViewById(R.id.tNombre);
        tCantidad=(TextView)findViewById(R.id.tCantidad);
        tValor=(TextView)findViewById(R.id.tValor);
        tInventario=(TextView)findViewById(R.id.tInventario);
        tGanancias=(TextView)findViewById(R.id.tGanancias);

        Firebase.setAndroidContext(this);
        firebasedatos = new Firebase(FIREBASE_URL);
        firebasedatosv = new Firebase(FIREBASEV_URL);

        bAgregar.setOnClickListener(this);
        bBuscar.setOnClickListener(this);
        bEliminar.setOnClickListener(this);
        bActualizar.setOnClickListener(this);
        bInventario.setOnClickListener(this);
        bVender.setOnClickListener(this);
        bGanancias.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAgregar:

                aux=bAgregar.getText().toString();

                if (aux.equals("Agregar")){

                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);
                    tValor.setVisibility(View.VISIBLE);
                    eValor.setVisibility(View.VISIBLE);

                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                    bAgregar.setText("Guardar");

                }

                else{

                    Toast.makeText(MainActivity.this, "Agregar",Toast.LENGTH_SHORT).show();
                    nom= eNombre.getText().toString();
                    can = eCantidad.getText().toString();
                    val = eValor.getText().toString();
                    firebd = firebasedatos.child("Peluchito "+ cont);
                    Peluchitos peluchitos = new Peluchitos(String.valueOf(cont),nom,can,val);
                    firebd.setValue(peluchitos);
                    eId.getText().clear();
                    eNombre.getText().clear();
                    eCantidad.getText().clear();
                    eValor.getText().clear();
                    cont++;

                    tNombre.setVisibility(View.GONE);
                    eNombre.setVisibility(View.GONE);
                    tCantidad.setVisibility(View.GONE);
                    eCantidad.setVisibility(View.GONE);
                    tValor.setVisibility(View.GONE);
                    eValor.setVisibility(View.GONE);

                    bBuscar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);

                    bAgregar.setText("Agregar");
                }
                break;

            case  R.id.bBuscar:

                aux=bBuscar.getText().toString();

                if (aux.equals("Buscar")){
                    tId.setVisibility(View.VISIBLE);
                    eId.setVisibility(View.VISIBLE);
                    bBuscar.setText("Ir");

                    bAgregar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);
                }
                else{
                    codigo = eId.getText().toString();
                    final String idS = "Peluchito "+codigo;

                    firebasedatos.addValueEventListener(new ValueEventListener() {
                        @Override

                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //Long contador=dataSnapshot.getChildrenCount();
                            //Toast.makeText(MainActivity.this, contador.toString(), Toast.LENGTH_SHORT).show();

                            if(dataSnapshot.child(idS).exists()){
                                tInventario.setVisibility(View.VISIBLE);
                                tInventario.setText(dataSnapshot.child(idS).getValue().toString());
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Éste Peluchito no existe",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    bBuscar.setText("Buscar");
                    tId.setVisibility(View.GONE);
                    eId.setVisibility(View.GONE);

                    bAgregar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.bEliminar:

                aux=bEliminar.getText().toString();

                if (aux.equals("Eliminar")){
                    tId.setVisibility(View.VISIBLE);
                    eId.setVisibility(View.VISIBLE);
                    bEliminar.setText("Borrar");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);
                }
                else{
                    codigo = eId.getText().toString();
                    firebd = firebasedatos.child("Peluchito "+codigo);
                    firebd.removeValue();

                    bEliminar.setText("Eliminar");

                    tId.setVisibility(View.GONE);
                    eId.setVisibility(View.GONE);

                    bAgregar.setVisibility(View.VISIBLE);
                    bBuscar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.bActualizar:

                aux=bActualizar.getText().toString();

                if (aux.equals("Actualizar")){

                    tId.setVisibility(View.VISIBLE);
                    eId.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);

                    bActualizar.setText("Guardar");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                }

                else{
                    codigo = eId.getText().toString();
                    can = eCantidad.getText().toString();
                    firebd = firebasedatos.child("Peluchito "+codigo);

                    Map<String, Object> nuevoCantidad = new HashMap<>();
                    nuevoCantidad.put("cantidad",can);
                    firebd.updateChildren(nuevoCantidad);

                    eId.getText().clear();
                    eCantidad.getText().clear();

                    tId.setVisibility(View.GONE);
                    eId.setVisibility(View.GONE);
                    tNombre.setVisibility(View.GONE);
                    eNombre.setVisibility(View.GONE);
                    tCantidad.setVisibility(View.GONE);
                    eCantidad.setVisibility(View.GONE);
                    tValor.setVisibility(View.GONE);
                    eValor.setVisibility(View.GONE);

                    bActualizar.setText("Actualizar");

                    bAgregar.setVisibility(View.VISIBLE);
                    bBuscar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);

                }
                break;

            case R.id.bInventario:

                tInventario.setVisibility(View.VISIBLE);
                tGanancias.setVisibility(View.GONE);

                tInventario.setText("");

                firebasedatos.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tInventario.setText(dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
                break;

            case R.id.bVender:

                aux=bVender.getText().toString();

                if (aux.equals("Vender")) {

                    bVender.setText("Confirmar");

                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setText("1");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);
                }

                else{

                    firebasedatos.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Peluchitos valores=dataSnapshot.getValue(Peluchitos.class);
                            System.out.println("id: " + valores.getId());
                            System.out.println("nombre: " + valores.getNombre());
                            System.out.println("valor: " + valores.getValor());
                            System.out.println("cantidad: " + valores.getCantidad());
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                }
                break;

            case R.id.bGanancias:

                tGanancias.setVisibility(View.VISIBLE);
                tInventario.setVisibility(View.GONE);

                tGanancias.setText("");

                firebasedatosv.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tGanancias.setText(dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                break;
        }
    }
}