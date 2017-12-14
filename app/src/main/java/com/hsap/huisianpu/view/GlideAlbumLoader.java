package com.hsap.huisianpu.view;

import android.webkit.URLUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.task.DefaultAlbumLoader;

import java.io.File;

/**
 * Created by zhao on 2017/12/11.
 */
public class GlideAlbumLoader implements AlbumLoader {

    @Override
    public void loadAlbumFile(ImageView imageView, AlbumFile albumFile, int viewWidth, int viewHeight) {
        int mediaType = albumFile.getMediaType();
        if (mediaType == AlbumFile.TYPE_IMAGE) {
            Glide.with(imageView.getContext())
                    .load(albumFile.getPath())
                    .into(imageView);
        } else if (mediaType == AlbumFile.TYPE_VIDEO) {
            DefaultAlbumLoader.getInstance()
                    .loadAlbumFile(imageView, albumFile, viewWidth, viewHeight);
        }
    }

    @Override
    public void loadImage(ImageView imageView, String imagePath, int width, int height) {
        if (URLUtil.isNetworkUrl(imagePath)) {
            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext())
                    .load(new File(imagePath))
                    .into(imageView);
        }
    }

}