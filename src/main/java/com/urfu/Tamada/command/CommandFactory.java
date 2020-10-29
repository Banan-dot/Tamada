package com.urfu.Tamada.command;

import com.urfu.Tamada.command.ascii.Ascii;
import com.urfu.Tamada.command.ascii.AsciiLines;
import com.urfu.Tamada.command.permissionCommands.*;

import java.util.HashMap;

public class CommandFactory {
    public Command getCommand(String commandName)
    {
        switch (commandName.toLowerCase())
        {
            case "anek":
                return new JokeCommand();
            case "mute":
                return new VoiceMute();
            case "unmute":
                return new VoiceUnmute();
            case "rename":
                return new Rename();
            case "kick":
                return new KickMember();
            case "mute_t":
                return new ChatMute();
            case "ascii":
                return new Ascii();
            case "hd":
                return new AsciiLines();
            default:
                return new NoCommand();
        }
    }
}
