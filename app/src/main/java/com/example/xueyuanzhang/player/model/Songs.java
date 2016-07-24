package com.example.xueyuanzhang.player.model;

import java.io.Serializable;

/**
 * Created by xueyuanzhang on 16/7/24.
 */
public class Songs implements Serializable{
    private String name;
    private String path;
    private String composer;
    private String album;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}
