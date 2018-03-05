package com.cembas.mobiledevelopertoolsforjira;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by cem on 11/21/17.
 */

public class GetPath {

    public static String getRealPathFromUri(Context context, Uri contentUri) {
            Cursor cursor;
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            cursor.moveToFirst();
            return cursor.getString(column_index);
    }
}
