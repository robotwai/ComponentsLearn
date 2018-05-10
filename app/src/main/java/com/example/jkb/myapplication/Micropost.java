package com.example.jkb.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jkb on 18/5/9.
 */
@Entity(tableName = "micropost")
public class Micropost {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "created_at")
    private String created_at;
    @ColumnInfo(name = "picture")
    private String picture;
    @ColumnInfo(name = "user_name")
    private String user_name;
    @ColumnInfo(name = "icon")
    private String icon;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Micropost{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user_id=" + user_id +
                ", created_at='" + created_at + '\'' +
                ", picture='" + picture + '\'' +
                ", user_name='" + user_name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
