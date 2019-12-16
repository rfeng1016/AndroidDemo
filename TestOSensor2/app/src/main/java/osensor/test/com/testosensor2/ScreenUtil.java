package osensor.test.com.testosensor2;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by F_Ruan on 16/8/12.
 */


public class ScreenUtil {

    //获取屏幕宽高
    public static int[] getScreenHW(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int[] HW = new int[]{width,height};
        return HW;
    }

    //获取屏幕的宽度
    public static int getScreenW(Context context) {
        return getScreenHW(context)[0];
    }

    //获取屏幕的高度
    public static int getScreenH(Context context) {
        return getScreenHW(context)[1];
    }
}


