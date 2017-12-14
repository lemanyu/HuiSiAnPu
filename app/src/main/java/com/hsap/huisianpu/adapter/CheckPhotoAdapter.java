package com.hsap.huisianpu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hsap.huisianpu.R;
import com.yanzhenjie.album.AlbumFile;

import java.util.List;

/**
 * 选择图片的adapter
 */

public class CheckPhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<AlbumFile> mList;
    private LayoutInflater inflater;
    public CheckPhotoAdapter(Context mContext, List<AlbumFile> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 9) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view=View.inflate(mContext, R.layout.item_check_photo,null);
        ImageView iv_check_photo = view.findViewById(R.id.iv_check_photo);
        if(position<mList.size()){
            Glide.with(mContext).load(mList.get(position).getPath()).into(iv_check_photo);
        }else {
            iv_check_photo.setImageResource(R.drawable.tianjia);
        }
        return view;
    }
}
