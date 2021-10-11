package com.yzk.baselib.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

public class ImageUtil {
    public static void setImage(SimpleDraweeView sdv, String url) {
        if (sdv == null || url == null) {
            return;
        }
        Uri uri = Uri.parse(url);
        sdv.setImageURI(uri);
    }

    public static void setFilePathImage(SimpleDraweeView sdv, String filePath) {
        if (sdv == null || filePath == null) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        sdv.setImageBitmap(bitmap);
    }

}
