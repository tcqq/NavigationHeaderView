package com.tcqq.navigationheaderview;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.RestrictTo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by raphaelbussa on 13/01/17.
 */
public class ImageLoader {

    public static final int AVATAR = 1;
    public static final int HEADER = 2;

    private static ImageLoader instance;
    private ImageLoaderInterface imageLoaderInterface;


    /**
     * @param imageLoaderInterface set ImageLoaderInterface for image loading from Uri
     */
    public static void init(ImageLoaderInterface imageLoaderInterface) {
        if (instance == null) {
            instance = new ImageLoader();
        }
        instance.setImageLoaderInterface(imageLoaderInterface);
    }

    static void loadImage(Uri url, ImageView imageView, @Type int type) {
        if (instance == null) {
            throw new RuntimeException("ImageLoader must be implemented for use setAvatarUrl and setBackgroundUrl methods");
        }
        instance.imageLoaderInterface.loadImage(url, imageView, type);
    }

    private void setImageLoaderInterface(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoaderInterface = imageLoaderInterface;
    }

    public interface ImageLoaderInterface {
        /**
         * @param url       uri of image
         * @param imageView reference of ImageView
         * @param type      type of image to load AVATAR or HEADER
         */
        void loadImage(Uri url, ImageView imageView, @Type int type);
    }

    @RestrictTo(LIBRARY_GROUP)
    @IntDef({AVATAR, HEADER})
    @IntRange(from = 1, to = 2)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

}
