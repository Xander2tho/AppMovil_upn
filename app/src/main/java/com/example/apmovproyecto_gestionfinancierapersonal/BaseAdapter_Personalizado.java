package com.example.apmovproyecto_gestionfinancierapersonal;
import android.graphics.Color;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter_Personalizado extends BaseAdapter {

    Context context;

    List<String> GasIn_Nombre = new ArrayList<>();
    List<Integer> GasIn_Img = new ArrayList<>();
    List<String> GasIn_numero = new ArrayList<>();
    List<String> GasIn_color = new ArrayList<>();


    LayoutInflater inflater;
    public BaseAdapter_Personalizado(Context ctx, List<String> GasIn_Nombre, List<String> GasIn_numero, List<Integer> GasIn_Img, List<String> GasIn_color){
        this.context = ctx;
        this.GasIn_Nombre = GasIn_Nombre;
        this.GasIn_Img = GasIn_Img;
        this.GasIn_numero = GasIn_numero;
        this.GasIn_color = GasIn_color;
        inflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return GasIn_Nombre.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_tarjeta_personalizada, null);
        TextView txtview_nombre = (TextView) convertView.findViewById(R.id.nombre_gastoOingreso);
        ImageView imgview = (ImageView) convertView.findViewById(R.id.img_gastoOingreso);
        TextView textview_numero = (TextView) convertView.findViewById(R.id.Valor_GastoOIngreso);
        txtview_nombre.setText(GasIn_Nombre.get(position));
        imgview.setImageResource(GasIn_Img.get(position));
        textview_numero.setText(GasIn_numero.get(position));

        String color = GasIn_color.get(position);
        textview_numero.setTextColor(Color.parseColor(color));


        return convertView;
    }
}
