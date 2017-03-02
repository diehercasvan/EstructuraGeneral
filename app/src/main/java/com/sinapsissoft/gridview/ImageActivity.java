package com.sinapsissoft.gridview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sinapsissoft.gridview.class_general.Animation_general;
import com.sinapsissoft.gridview.class_general.General;
import com.sinapsissoft.gridview.class_general.MenuFragment;


public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final boolean AUTO_HIDE = true;
    public static final String VIEW_NAME_HEADER_TEXT = "share_text";
    public static final String VIEW_NAME_HEADER_IMAGE = "share_image";
    public static final String EXTRA_PARAM_ID = "com.sinapsissoft.gridview.extra.ID";
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private View mControlsView;
    private FloatingActionButton floatingActionButton;
    private View content_view_items;
    private Animation_general animation_general;
    private boolean bSelectItemsToolbar = false;
    private int iItemsPreviuss;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
           /* mContentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);*/
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            //mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image);
        loadGeneral();
        loadToolbar();
        loadView();

        mVisible = true;
        //mControlsView = findViewById(R.id.contentFloating);
        mContentView = findViewById(R.id.fullscreen_content);

        findViewById(R.id.btn_imag).setOnClickListener(this);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


    }

    private void loadGeneral() {

        General.CONTEXT = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();

        }


    }

    private void loadView() {
        animation_general = new Animation_general();
        content_view_items = findViewById(R.id.content_view_items);
    }

    private void loadToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Datos", Toast.LENGTH_SHORT).show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    finishAfterTransition();
                } else {
                    finish();
                }

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the MenuApp; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int iItems = 0;
        switch (item.getItemId()) {
            case R.id.action_image:
                iItems = 0;
                break;
            case R.id.action_text:
                iItems = 1;
                break;
            case R.id.action_signpost:
                iItems = 2;
                break;
            case R.id.action_video:
                iItems = 3;
                break;

        }

        selectedItems(content_view_items, iItems);
        return super.onOptionsItemSelected(item);

    }

    private void selectedItems(View view, int iItems) {

        if (view.getVisibility() == View.VISIBLE) {
            //view.setVisibility(View.GONE);
            if (iItemsPreviuss == iItems) {
                HideContent(view);
            } else {
                iItemsPreviuss = iItems;
            }
        } else {
            //view.setVisibility(View.VISIBLE);
            iItemsPreviuss = iItems;
            revealContent(view);
        }
        menuSelectedItem(iItems);
    }

    private void menuSelectedItem(int iItems) {
        View view = findViewById(R.id.content_view_items);
        view.clearAnimation();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
        fragmentTransaction.replace(R.id.content_view_items, new MenuFragment().selectedFragment(iItems), "view items");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack();
        fragmentTransaction.commit();


    }

    private void HideContent(final View view) {

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
            anim.start();
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.GONE);

                }
            });
        } else {
            view.startAnimation(animation_general.selectAnimation(1, this));
            view.setVisibility(View.GONE);
        }

    }

    private void revealContent(View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = null;
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            view.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation_general.selectAnimation(0, this));

        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();


        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Si  es  lo  que yo quiero", Toast.LENGTH_LONG).show();
    }
}
