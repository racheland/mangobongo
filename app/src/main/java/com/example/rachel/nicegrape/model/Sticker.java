package com.example.rachel.nicegrape.model;

import java.util.Date;

public class Sticker {
    private boolean activate;
    private String content;
    private Date createDate;

    public Sticker(boolean activate, String content, Date createDate) {
        this.activate = activate;
        this.content = content;
        this.createDate = createDate;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
