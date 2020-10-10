package com.urfu.Tamada.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.entities.Guild;
import java.util.Objects;

public class VoiceMute extends Command{
    @Override
    public void execute(CommandData commandData, GuildMessageReceivedEvent event) {
        var guild = event.getJDA().getGuildById(Objects.requireNonNull(event.getMember()).getGuild().getId());
        var mess = event.getMessage().getContentRaw();
        assert guild != null;
        var member = guild.getMemberById(Long.parseLong(mess.substring(8, mess.length() - 1)));
        assert member != null;
        System.out.println(member.getNickname());
        guild.mute(member, true).queue();
    }
}
