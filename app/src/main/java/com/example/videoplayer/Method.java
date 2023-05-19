package com.example.videoplayer;

import java.io.File;

public class Method {

    public static void load_Directory_files(File directory) {

        File[] listfiles = directory.listFiles();
        if (listfiles != null && listfiles.length > 0) {
            for (int i = 0; i < listfiles.length; i++) {
                if (listfiles[i].isDirectory()) {
                    load_Directory_files(listfiles[i]);
                } else {
                    String name = listfiles[i].getName().toLowerCase();
                    for (String extension : Constant.videoExtensions) {
                        //check the type of file
                        if (name.endsWith(extension)) {
                            Constant.allMediaList.add(listfiles[i]);
                            //when we found file
                            break;
                        }
                    }
                }
            }
        }
    }
}
