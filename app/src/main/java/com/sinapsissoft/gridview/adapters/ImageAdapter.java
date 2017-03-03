package com.sinapsissoft.gridview.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinapsissoft.gridview.R;
import com.sinapsissoft.gridview.dto.MenuApp;

import java.util.ArrayList;

/**
 * Created by DIEGOH on 2/25/2017.
 */

public class ImageAdapter extends BaseAdapter implements Filterable {
    private Context context;
    ArrayList<MenuApp> menuApps;
    ArrayList<MenuApp> filterList;
    CustomFilter filter;

    public ImageAdapter(Context ctx, ArrayList<MenuApp> lists) {
        this.context = ctx;
        this.menuApps = lists;
        this.filterList = lists;
    }

    @Override
    public int getCount() {
        return menuApps.size();
    }

    @Override
    public Object getItem(int position) {
        return menuApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menuApps.indexOf(getItem(position));
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.grid_item_menu, parent,false);
        }
        ImageView imageMenu = (ImageView) convertView.findViewById(R.id.imagen_menu);
        TextView title = (TextView) convertView.findViewById(R.id.nombre_coche);

        //final MenuApp item = getItem(position);
        // Glide.with(imageMenu.getContext()).load(item.getIdDrawable()).into(imageMenu);
        imageMenu.setImageResource(menuApps.get(position).getIdDrawable());
        title.setText(menuApps.get(position).getNombre());

        return convertView;
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

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() > 0) {

                charSequence = charSequence.toString().toUpperCase();
                ArrayList<MenuApp> filters = new ArrayList<>();
                //Filtering
                for (int i = 0; i < filterList.size(); i++) {

                    if (filterList.get(i).getNombre().toUpperCase().contains(charSequence)) {
                        MenuApp m = new MenuApp(filterList.get(i).getNombre(), filterList.get(i).getIdDrawable());
                        filters.add(m);
                        Log.i("DATA",filters.get(i).getNombre());
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }else{
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            menuApps = (ArrayList<MenuApp>) filterResults.values;
            notifyDataSetChanged();

        }
    }
}
