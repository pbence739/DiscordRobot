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
            int[] rolls = new int[4];

            for (int j = 0; j < attributes.length; j++) {
                for (int i = 0; i < rolls.length; i++) {
                    rolls[i] = random.nextInt(6) + 1;
                }

                int maxRoll = rolls[0];
                int rollsSum = 0;
                for (int i = 0; i < rolls.length; i++) {
                    if (rolls[i] > maxRoll) {
                        maxRoll = rolls[i];
                    }
                    rollsSum += rolls[i];
                }
                attributeScores[j] = rollsSum - maxRoll;
            }

            String results = "";
            for (int i = 0; i < attributes.length; i++){
                results+= attributes[i] + ": " + attributeScores[i] + "\n";
            }

            embed.addField("Scores: ", results, false);

            event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}