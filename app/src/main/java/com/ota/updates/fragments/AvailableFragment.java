package com.ota.updates.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ota.updates.R;
import com.ota.updates.utils.Constants;
import com.ota.updates.utils.FragmentInteractionListener;

import in.uncod.android.bypass.Bypass;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AvailableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableFragment extends Fragment implements Constants {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentInteractionListener mListener;

    public AvailableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvailableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvailableFragment newInstance(String param1, String param2) {
        AvailableFragment fragment = new AvailableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        setupUpdateIconsText(view);

        setupChangelog(view, activity);

        return view;
    }

    /**
     * Sets up the update icon text for the upper seam of the app
     *
     * @param view The root view for the fragment
     */
    private void setupUpdateIconsText(View view) {
        View fileNameView = view.findViewById(R.id.filename);
        if (fileNameView != null) {
            TextView fileNameTV = (TextView) fileNameView;
        }

        View fileSizeView = view.findViewById(R.id.filesize);
        if (fileSizeView != null) {
            TextView fileSizeTV = (TextView) fileSizeView;
        }

        View fileHostView = view.findViewById(R.id.filehost);
        if (fileHostView != null) {
            TextView fileHostTV = (TextView) fileHostView;
        }

        View fileHashView = view.findViewById(R.id.filehash);
        if (fileHashView != null) {
            TextView fileHashTV = (TextView) fileHashView;
        }
    }

    private void setupChangelog(View view, Context context) {
        View changelog = view.findViewById(R.id.changelog);
        if (changelog != null) {
            TextView tv = (TextView) changelog;
            Bypass byPass = new Bypass(context);
            String changelogStr = "* ROM \n" +
                    "    * Fixed tethering\n" +
                    "    * Added ipv6 tethering\n" +
                    "    * Launcher3: Optimisation and some Materialize\n" +
                    "    * Fixed back/menu keys wake up the device\n" +
                    "    * A lot of optimisation to build enviroment\n" +
                    "    * Updated translation for AOSP Settings and Power menu\n" +
                    "    * Updated stagefright/av with latest cm changes\n" +
                    "    * Fixed OTA Updates\n" +
                    "    * Added clear recents app button\n" +
                    "    * Make \"SD Card removed\" notification dismissible\n" +
                    "    * Various frameworks improvements";
            CharSequence string = byPass.markdownToSpannable(changelogStr);
            tv.setText(string);
            tv.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
