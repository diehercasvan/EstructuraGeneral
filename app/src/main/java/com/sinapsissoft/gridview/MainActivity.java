package com.sinapsissoft.gridview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sinapsissoft.gridview.adapters.ImageAdapter;
import com.sinapsissoft.gridview.class_general.General;
import com.sinapsissoft.gridview.dto.MenuApp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private GridView gridView = null;

    private Toolbar toolbar;
    private SearchView searchView;


    private String[] sTitle = {"Jaguar F-Type 2015", "Mercedes AMG-GT", "Mazda MX-5", "Porsche 911 GTS", "BMW Serie 6", "Ford Mondeo", "Volvo V60 Cross Country", "Jaguar XE", "VW Golf R Variant", "Seat León ST Cupra"};
    private int[] iIdImage = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadGeneral();
        }
        loadToolbar();
        loadDrawer();
        loadNavigationView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadVIew();
        setupWindowAnimations();
    }

    private void loadToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void loadDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void loadNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadGeneral() {

        General.CONTEXT = this;
    }

    private void loadVIew() {

        loadGridMenu();
    }

    public void loadGridMenu() {

        gridView = (GridView) findViewById(R.id.gridViewMenu);
        searchView = (SearchView) findViewById(R.id.action_search);

        final ImageAdapter imageAdapter = new ImageAdapter(this, this.getMenuApp());
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(TextUtils.isEmpty(newText)){

                    imageAdapter.getFilter().filter("");
                    gridView.clearTextFilter();
                }
                else{

                    imageAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
    }

    private ArrayList<MenuApp> getMenuApp() {
        ArrayList<MenuApp> menuApp = new ArrayList<>();
        MenuApp m;
        for (int i = 0; i < sTitle.length; i++) {
            m = new MenuApp(sTitle[i], iIdImage[i]);
            menuApp.add(m);
        }
        return menuApp;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the MenuApp; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
*/


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MenuApp item = (MenuApp) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_PARAM_ID, item.getId());

        /*MenuApp.ITEMS=null;
        MenuApp.ITEMS=new MenuApp[1];
        MenuApp.ITEMS[0]=new MenuApp("Diego",R.drawable.ic_menu_share);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    new Pair<>(view.findViewById(R.id.nombre_coche), ImageActivity.VIEW_NAME_HEADER_TEXT),
                    new Pair<>(view.findViewById(R.id.imagen_menu), ImageActivity.VIEW_NAME_HEADER_IMAGE)
            );
            /*ActivityOptionsCompat activityOptions=ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    Pair.create(view.findViewById(R.id.imagen_menu), view.getTransitionName()),
                    Pair.create(view.findViewById(R.id.nombre_coche), view.getTransitionName()));*/


            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());

        } else
            startActivity(intent);

    }

    @SuppressLint("RtlHardcoded")
    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide t1 = new Slide();
            t1.setSlideEdge(Gravity.LEFT);
            //getWindow().setReenterTransition(t1);
            getWindow().setExitTransition(new Explode().setDuration(500));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
