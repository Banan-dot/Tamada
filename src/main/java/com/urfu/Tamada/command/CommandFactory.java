package com.urfu.Tamada.command;

import com.urfu.Tamada.command.ascii.Ascii;
import com.urfu.Tamada.command.ascii.AsciiLines;
import com.urfu.Tamada.command.ascii.alias.Alias;
import com.urfu.Tamada.command.ascii.crocodile.Crocodile;
import com.urfu.Tamada.command.permissionCommands.*;

import java.util.HashMap;

public class CommandFactory {
    public Command getCommand(String commandName)
    {
        System.out.println(commandName);
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
            case "mm":
                return new WriteMembers();
            case "unmute_t":
                return new ChatUnmute();
            case "addsmile":
                return new AddNewEmoji();
            case "importemotes":
                return new ImportEmotes();
            case "delete":
                return new DeleteMessages();
            case "croc":
                return new Crocodile();
            case "alias":
                return new Alias();
            default:
                return new NoCommand();
        }
    }
}
