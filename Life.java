
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Life {
    public static boolean continueGame = true;
    public static GameState gs = new GameState();

    public static void main(String args[]) {
        gs.addToActions("exit");
        gs.addToActions("start");
        printTitle();
        System.out.println(
                "\nA text based game about existing, by William Beck-Askenaizer.\n\tType Start to begin, and Exit at any time to exit.");
        while (continueGame) {
            getNextAction();
        }
    }

    public static String getNextAction() {

        Scanner userIn = new Scanner(System.in);
        String action = userIn.next().toLowerCase();
        if (continueGame) {
            if (gs.getAcceptedCommands().contains(action) || gs.getDevCommands().contains(action)) {
                System.out.println("\n\n");
                GameLogic.makeDecision(gs.getCurrentState(), action);
            } else {
                System.out.println("Not right now.");
            }
        }
        System.out.println("\n\n");
        System.out.println("Actions you'll focus on: " + gs.chosenActionString());
        System.out.println("You are: " + gs.getPlayerIs());
        gs.displayActionsRemaining();
        gs.displayAvailableCommands();
        if (gs.getSkillsList().size() > 0) {
            System.out.println("You have a talent for: " + gs.getSkillsList());
        }
        System.out.println("----------------------------------------------------");
        return action;
    }

    public static void gameStart() {
        gs.initDevCommands();
        System.out.println("This is a game about life.");
        System.out.println("Lead a life to its conclusion.");
        System.out.println("Choose from, and type out available commands.");
        System.out.println("Each phase will provide you with a limited number of actions to focus on.");
        System.out.println("Depending on your choices, you can end up in different places.");
        System.out.println("Let's start at the beginning.\n\n");
        gs.initializeFlags();
    }

    public static void exitGame() {
        // we're in the Endgame now.
        System.out.println("Exit game?(yes/no)");
        Scanner userIn = new Scanner(System.in);
        String decision = userIn.nextLine();
        if (decision.equalsIgnoreCase("yes")) {
            continueGame = false;
            System.out.println("Goodnight.");
            userIn.close();
            System.exit(0);
        } else {
            System.out.println("Ok.\n");
        }
    }

    public static void deth() {
        try (Scanner input = new Scanner(new File("deth.txt"))) {
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("no file :(");
        }
        System.exit(0);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printTitle() {
        System.out.println("\t      ___      ___   _______  _______         ");
        System.out.println("\t     |   |    |   | |       ||       |        ");
        System.out.println("\t     |   |    |   | |    ___||    ___|        ");
        System.out.println("\t     |   |    |   | |   |___ |   |___         ");
        System.out.println("\t     |   |___ |   | |    ___||    ___|        ");
        System.out.println("\t     |       ||   | |   |    |   |___         ");
        System.out.println("\t     |_______||___| |___|    |_______| o\n");
    }
}