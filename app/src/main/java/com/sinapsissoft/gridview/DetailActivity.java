package com.sinapsissoft.gridview;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sinapsissoft.gridview.class_general.General;
import com.sinapsissoft.gridview.dto.MenuApp;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    public static final String VIEW_NAME_HEADER_TEXT = "text_compartida";
    private MenuApp menuApp;
    private ImageView imagenExtendida;
    private View content_view_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            loadGeneral();
        }
        loadToolbar();
        // Obtener el coche con el identificador establecido en la actividad principal
        menuApp = MenuApp.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));


        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);
        imagenExtendida.setImageResource(menuApp.getIdDrawable());
        TextView textView = (TextView) findViewById(R.id.nombre_coche2);
        textView.setText(menuApp.getNombre());
        //cargarImagenExtendida();
        loadView();

    }
    private void loadGeneral(){
        General.CONTEXT=this;
    }
    private void loadView(){
        content_view_items=findViewById(R.id.content_view_items);
    }
    private void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(menuApp.getIdDrawable())
                .into(imagenExtendida);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
            // this.finishAfterTransition();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iItems=0;
        switch (item.getItemId()) {
            case R.id.action_image:
                break;
            case R.id.action_text:
                break;
            case R.id.action_signpost:
                break;
            case R.id.action_video:
                break;

        }
        selectedItems(content_view_items,iItems);
        return super.onOptionsItemSelected(item);


    }
    private void selectedItems(View view,int iItems){
        if(view.getVisibility()== View.VISIBLE){
            view.setVisibility(View.GONE);
        }
        else{
            view.setVisibility(View.VISIBLE);
        }
    }

}
