package se.umu.cs.android.thirty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This is the activity that will host the start fragment and the game fragment
 */
public class GameActivity extends AppCompatActivity implements FragmentChangeListener {

    /**
     * Start the activity and if there is no fragment set the rules fragment as active fragment.
     * @param savedInstanceState If there is a saved state the data will be store in this.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new RulesFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    /**
     * This method makes it possible to switch the fragment currently hosted in the activity.
     * @param fragment The new fragment to get hosted.
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
