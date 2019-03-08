package com.example.rachel.nicegrape.model;

import java.util.List;

public class Grape {
    private String title;
    private List<Sticker> stickerList;

    public Grape(String title, List<Sticker> stickerList) {
        this.title = title;
        this.stickerList = stickerList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(List<Sticker> stickerList) {
        this.stickerList = stickerList;
    }
}
