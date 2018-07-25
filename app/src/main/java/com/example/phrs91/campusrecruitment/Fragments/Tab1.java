package com.example.phrs91.campusrecruitment.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phrs91.campusrecruitment.R;
import com.example.phrs91.campusrecruitment.UserProfile;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private UserProfile mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(UserProfile param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (UserProfile)getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_tab1, container, false);
        TextView t1=(TextView)v.findViewById(R.id.regno);
        t1.setText(mParam1.getRegisterNumber());
        TextView name=(TextView)v.findViewById(R.id.nameofuser);
        name.setText(mParam1.getName());
        TextView email=(TextView)v.findViewById(R.id.email);
        email.setText(mParam1.getEmail());
        TextView adress=(TextView)v.findViewById(R.id.address);
        adress.setText(mParam1.getAddress());
        TextView state=(TextView)v.findViewById(R.id.state);
        state.setText(mParam1.getState());
        TextView country=(TextView)v.findViewById(R.id.country);
        country.setText(mParam1.getCountry());
        TextView dob=(TextView)v.findViewById(R.id.dob);
        dob.setText(mParam1.getDobirth());
         name=(TextView)v.findViewById(R.id.phone);
        name.setText(mParam1.getPhone());
        name=(TextView)v.findViewById(R.id.category);
        name.setText(mParam1.getCate());
         name=(TextView)v.findViewById(R.id.course);
        name.setText(mParam1.getCourse());
         name=(TextView)v.findViewById(R.id.branch);
        name.setText(mParam1.getBranch());
        name=(TextView)v.findViewById(R.id.gender);
        name.setText(mParam1.getGender());



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
