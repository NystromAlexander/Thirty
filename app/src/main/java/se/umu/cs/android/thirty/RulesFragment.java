package se.umu.cs.android.thirty;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RulesFragment extends Fragment {

    private Button mStartButton;

//    public RulesFragment() {
//        // Required empty public constructor
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        mStartButton = (Button) view.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(v -> {
            FragmentChangeListener changeListener = (FragmentChangeListener) getActivity();
            changeListener.replaceFragment(new GameFragment());
        });

        return view;
    }





}
