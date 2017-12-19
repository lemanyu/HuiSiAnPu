package com.hsap.huisianpu.expand;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zhao on 2017/12/19.
 */

public class Expand1Item implements MultiItemEntity {
    private String title;

    public Expand1Item(String title){
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
