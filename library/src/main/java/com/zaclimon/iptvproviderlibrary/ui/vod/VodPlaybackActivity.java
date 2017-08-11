package com.zaclimon.iptvproviderlibrary.ui.vod;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.zaclimon.iptvproviderlibrary.R;
import com.zaclimon.iptvproviderlibrary.util.ActivityUtil;

/**
 * Activity responsible for VOD playback.
 *
 * @author zaclimon
 * Creation date: 11/08/17
 */

public abstract class VodPlaybackActivity extends Activity {

    public static final int NO_THEME = -1;

    /**
     * Defines the theme id to set for this {@link Activity}. Note that the no theme might be set
     * with {@link #NO_THEME}
     *
     * @return the theme resource id or {@link #NO_THEME} if none set.
     */
    protected abstract int getThemeId();

    /**
     * Defines a {@link VodPlaybackFragment} that will be used to playback the given content.
     *
     * @return the fragment that will play the given content.
     */
    protected abstract VodPlaybackFragment getVodPlaybackFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityUtil.isTvMode(this) && getThemeId() != NO_THEME) {
            setTheme(getThemeId());
        } else if (ActivityUtil.isTvMode(this)) {
            setTheme(R.style.Theme_Leanback);
        }

        setContentView(R.layout.activity_vod_playback);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment playbackFragment = getVodPlaybackFragment();
        Bundle arguments = getIntent().getExtras();
        playbackFragment.setArguments(arguments);
        fragmentTransaction.add(R.id.activity_vod_playback_fragment, playbackFragment);

        fragmentTransaction.commit();

    }

}