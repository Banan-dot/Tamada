package com.urfu.Tamada.command;

import com.urfu.Tamada.command.ascii.Ascii;
import com.urfu.Tamada.command.ascii.AsciiLines;
import com.urfu.Tamada.command.permissionCommands.*;

public class CommandFactory {
    public Command getCommand(String commandName)
    {
        switch (commandName.toLowerCase())
        {
            case "анек":
                return new JokeCommand();
            case "mute":
                return new VoiceMute();
            case "unmute":
                new VoiceUnmute();
            case "rename":
                new Rename();
            case "kick":
                new KickMember();
            case "mute_t":
                new ChatMute();
            case "ascii":
                new Ascii();
            case "hd":
                new AsciiLines();
            default:
                return null;
        }
    }
}
