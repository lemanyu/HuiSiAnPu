package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/16.
 */

public class TestBean {


    private List<FileNameBean> fileName;

    public List<FileNameBean> getFileName() {
        return fileName;
    }

    public void setFileName(List<FileNameBean> fileName) {
        this.fileName = fileName;
    }

    public static class FileNameBean {
        /**
         * name : 开发
         * Level : 0
         * isDirectory : true
         */

        private String name;
        private int Level;
        private boolean isDirectory;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public boolean isIsDirectory() {
            return isDirectory;
        }

        public void setIsDirectory(boolean isDirectory) {
            this.isDirectory = isDirectory;
        }
    }
}
