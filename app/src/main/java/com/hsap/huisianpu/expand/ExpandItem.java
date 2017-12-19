package com.hsap.huisianpu.expand;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zhao on 2017/12/19.
 */

public class ExpandItem extends AbstractExpandableItem<Expand1Item> implements MultiItemEntity {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public ExpandItem(String title){
        this.title = title;
    }
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
