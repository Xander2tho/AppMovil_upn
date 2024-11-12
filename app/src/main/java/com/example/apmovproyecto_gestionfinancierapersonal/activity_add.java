package com.example.apmovproyecto_gestionfinancierapersonal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class activity_add extends AppCompatActivity {

    //Regresar al Inicio de la app
    private Button btn_regresar;
    //Gastos e ingresos
    private Button btn_gastos, btn_ingresos;
    //Flipper
    private ViewFlipper view_flipper;

    private View teclado_personalizado;
    private EditText et_numero_input;


    private String operator  = "";
    private Double firstNumber  = null;


    private static String input = "";
    private static String selectedCategory = "";
    public static Drawable the_img = null;

    //--------------------------------------
    private static String   TOTAL_gastos;
    private static String   TOTAL_ingresos;
    private static String   SALDO;

    private static float f_gastos;
    private static float f_ingresos;
    private static float f_saldo;



    public String Valor_Color;

    //---------------------------------



    //---------------------------------
    public static boolean gasto0_ingreso1;

    private ListView List_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gasto0_ingreso1 = false;

        setContentView(R.layout.activity_add);
        //obtenemos el valor
        btn_regresar = findViewById(R.id.btn_regresar);
        //obtenemos gastos e ingresos
        btn_ingresos = findViewById(R.id.btn_ingresos);
        btn_gastos = findViewById(R.id.btn_gastos);
        //Obtenemos el flipper
        view_flipper = findViewById(R.id.view_fliper);

        //obtenemos el teclado
        teclado_personalizado = findViewById(R.id.teclado_personal);
        //obtenemos la barra de llenado
        et_numero_input = teclado_personalizado.findViewById(R.id.etNumberInput);


        //Click en Regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_add.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Click en Gastos
        btn_gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gasto0_ingreso1 = false;//LOS GASTOS MANDAN -- EL VALOR DE NUMERO ES NEGATIVO (-)

                view_flipper.setInAnimation(activity_add.this, R.anim.slide_in_left);
                view_flipper.setOutAnimation(activity_add.this, R.anim.slide_out_right);


                //flip to Gastos_card
                if(view_flipper.getDisplayedChild() != 0){
                    view_flipper.setDisplayedChild(0);
                }

                //colorear btn_Gastos en blanco

                btn_gastos.setBackgroundColor(getResources().getColor(R.color.light_white));
                btn_ingresos.setBackgroundColor(getResources().getColor(R.color.light_black));


                btn_gastos.setTextColor(getResources().getColor(R.color.light_black));
                btn_ingresos.setTextColor(getResources().getColor(R.color.light_white));
            }
        });

        //Click en Ingresos
        btn_ingresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gasto0_ingreso1 = true;//LOS INGRESOS MANDAN -- EL VALOR DE NUMERO ES POSITIVO (+)

                view_flipper.setInAnimation(activity_add.this, R.anim.slide_in_right);
                view_flipper.setOutAnimation(activity_add.this, R.anim.slide_out_left);
                //flip to Gastos_card
                if(view_flipper.getDisplayedChild() != 1){
                    view_flipper.setDisplayedChild(1);
                }

                //colorear btn_ingresos en blanco
                btn_ingresos.setBackgroundColor(getResources().getColor(R.color.light_white));
                btn_gastos.setBackgroundColor(getResources().getColor(R.color.light_black));

                btn_ingresos.setTextColor(getResources().getColor(R.color.light_black));
                btn_gastos.setTextColor(getResources().getColor(R.color.light_white));


            }
        });


        setupCategoryButtons();
        setupKeyboard();

    }

    private void setupCategoryButtons() {
        int[] categoryButtonIds = new int[]{
                R.id.imgb_compras, R.id.imgb_comida,
                R.id.imgbtn_parcial, R.id.imgbtn_salario
                // Agrega más IDs de botones aquí si tienes más categorías
        };

        for (int id : categoryButtonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton button_img = (ImageButton) v;
                    selectedCategory = button_img.getContentDescription().toString();
                    the_img = button_img.getBackground();
                    Toast.makeText(activity_add.this, "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
                    //invocar teclado numerico
                    if (teclado_personalizado.getVisibility() == View.GONE) {
                        teclado_personalizado.setVisibility(View.VISIBLE);
                    } else {
                        teclado_personalizado.setVisibility(View.GONE);
                    }

                }
            });
        }
    }
    private void setupKeyboard() {
        int[] buttonIds = new int[]{
                R.id.btnOne, R.id.btnTwo, R.id.btnThree,
                R.id.btnFour, R.id.btnFive, R.id.btnSix,
                R.id.btnSeven, R.id.btnEight, R.id.btnNine,
                R.id.btnZero, R.id.btnDot, R.id.btnBackspace,
                R.id.btnPlus, R.id.btnConfirm, R.id.btnequal
        };

        for (int id : buttonIds){
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    handleButtonClick(button);
                }
            });
        }
    }
    private void handleButtonClick(Button button) {
        int button_id = button.getId();
        if(button_id == R.id.btnBackspace){
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
            }
        }else if(button_id == R.id.btnPlus){
            if (!input.isEmpty()) {
                calculate();
                operator = button.getText().toString();
                firstNumber = Double.valueOf(input);
                input = "";
            }
        }else if(button_id == R.id.btnequal){
            calculate();
            operator = "";
            firstNumber = null;
        }else if(button_id == R.id.btnConfirm){
            calculate();
            operator = "";
            firstNumber = null;

            if (!selectedCategory.isEmpty() && !input.isEmpty()) {

                if(!gasto0_ingreso1){ //GASTO (-)

                    float t_gastos = Float.parseFloat(input);
                    f_gastos += t_gastos;
                    TOTAL_gastos = Float.toString(f_gastos);




                    input = "-"+ input;
                    Valor_Color = "#FF0000";
                }else{ //INGRESO (+)
                    float t_ingresos = Float.parseFloat(input);
                    f_ingresos += t_ingresos;
                    TOTAL_ingresos = Float.toString(f_ingresos);


                    input = "+"+ input;
                    Valor_Color = "#2EFF00";
                }


                //obtener el nombre de categoria y valor
                Intent resultintent = new Intent();
                resultintent.putExtra("NOMBRE", selectedCategory);
                resultintent.putExtra("VALOR", input);
                resultintent.putExtra("COLOR", Valor_Color);
                resultintent.putExtra("TOTAL_GASTOS", TOTAL_gastos);
                resultintent.putExtra("TOTAL_INGRESOS", TOTAL_ingresos);
                setResult(RESULT_OK, resultintent);
                finish();

                //Realizar una copia de la (lista_personalizada)


                //ingresar los valores "input-numero de gasto o ingreso"


                input = "";


            }
        }
        else {
            input += button.getText().toString();
        }
        et_numero_input.setText(input);
    }

    private void calculate() {
        if (firstNumber != null && !input.isEmpty()) {
            Double secondNumber = Double.valueOf(input);
            switch (operator) {
                case "+":
                    input = String.valueOf(firstNumber + secondNumber);
                    break;
            }
        }
    }

}