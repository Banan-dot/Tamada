package com.urfu.Tamada.command.permissionCommands;

public class GetIdFromString {
    public String getIdFromString(String message){
        var startIndex = message.indexOf("<@!");
        var endIndex = message.indexOf(">");
        return message.substring(startIndex + 3, endIndex);
    }
}
