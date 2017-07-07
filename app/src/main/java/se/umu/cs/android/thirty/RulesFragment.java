package se.umu.cs.android.thirty;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This Fragment shows information regarding the rules of the game.
 */
public class RulesFragment extends Fragment {

    private Button mStartButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Set up the UI and if there is a saved state restore that state to the fragment.
     * @param inflater Used to inflate the UI elements.
     * @param container The container that will host the fragment.
     * @param savedInstanceState Contains the saved data of the fragment
     * @return The finished view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules, container, false);

        //Button to start the game and switch this fragment for the game fragment.
        mStartButton = (Button) view.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(v -> {
            FragmentChangeListener changeListener = (FragmentChangeListener) getActivity();
            changeListener.replaceFragment(new GameFragment());
        });

        return view;
    }


}
