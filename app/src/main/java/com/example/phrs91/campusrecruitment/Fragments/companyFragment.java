package com.example.phrs91.campusrecruitment.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phrs91.campusrecruitment.MainActivity;
import com.example.phrs91.campusrecruitment.NotificationAdapter;
import com.example.phrs91.campusrecruitment.R;
import com.example.phrs91.campusrecruitment.UserProfile;
import com.example.phrs91.campusrecruitment.Utils;
import com.example.phrs91.campusrecruitment.companyAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link companyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class companyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private UserProfile mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Company> movieList = new ArrayList<>();
    private companyAdapter mAdapter;
    public companyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment companyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static companyFragment newInstance(UserProfile param1, String param2) {
        companyFragment fragment = new companyFragment();
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
        final View v= inflater.inflate(R.layout.fragment_company, container, false);
        RecyclerView recyclerView =(RecyclerView)v.findViewById(R.id.recycler_view);
        mAdapter = new companyAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(companyFragment.this.getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Company movie = movieList.get(position);
              //  // This effect can be seen in GMail appi
                Intent t=new Intent(v.getContext(),Registerforcompany.class);
                t.putExtra("company",movie);
                t.putExtra("user",mParam1);
                startActivity(t);
                //Toast.makeText(getApplicationContext(), movie.getTopi + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        loaddata();
        mAdapter.notifyDataSetChanged();
        return v;
    }
    private void loaddata()
    {
        final FirebaseDatabase database = Utils.getDatabase();
        DatabaseReference myref=database.getReference("company");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                HashMap<String,String> hs=(HashMap<String, String>) dataSnapshot.getValue();
                String ctc=""+hs.get("CTC");
                String name=hs.get("name");
                String branch[]=hs.get("branches").split(" ");
                HashSet<String> branchset=new HashSet<>();
                for(int i=0;i<branch.length;i++)
                {
                    branchset.add(branch[i]);
                }
                String course[]=hs.get("courses").split(" ");
                HashSet<String> courseset=new HashSet<>();
                for(int i=0;i<course.length;i++)
                {
                    courseset.add(course[i]);
                }
                String desc=hs.get("Desc");
                Date date=new Date();
                try {
                     date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(hs.get("date"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                String cpi=hs.get("cpi");
                Company n1=new Company(name,ctc,branchset,courseset,date,desc,Double.parseDouble(cpi));
                movieList.add(n1);
                //Toast.makeText(companyFragment.this.getActivity(),cpi+" "+date, Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();



                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.


                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.


                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
               // Toast.makeText(companyFragment.this.getActivity(), "Failed to load comments.",
                 //       Toast.LENGTH_SHORT).show();
            }
        };
        myref.addChildEventListener(childEventListener);
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
      /*  if (context instanceof OnFragmentInteractionListener) {
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
