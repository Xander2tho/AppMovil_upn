package com.example.apmovproyecto_gestionfinancierapersonal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_grafic extends AppCompatActivity {

    private TextView txt_inicio, txt_grafica;
    private ImageButton btn_inicio, btn_grafica, btn_anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);


        btn_anadir = (ImageButton)findViewById(R.id.img_añadir);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_grafic.this, activity_add.class);
                startActivity(i);
            }
        });



        txt_inicio = (TextView)findViewById(R.id.txt_inicio);
        btn_inicio = (ImageButton)findViewById(R.id.img_inicio);

        txt_grafica = (TextView)findViewById(R.id.txt_grafica);
        btn_grafica = (ImageButton)findViewById(R.id.img_grafica);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambio de color en las imagenes
                btn_inicio.setColorFilter(ContextCompat.getColor(activity_grafic.this,R.color.red), PorterDuff.Mode.SRC_IN);
                txt_inicio.setTextColor(ContextCompat.getColor(activity_grafic.this, R.color.red));

                btn_grafica.setColorFilter(ContextCompat.getColor(activity_grafic.this,R.color.white), PorterDuff.Mode.SRC_IN);
                txt_grafica.setTextColor(ContextCompat.getColor(activity_grafic.this, R.color.white));

                Intent i = new Intent(activity_grafic.this, MainActivity.class);
                startActivity(i);

            }
        });


    }

}