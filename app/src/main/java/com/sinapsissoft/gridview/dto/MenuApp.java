package com.sinapsissoft.gridview.dto;

import com.sinapsissoft.gridview.R;

/**
 * Created by DIEGO H CASALLAS  on 2/25/2017.
 */

public class MenuApp {
    private String nombre;
    private int idDrawable;

    public MenuApp(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static MenuApp[] ITEMS = {
            new MenuApp("Jaguar F-Type 2015", R.mipmap.ic_launcher),
            new MenuApp("Mercedes AMG-GT", R.mipmap.ic_launcher),
            new MenuApp("Mazda MX-5", R.mipmap.ic_launcher),
            new MenuApp("Porsche 911 GTS", R.mipmap.ic_launcher),
            new MenuApp("BMW Serie 6", R.mipmap.ic_launcher),
            new MenuApp("Ford Mondeo", R.mipmap.ic_launcher),
            new MenuApp("Volvo V60 Cross Country", R.mipmap.ic_launcher),
            new MenuApp("Jaguar XE", R.mipmap.ic_launcher),
            new MenuApp("VW Golf R Variant", R.mipmap.ic_launcher),
            new MenuApp("Seat León ST Cupra", R.mipmap.ic_launcher),
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static MenuApp getItem(int id) {
        for (MenuApp item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
