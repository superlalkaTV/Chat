package model;

import java.util.Date;

public class Message {
    private String message;
    private Date date;
    private String nickname;

    public Message(String nickname, Date date, String message) {
        this.message = message;
        this.date = date;
        this.nickname = nickname;
    }

    public Message(String nickname, String message) {
        this.message = message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "("+ date +") " + nickname +": " + message;
    }
}
