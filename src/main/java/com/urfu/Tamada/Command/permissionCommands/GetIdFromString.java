package com.urfu.Tamada.Command.permissionCommands;

public class GetIdFromString {
    public String getIdFromString(String message){
        var startIndex = message.indexOf("<@!");
        var endIndex = message.indexOf(">"); //  <@!691348706741452830>
        return message.substring(startIndex + 3, endIndex);
    }
}
