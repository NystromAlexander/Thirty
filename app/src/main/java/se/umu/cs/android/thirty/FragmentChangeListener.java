package se.umu.cs.android.thirty;

import android.support.v4.app.Fragment;

/**
 * This interface is used to allow fragments to switch fragment in their host activity.
 */

public interface FragmentChangeListener {
    void replaceFragment(Fragment fragment);
}
