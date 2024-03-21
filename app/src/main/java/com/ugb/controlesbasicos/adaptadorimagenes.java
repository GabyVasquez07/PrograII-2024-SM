package com.ugb.controlesbasicos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adaptadorimagenes extends BaseAdapter {
    Context context;
    ArrayList<amigos> datosProductosArrayList;
    amigos datosProductos;
    LayoutInflater layoutInflater;
    public adaptadorimagenes(Context context, ArrayList<amigos> datosProductosArrayList) {
        this.context = context;
        this.datosProductosArrayList = datosProductosArrayList;
    }
    @Override
    public int getCount() {
        return datosProductosArrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return datosProductosArrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return Long.parseLong(datosProductosArrayList.get(i).getIdproductos());
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes, viewGroup, false);
        try{
            datosProductos = datosProductosArrayList.get(i);

            TextView tempVal = itemView.findViewById(R.id.lblcodigo);
            tempVal.setText(datosProductos.getCodigo());

            tempVal = itemView.findViewById(R.id.lbldescripcion);
            tempVal.setText(datosProductos.getDescripcion());

            tempVal = itemView.findViewById(R.id.lblprecio);
            tempVal.setText(datosProductos.getPrecio());

            Bitmap imageBitmap = BitmapFactory.decodeFile(datosProductos.getUrlFotoProdu());
            ImageView img = itemView.findViewById(R.id.imgFoto);
            img.setImageBitmap(imageBitmap);
        }catch (Exception e){
            Toast.makeText(context, "Error al mostrar los datos: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return itemView;
    }
}