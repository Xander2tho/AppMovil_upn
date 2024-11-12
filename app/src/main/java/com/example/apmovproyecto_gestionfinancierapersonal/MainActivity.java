package com.example.apmovproyecto_gestionfinancierapersonal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //crear botón añadir (ingresos o gastos)
    private ImageButton btn_anadir;//(+)
    private ImageButton btn_inicio;
    private TextView txt_inicio;
    private ImageButton btn_grafica;
    private TextView txt_grafica;

    private TextView tvTotalGastos;
    private TextView tvTotalIngresos;
    private TextView tvSaldo;
    private float totalGastos = 0.0f;
    private float totalIngresos = 0.0f;
    private float totalSaldo = 0.0f;

    //------------------------------------------------

    static ArrayList<String> GI_nombre = new ArrayList<>();
    static ArrayList<String> GI_numero = new ArrayList<>();
    static ArrayList<Integer> GI_Img = new ArrayList<>();
    static ArrayList<String> GI_color= new ArrayList<>();

   // String GasIn_list[] = {"Compras", "Comida", "Tiempo Parcial", "Salario"};
   //String GasIn_numero[] = {"-123", "34", "456", "234"};
    //int GasIn_Img[] = {R.drawable.compras, R.drawable.comida, R.drawable.start_grafic, R.drawable.start_salario};



    private ListView listView;
    BaseAdapter_Personalizado baseAdapterPersonalizado;

    //------------------------------------------------





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_anadir = (ImageButton)findViewById(R.id.img_añadir);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, activity_add.class);
                startActivityForResult(i,1);
            }
        });
        txt_inicio = (TextView)findViewById(R.id.txt_inicio);
        btn_inicio = (ImageButton)findViewById(R.id.img_inicio);


        txt_grafica = (TextView)findViewById(R.id.txt_grafica);
        btn_grafica = (ImageButton)findViewById(R.id.img_grafica);
        btn_grafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambio de color en las imagenes
                btn_grafica.setColorFilter(ContextCompat.getColor(MainActivity.this,R.color.red), PorterDuff.Mode.SRC_IN);
                txt_grafica.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));

                btn_inicio.setColorFilter(ContextCompat.getColor(MainActivity.this,R.color.white), PorterDuff.Mode.SRC_IN);
                txt_inicio.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));

                Intent i = new Intent(MainActivity.this, activity_grafic.class);
                startActivity(i);

            }
        });

        tvTotalGastos = findViewById(R.id.int_TotalGastos);
        tvTotalIngresos = findViewById(R.id.int_TotalIngresos);
        tvSaldo = findViewById(R.id.int_TotalSaldo);


        //ALL ABOUT LIST

        listView = (ListView) findViewById(R.id.Lista_Gastos_Consumos);
        baseAdapterPersonalizado = new BaseAdapter_Personalizado(getApplicationContext(), GI_nombre, GI_numero, GI_Img, GI_color);
        listView.setAdapter(baseAdapterPersonalizado);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        float aux_gastos= 0.0f, aux_ingreso = 0.0f;


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String categoria = data.getStringExtra("NOMBRE");
            String valor = data.getStringExtra("VALOR");
            String color = data.getStringExtra("COLOR");
            String total_gastos = data.getStringExtra("TOTAL_GASTOS");
            String total_ingresos = data.getStringExtra("TOTAL_INGRESOS");



            Toast.makeText(MainActivity.this, "NUMERO IS :" + total_gastos, Toast.LENGTH_SHORT).show();

            // Añadir los nuevos datos a las listas y notificar al adaptador
            GI_nombre.add(categoria);
            GI_numero.add(valor);
            GI_color.add(color);

            if(total_gastos != null){
                tvTotalGastos.setText(total_gastos);
                 aux_gastos = Float.parseFloat(total_gastos);
            }
            if(total_ingresos != null){
                tvTotalIngresos.setText(total_ingresos);
                aux_ingreso = Float.parseFloat(total_ingresos);
            }

            totalSaldo = aux_ingreso - aux_gastos;
            String aux_saldo = Float.toString(totalSaldo);
            tvSaldo.setText(aux_saldo);



            // Obtén el ID del recurso usando el nombre en la variable 'categoria'
            int imgResId = getResources().getIdentifier(categoria, "drawable", getPackageName());

            GI_Img.add(imgResId); // Cambia a la imagen que desees o define otra

            baseAdapterPersonalizado.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado


        }
    }
}