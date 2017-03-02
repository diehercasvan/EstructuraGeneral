package com.sinapsissoft.gridview.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sinapsissoft.gridview.R;
import com.sinapsissoft.gridview.dto.MenuApp;

/**
 * Created by DIEGOH on 2/25/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MenuApp.ITEMS.length;
    }

    @Override
    public MenuApp getItem(int position) {
        return MenuApp.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;*/
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.grid_item_menu,parent,false);
        }
        ImageView imageMenu = (ImageView) convertView.findViewById(R.id.imagen_menu);
        TextView title = (TextView) convertView.findViewById(R.id.nombre_coche);

        final MenuApp item = getItem(position);
       // Glide.with(imageMenu.getContext()).load(item.getIdDrawable()).into(imageMenu);
        imageMenu.setImageResource(item.getIdDrawable());
        title.setText(item.getNombre());

        return  convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher


    };
}
