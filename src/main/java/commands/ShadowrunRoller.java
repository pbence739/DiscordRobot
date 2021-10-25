package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class ShadowrunRoller extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Random random = new Random();
        String message = event.getMessage().getContentRaw();
        EmbedBuilder embed = new EmbedBuilder();

        if (message.charAt(0) == '!'){
            String[] messageArr = message.split(" ");
            int dices = 0;
            int succes = 0;
            int fail = 0;
            String rolls = "";

                embed.setTitle(event.getMember().getUser().getName() + "'s roll");
                dices = Integer.parseInt(messageArr[1]);
                for (int i = 0; i < dices; i++){
                    int singelRoll = random.nextInt(6) + 1;

                    if (i == dices - 1){
                        rolls += "" + singelRoll;
                    } else {
                        rolls += "" + singelRoll + ", ";
                    }

                    if (singelRoll >= 5){
                        succes++;
                    } else if (singelRoll == 1){
                        fail++;
                    }
                }

                embed.setColor(Color.BLACK);
                embed.addField("Rolls: ", rolls, true);

                if (fail >= dices / 2 && succes == 0){
                    embed.addField("Result: ", "Critical glitch!", false);
                } else if (fail >= dices / 2){
                    embed.addField("Result: ", "Glitch!", false);
                } else {
                    embed.addField("Result: ", ""+succes, false);
                }

                event.getChannel().sendMessage(embed.build()).queue();
            }
    }
}
