package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Calculate extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String[] message = event.getMessage().getContentRaw().split(" ");

        if (message[0].equalsIgnoreCase("!calculate")){
            if (message.length == 1){
                event.getChannel().sendMessage("This is how the bot wroks: !calculate [add/sub] [first-num] [second-num]").queue();
            } else if (message[1].equalsIgnoreCase("add")){
                int num1 = Integer.parseInt(message[2]);
                int num2 = Integer.parseInt(message[3]);
                event.getChannel().sendMessage("Here is the result: " + (num1 + num2)).queue();
            } else if (message[1].equalsIgnoreCase("sub")){
                int num1 = Integer.parseInt(message[2]);
                int num2 = Integer.parseInt(message[3]);
                event.getChannel().sendMessage("Here is the result: " + (num1 - num2)).queue();
            }
        }

    }
}
