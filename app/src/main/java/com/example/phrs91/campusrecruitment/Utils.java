package com.example.phrs91.campusrecruitment;

import com.google.firebase.database.FirebaseDatabase;

public class Utils {
    private static FirebaseDatabase mdatabase;
    private static boolean first=true;
    public static FirebaseDatabase getDatabase()
    {
        if(mdatabase==null)
        {
            mdatabase=FirebaseDatabase.getInstance();
            mdatabase.setPersistenceEnabled(true);
        }
        return mdatabase;
    }
}
