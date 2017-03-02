package com.sinapsissoft.gridview.class_general;

import android.support.v4.app.Fragment;
import com.sinapsissoft.gridview.fragment.ImageFragment;
import com.sinapsissoft.gridview.fragment.SignalingFragment;
import com.sinapsissoft.gridview.fragment.TextFragment;
import com.sinapsissoft.gridview.fragment.VideoFragment;

/**
 * Created by DIEGO H CASALLAS on 2/22/2017.
 */

public class MenuFragment {
    Fragment fragment;

    public Fragment selectedFragment(int iSelect) {

        switch (iSelect) {
            case 0:
                fragment = new ImageFragment();
                break;
            case 1:
                fragment = new TextFragment();
                break;
            case 2:
                fragment = new SignalingFragment();
                break;
            case 3:
                fragment = new VideoFragment();
                break;
        }

        return fragment;
    }
}
