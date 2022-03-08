package com.example.instagram;

/* loaded from: classes.dex */
public class FindFriends {
    public String fullname;
    public String profileimage;
    public String status;

    public FindFriends() {
    }

    public FindFriends(String profileimage, String fullname, String status) {
        this.profileimage = profileimage;
        this.fullname = fullname;
        this.status = status;
    }

    public String getProfileimage() {
        return this.profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
