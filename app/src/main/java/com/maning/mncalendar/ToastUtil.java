package com.maning.mncalendar;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by maning on 2017/11/21.
 */

public class ToastUtil {
    private static Context context = null;
    private static Toast toast = null;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }


        toast.show();
    }
}
