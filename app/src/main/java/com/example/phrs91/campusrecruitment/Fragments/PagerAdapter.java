package com.example.phrs91.campusrecruitment.Fragments;

import android.hardware.usb.UsbRequest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.phrs91.campusrecruitment.UserProfile;

/**
 * Created by user on 7/10/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{

    int noftabs;
    UserProfile user;

    public  PagerAdapter (FragmentManager fm, int numberofTabs, UserProfile user){
        super(fm);
        this.noftabs=numberofTabs;
        this.user=user;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Tab1 tab1=Tab1.newInstance(user,"  ");
                return tab1;
            case 1:
                Tab2 tab2= Tab2.newInstance(user," ");
                return tab2;
            case 2:
                Tab3 tab3=Tab3.newInstance(user," ");
                return tab3;
            case 3:
                Tab4 tab4=Tab4.newInstance(" "," ");
                return tab4;
            default:
                return null;
        }
    }

    public int getCount(){
        return noftabs;
    }
}
