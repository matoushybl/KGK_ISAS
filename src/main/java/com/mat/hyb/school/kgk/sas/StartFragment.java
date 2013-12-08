package com.mat.hyb.school.kgk.sas;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by matous on 4.12.13.
 */
public class StartFragment extends Fragment {
    public StartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
            getActivity().getActionBar().setDisplayUseLogoEnabled(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_start, container, false);
        assert layout != null;
        Button marks = (Button) layout.findViewById(R.id.button_marks);
        Button supplementation = (Button) layout.findViewById(R.id.button_substitution);
        Button moodle = (Button) layout.findViewById(R.id.button_moodle);
        Button canteen = (Button) layout.findViewById(R.id.button_canteen);

        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.container, new MarksFragment())
                            .commit();
                }
            }
        });
        supplementation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null && getFragmentManager() != null) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container,
                                    new SubstitutionFragment(getActivity().getApplicationContext()))
                            .commit();
                }
            }
        });
        moodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BasicWebFragment.MODE, BasicWebFragment.MODE_MOODLE);
                BasicWebFragment moodleFragment = new BasicWebFragment();
                moodleFragment.setArguments(bundle);
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().
                        replace(R.id.container, moodleFragment).commit();
            }
        });
        canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    startActivity(new Intent().setAction(Intent.ACTION_VIEW)
                            .setData(Uri.parse(new UrlProvider(getActivity().getApplicationContext())
                                    .getCanteenUrl())));
                }
            }
        });
        return layout;
    }
}
