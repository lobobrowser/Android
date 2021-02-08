package org.lobobrowser.ssl;

import org.lobobrowser.R;
import org.lobobrowser.utils.DrawableUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class SslIcon {

    public static Drawable createSslDrawableForState(Context context, SslState sslState) {
        switch (sslState) {
            case VALID:
                Bitmap valid = DrawableUtils.createImageInsetInRoundedSquare(context, R.drawable.ic_secured);
                return new BitmapDrawable(context.getResources(), valid);
            case INVALID:
                Bitmap invalid = DrawableUtils.createImageInsetInRoundedSquare(context, R.drawable.ic_unsecured);
                return new BitmapDrawable(context.getResources(), invalid);
            case NONE:
            default:
                return null;
        }
    }

}
