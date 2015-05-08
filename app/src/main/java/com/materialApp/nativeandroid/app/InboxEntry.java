package com.materialApp.nativeandroid.app;

/**
 * Created by andre on 5/8/15.
 */
public class InboxEntry {
    public String userName;
    public String userState;
    public String userAvatar;
    public String lastMessage;
    public String lastMessageTime;


    public InboxEntry(String userName,String userState, String userAvatar, String lastMessage,String lastMessageTime){
        this.userName = userName;
        this.userState = userState;
        this.userAvatar = userAvatar;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

}
