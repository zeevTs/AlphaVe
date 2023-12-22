package com.example.alphave;

public class Chat {
    private  String text;
    private static int num=0;

    public Chat(String text) {
        this.text = text;
        this.num++;
    }
    public Chat(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
