package com.junie.callerblock.util;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.junie.callerblock.MainApplication;

/**
 * Created by niejun on 2018/1/29.
 */

public class ContactUtils {


    public static String getContactName(String number) {
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {
                ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER,
                ContactsContract.PhoneLookup.DISPLAY_NAME
        };
        Cursor cur = MainApplication.getApplication()
                .getContentResolver()
                .query(lookupUri, mPhoneNumberProjection, null,
                        null, null);
        if (cur != null) {
            try {
                if (cur.moveToFirst()) {
                    return cur.getString(
                            cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                }
            } finally {
                cur.close();
            }
        }
        return "";



    }



}
