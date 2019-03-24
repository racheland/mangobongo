package com.example.rachel.nicegrape.model;

import java.util.ArrayList;
import java.util.List;

public class Grape {
    private String title;
    private ArrayList<Sticker> stickerList;

    public Grape(String title, ArrayList<Sticker> stickerList) {
        this.title = title;
        this.stickerList = stickerList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Sticker> getStickerList() {
        return stickerList;
    }

    public void setStickerList(ArrayList<Sticker> stickerList) {
        this.stickerList = stickerList;
    }
}
