package com.urfu.Tamada.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import com.urfu.Tamada.Sender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MemeCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var parser = new JSONParser();
        var postLink = "";
        var title = "";
        var url = "";
        try {
            var urlMeme = new URL("https://meme-api.herokuapp.com/gimme");
            var memeStream = new InputStreamReader(urlMeme.openConnection().getInputStream());
            var bufferReader = new BufferedReader(memeStream);

            String lines;
            while ((lines = bufferReader.readLine()) != null) {
                var array = new JSONArray();
                array.add(parser.parse(lines));

                for (Object obj : array) {
                    var jsonObj = (JSONObject) obj;
                    postLink =  jsonObj.get("postLink").toString();
                    title = jsonObj.get("title").toString();
                    url = jsonObj.get("url").toString();
                }
            }
            bufferReader.close();
            var builder = new EmbedBuilder()
                    .setTitle(title, postLink)
                    .setImage(url);
            event.getChannel().sendMessage(builder.build()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, "мем");
    }
}
