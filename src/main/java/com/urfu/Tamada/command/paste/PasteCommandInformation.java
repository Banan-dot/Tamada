package com.urfu.Tamada.command.paste;

import com.urfu.Tamada.command.database.pastes.PastTable;
import com.urfu.Tamada.command.database.pastes.PastTable;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.regex.Pattern;

public class PasteCommandInformation {
    private final String pasteName;
    private final String pasteText;
    private static final PastTable PASTES_DB = new PastTable();

    public PasteCommandInformation(GuildMessageReceivedEvent event) {
        pasteName = parsePasteNameFromString(event.getMessage().getContentRaw());
        pasteText = parsePasteTextFromString(event.getMessage().getContentRaw(), pasteName);
    }

    public static PastTable getPastesDB() {
        return PASTES_DB;
    }

    private String parsePasteNameFromString(String commandString) {
        var pattern = Pattern.compile("(?<=[\"'])[^\"']+");
        var matcher = pattern.matcher(commandString);
        if (!matcher.find())
            return "";
        return matcher.group();
    }

    private String parsePasteTextFromString(String commandString, String pasteName) {
        var index = commandString.indexOf(pasteName) + pasteName.length() + 1;
        return commandString.substring(index);
    }

    public String getPasteText() {
        return "\"" + pasteText + "\"";
    }

    public String getPasteName() {
        return "\"" + pasteName + "\"";
    }
}
