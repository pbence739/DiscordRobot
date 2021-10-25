import commands.*;
import events.HelloEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class BotMain {

    public static void main(String args[]) throws Exception{
        JDA jda = JDABuilder.createDefault("OTAyMDkzNDAyNTg4NDY3MjMx.YXZZ8w.gEyS5F3tEi00CHpwvJufLURk9EI").build();

        jda.addEventListener(new Help());
        jda.addEventListener(new HelloEvents());
        jda.addEventListener(new Calculate());
        jda.addEventListener(new RollDice());
        jda.addEventListener(new ShadowrunRoller());
        jda.addEventListener(new CreateCharacter());
    }
}
