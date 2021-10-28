package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class CreateCharacter extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Random random = new Random();
        EmbedBuilder embed = new EmbedBuilder();
        String message = event.getMessage().getContentRaw();

        embed.setColor(Color.RED);
        embed.setTitle(event.getMember().getUser().getName() + "'s character");

        if (message.equalsIgnoreCase("!create")){
            String[] attributes = new String[]{"Str","Dex","Con","Int","Wis","Cha"};
            int[] attributeScores = new int[6];

            for (int j = 0; j < attributes.length; j++) {
                int rollsSum = 0;
                int minRoll = Integer.MAX_VALUE;
                for (int i = 0; i < 4; i++) {
                    int singleRoll = random.nextInt(6) + 1;
                    if (singleRoll < minRoll){
                        minRoll = singleRoll;
                    }
                    rollsSum += singleRoll;
                }
                attributeScores[j] = rollsSum - minRoll;
            }

            String results = "";
            for (int i = 0; i < attributes.length; i++){
                results += attributes[i] + ": " + attributeScores[i] + "\n";
            }

            embed.addField("Scores: ", results, false);

            event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}