package com.example.improvement.Service.Model;

public class DreamModel {

    String title;
    String des;
    byte[] image;
    String createDate;
    String endDate;

    public DreamModel(String title, String des, byte[] image, String createDate, String endDate) {
        this.title = title;
        this.des = des;
        this.image = image;
        this.createDate = createDate;
        this.endDate = endDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
