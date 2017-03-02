package com.sinapsissoft.gridview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadGeneral();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadVIew();
        setupWindowAnimations();
    }
    private void loadGeneral() {

        General.CONTEXT = this;
    }
    private void loadVIew() {
        GridView gridView = (GridView) findViewById(R.id.gridViewMenu);
        gridView.setAdapter(new ImageAdapter(this));
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
        gridView.setOnItemClickListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        /*MenuItemCompat.OnActionExpandListener expandListener=new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(getApplicationContext(),"Si",Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                return true;
            }
        };*/
        MenuItem searchItem=menu.findItem(R.id.action_search);
       //MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);
         SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        return  super.onCreateOptionsMenu(menu);
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
    public boolean onNavigationItemSelected(MenuItem item) {
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
