package com.onhand.rachel.nicegrape.model;

import com.google.gson.Gson;

import java.util.ArrayList;

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

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
