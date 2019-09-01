import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;

public class GameLogic {

    // use this for repeated commands
    private static int numCommands;
    private static boolean initPhase = false;

    public static void makeDecision(GameState gs, String command) {
        // numCommands++;

        if (gs.getAcceptedCommands().contains(command)) {
            if (command.equalsIgnoreCase("exit")) {
                Life.exitGame();
            } else {
                // switch statement containing cases for different phases.
                switch (gs.getGamePhase()) {
                case "infant":
                    Life.gameStart();
                    startPhase(command, gs);
                    break;
                case "child":
                    childhoodPhase(command, gs);
                    break;
                case "teenager":
                    teenagePhase(command, gs);
                    break;
                case "adult":
                    adultPhase(command, gs);
                    break;
                case "elder":
                    elderPhase(command, gs);
                    break;
                case "endgame":
                    endgamePhase(command, gs);
                    break;
                default:
                    break;
                }

            }
        } else if (gs.getDevCommands().contains(command)) {
            switch (command) {
            case "kill":
                Life.deth();
                break;
            case "infLove":
                gs.addStat("love", 9999);
                break;
            case "infStrength":
                gs.addStat("strength", 9999);
                break;
            }
        }
    }

    public static void startPhase(String command, GameState gs) {
        if (!initPhase) {
            gs.advanceGamePhase("infant");
            gs.setPlayerIs("infant");
            System.out.println(
                    "You're an infant. There are a limited number of things your infant self may choose to focus on.");
            gs.addToActions("observe");
            gs.addToActions("play");
            gs.addToActions("crawl");
            initPhase = true;
        }

        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }

        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }

    }

    public static void childhoodPhase(String command, GameState gs) {
        if (!initPhase) {
            gs.setPlayerIs("child");
            System.out.println("You're a child now. You may choose to focus a wider variety of choices.");
            gs.addToActions("friendship");
            gs.addToActions("play");
            gs.addToActions("explore");
            if (gs.statCheck("strength", 10)) {
                gs.addToActions("sports");
            }
            if (gs.statCheck("knowledge", 10)) {
                gs.addToActions("study");
            }
            if (gs.statCheck("knowledge", 10) && gs.statCheck("strength", 10)) {
                gs.addToActions("skill");
            }
            if (gs.statCheck("happiness", 10)) {
                gs.addToActions("create");
            }
            initPhase = true;
        }
        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }
        if (gs.getActionsRemaining() == 0 && gs.getPlayerIs().equalsIgnoreCase("child")) {
            intermission(gs);
        }
        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }
    }

    public static void teenagePhase(String command, GameState gs) {

        if (!initPhase) {
            gs.setPlayerIs("teenager");
            System.out.println("You're a teenager. Your potential for growth is high.");
            gs.addToActions("friendship");
            gs.addToActions("play");
            gs.addToActions("romance");
            gs.addToActions("rebel");
            gs.addToActions("work");
            gs.addToActions("socialize");
            if (gs.statCheck("strength", 30)) {
                gs.addToActions("sports");
            }
            if (gs.statCheck("knowledge", 30)) {
                gs.addToActions("study");
            }
            if (gs.statCheck("knowledge", 15) && gs.statCheck("strength", 15)) {
                gs.addToActions("skill");
            }
            if (gs.statCheck("happiness", 10)) {
                gs.addToActions("express");
            }
            if (gs.statCheck("happiness", 15) && gs.statCheck("love", 10) && gs.statCheck("strength", 20)
                    && gs.statCheck("sadness", 1)) {
                gs.addToActions("activism");
            }
            initPhase = true;
        }
        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }
        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }
    }

    public static void adultPhase(String command, GameState gs) {
        if (!initPhase) {
            gs.setPlayerIs("adult");
            System.out.println("You're an adult. You've learned much, and gained valuable skills");
            System.out.println("Time to put those skills to use, and make something of yourself.");
            System.out.println("The choices you make here will determine where you end up.");
            gs.addToActions("friendship");
            gs.addToActions("play");
            gs.addToActions("romance");
            gs.addToActions("career");
            gs.addToActions("work");
            gs.addToActions("socialize");
            gs.addToActions("hobby");
            gs.addToActions("create");

            if (gs.statCheck("love", 35) && gs.getFlag("highschoolSweetheart")) {
                gs.addToActions("engagement");
            }
            if (gs.statCheck("strength", 70)) {
                gs.addToActions("pro-sport");
            }
            if (gs.statCheck("knowledge", 70)) {
                gs.addToActions("doctorate");
            }
            if (gs.statCheck("knowledge", 50) && !gs.getAcceptedCommands().contains("doctorate")) {
                gs.addToActions("degree");
            }
            if (gs.statCheck("knowledge", 50) && gs.statCheck("strength", 40)) {
                gs.addToActions("skill");
            }
            if (gs.statCheck("happiness", 40)) {
                gs.addToActions("express");
            }
            if (gs.statCheck("happiness", 15) && gs.statCheck("love", 10) && gs.statCheck("strength", 20)
                    && gs.statCheck("sadness", 1)) {
                gs.addToActions("activism");
            }
            initPhase = true;
        }
        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }
        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }
    }

    public static void elderPhase(String command, GameState gs) {
        if (!initPhase) {
            gs.setPlayerIs("elder");
            System.out.println(
                    "You're an elder now. You've accomplished much, and may now look back fondly on your experiences.");
            gs.addToActions("remember");
            gs.addToActions("relax");
            gs.addToActions("educate");
            initPhase = true;

        }
        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }
        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }
    }

    public static void endgamePhase(String command, GameState gs) {
        if (!initPhase) {
            gs.setPlayerIs("leaving soon.");
            System.out.println("It is time. You know it's time, but you can't help but feel a little sadness.");
            System.out.println("The experiences you've had, your hobbies, you'll miss them all.");
            if (gs.getFlag("isMarried")) {
                System.out.println("Most of all, you'll miss your partner." + getHeShe(gs.getPartnerIs())
                        + " made this life more beautiful than you could have imagined.");
            }
            gs.addToActions("sleep");
            initPhase = true;

        }
        if (!command.equalsIgnoreCase("start")) {
            gs.subPhaseActionCounter();
            gs.addToChosenActions(command);
            gs.removeFromActions(command);
        }
        if (gs.getActionsRemaining() == 0) {
            intermission(gs);
        }
    }

    public static void intermission(GameState gs) {
        Life.clearScreen();
        System.out.println("You have made it through your " + gs.getPlayerIs() + " phase.");
        System.out.println("Here are the things you chose to focus on:\n");
        gs.displayChosenActions();

        if (gs.getPlayerIs().equalsIgnoreCase("infant")) {
            if (gs.getChosenActions().contains("observe")) {
                System.out.println("\nYou desired to learn.");
                gs.addStat("knowledge", 10);
            }
            if (gs.getChosenActions().contains("play")) {
                System.out.println("\nYou sought enjoyment.");
                gs.addStat("happiness", 10);
            }
            if (gs.getChosenActions().contains("crawl")) {
                System.out.println("\nYou desired action.");
                gs.addStat("strength", 10);
            }
            gs.advanceGamePhase("child");
            initPhase = false;
            childhoodPhase("start", gs);
        } else if (gs.getPlayerIs().equalsIgnoreCase("child")) {
            // friendship, play, sports, study, skill, create
            if (gs.getChosenActions().contains("friendship")) {
                System.out.println("\nThe people in your life are important to you.");
                gs.addStat("happiness", 10);
                gs.addStat("love", 10);
            }
            if (gs.getChosenActions().contains("play")) {
                System.out.println("\nGames bring you joy. Strategy and fun.");
                gs.addStat("knowledge", 10);
                gs.addStat("happiness", 10);
            }
            if (gs.getChosenActions().contains("sports")) {
                if (!gs.getChosenActions().contains("study")) {
                    System.out.println("\nYou are the strongest and fastest around!");
                    System.out.println(
                            "You've certainly worked hard, but perhaps it may have been best to spend a little time with a few books.");
                    gs.addStat("strength", 20);
                    gs.addStat("happiness", 5);
                    gs.addStat("confidence", 10);
                    gs.subtractStat("knowledge", 5);
                }
                System.out.println("\nYour physical ability lends itself to high performance.");
                gs.addStat("strength", 15);
                gs.addStat("confidence", 5);
                gs.addStat("happiness", 5);

            }
            if (gs.getChosenActions().contains("study")) {
                if (!gs.getChosenActions().contains("friendship")) {
                    System.out.println("\nYou value knowledge over human connection.");
                    gs.addStat("knowledge", 20);
                    gs.subtractStat("happiness", 5);
                } else {
                    System.out.println("\nLearning fascinates you.");
                    gs.addStat("knowledge", 10);
                    gs.addStat("happiness", 5);
                }
            }
            if (gs.getChosenActions().contains("create")) {
                System.out.println("\nYou experienced strong emotions, and they drove you to express yourself.");
                gs.addStat("happiness", 15);
                gs.addStat("fulfillment", 2);
            }
            if (gs.getChosenActions().contains("explore")) {
                System.out.println("\nYou were interested in your surroundings. You took risks, learned, and grew.");
                gs.addStat("knowledge", 5);
                gs.addStat("strength", 5);
                gs.addStat("happiness", 5);
                gs.addStat("fulfillment", 1);
            }
            if (gs.getChosenActions().contains("skill")) {
                System.out.println("\nYour intellect and physical ability allowed you to pick up a skill!");
                System.out.println("You picked up: " + randomSkill(gs));
                gs.addStat("confidence", 5);
            }
            gs.advanceGamePhase("teenager");
            initPhase = false;
            teenagePhase("start", gs);
        } else if (gs.getPlayerIs().equalsIgnoreCase("teenager")) {
            // friendship play romance rebel socialize sports study skill express activism
            if (gs.getChosenActions().contains("friendship")) {
                System.out.println(
                        "\nYour friends are important to you and your identity. Life wouldn't be the same without them.");
                gs.addStat("love", 15);
                gs.addStat("happiness", 15);
            }
            if (gs.getChosenActions().contains("play")) {
                System.out.println("\nGames have become a passion. You hold yourself to a high standard of play.");
                gs.addStat("knowledge", 10);
                gs.addStat("happiness", 15);
                gs.addStat("fullfillment", 4);
            }
            if (gs.getChosenActions().contains("romance")) {
                System.out.println("You've taken an interest in companionship. Being more than friends.");

                if (!gs.statCheck("love", 10)) {
                    System.out.println("You grew a lot, but unfortunately, love was still a bit of a foreign concept.");
                    System.out.println("Things ended poorly.");
                    gs.addStat("sadness", 10);
                    gs.addStat("strength", 5);
                    if (gs.getChosenActions().contains("friendship")) {
                        System.out.println("Thankfully, your friends were there, every step of the way.");
                        gs.addStat("love", 15);
                        gs.addStat("fulfillment", 3);
                    }
                } else {
                    System.out.println(
                            "Someone you can tell anything, spend any amount of time with, and have confidence.");
                    System.out.println("You don't ever want this to end.");
                    gs.setFlag("highschoolSweetheart", true);
                    gs.addStat("love", 20);
                    gs.addStat("fulfillment", 10);
                    gs.addStat("confidence", 10);
                }
            }
            if (gs.getChosenActions().contains("rebel")) {
                System.out.println(
                        "Something about conformity got under your skin. You sought out to be different. Your own leader.");
                if (gs.getChosenActions().contains("friendship")) {
                    System.out.println(
                            "So much so that you set an example. Your group of friends followed in your footsteps.");
                    gs.addStat("fulfillment", 3);
                    gs.addStat("happiness", 10);
                } else {
                    System.out.println("Your choices ostricized you.");
                    gs.addStat("strength", 5);
                    gs.addStat("sadness", 5);
                }
            }
            if (gs.getChosenActions().contains("socialize")) {
                System.out.println("You put yourself out there.");
                gs.addStat("happiness", 5);
                gs.addStat("confidence", 15);
            }
            if (gs.getChosenActions().contains("sports")) {
                System.out.println("You pursue a lifelong love of sports. You're undeniably gifted.");
                gs.addStat("strength", 30);
                gs.addStat("confidence", 15);
                gs.addStat("happiness", 10);
                if (!gs.getChosenActions().contains("study")) {
                    System.out.println("You rely heavily on your physical ability.");
                    System.out.println("To be fair, you've broken national records.");
                    System.out.println("People come to see you in action.");
                    System.out.println("Your studies have taken a hit due to your time spend out in the field.");
                    gs.addStat("strength", 40);
                    gs.addStat("confidence", 20);
                    gs.addStat("happiness", 15);
                    gs.subtractStat("knowledge", 10);
                }
            }
            if (gs.getChosenActions().contains("study")) {
                System.out.println("Your studies are important to you.");
                System.out.println("The more you know about the world, the more you realize there is to learn.");
                System.out.println("This feeling fills you with a sense of excitement.");
                gs.addStat("knowledge", 30);
                gs.addStat("confidence", 10);
                if (!gs.getChosenActions().contains("friendship")) {
                    System.out.println("Studying and learning were always more important to you than frienship.");
                    System.out.println(
                            "You test well, and consistently receieve the highest scores on all your endeavors.");
                    System.out.println("You gain great knowledge of the academic world, but little of the social one.");
                    gs.addStat("knowledge", 40);
                    gs.addStat("confidence", 15);
                    gs.subtractStat("love", 10);
                    gs.subtractStat("strength", 10);
                }
            }
            if (gs.getChosenActions().contains("skill")) {
                randomSkill(gs);
            }
            if (gs.getChosenActions().contains("express")) {
                System.out.println("Your understanding of emotion is deep, yet you seek more.");
                System.out.println("You express your view of the human experience through your art.");
                if (gs.getSkillsList().contains("painting")) {
                    System.out.println(
                            "Your paintings evoke emotions in others that they sometimes do not fully understand");
                    System.out.println("You are talented.");
                    gs.addStat("happiness", 20);
                    gs.addStat("sadness", 5);
                    gs.addStat("fulfillment", 20);
                } else if (gs.getSkillsList().contains("singing")) {
                    System.out.println("Your voice is fine tuned to hit the most beautiful notes.");
                    System.out.println("Others come to hear you perform.");
                    gs.addStat("happiness", 20);
                    gs.addStat("fullfillment", 15);
                    gs.addStat("confidence", 15);
                } else {
                    gs.addStat("happiness", 15);
                    gs.addStat("fulfillment", 5);
                }
            }
            if (gs.getChosenActions().contains("activism")) {
                System.out.println(
                        "You see the many injustices of the world, but unlike the others, are not content to sit back and do nothing.");
                System.out.println("You involve yourself in making the world a better place.");
                if (gs.getChosenActions().contains("rebel")) {
                    System.out.println("The hardest choices require the strongest wills.");
                    System.out.println("Due to your rebellious streak, you participate in guerilla activism.");
                    gs.addStat("fulfillment", 10);
                    gs.addStat("strength", 15);
                    gs.addStat("confidence", 10);
                } else {
                    gs.addStat("fulfillment", 5);
                    gs.addStat("confidence", 10);
                    gs.addStat("love", 10);
                }
            }
            if (gs.getChosenActions().contains("work")) {
                System.out.println("You acquire a part-time job for some disposable income.");
                gs.addStat("finances", 10);
                gs.addStat("sadness", 5);
            }
            // friendship play romance rebel socialize sports study skill express activism
            gs.advanceGamePhase("adult");
            initPhase = false;
            adultPhase("start", gs);
        } else if (gs.getPlayerIs().equalsIgnoreCase("adult")) {
            if (gs.getChosenActions().contains("friendship")) {
                System.out.println(
                        "Even after everything life has thrown at you, your friends still mean the world to you.");
                System.out.println("They give life meaning.");
                gs.addStat("love", 30);
                gs.addStat("happiness", 20);
                gs.addStat("fulfillment", 10);
            }
            if (gs.getChosenActions().contains("play")) {
                System.out.println("Games are still a passion. Whenever you have a free moment, you're playing.");
                System.out.println("This may take time away from more fulfilling activities, but you don't mind.");
                gs.addStat("happiness", 30);
                gs.subtractStat("fulfillment", 10);
            }
            if (gs.getChosenActions().contains("romance")) {
                if (gs.getFlag("highschoolSweetheart")) {
                    String partnerSex = determineSex(gs);
                    System.out.println("From the moment you met, you knew.");
                    System.out.println(getHeShe(partnerSex) + " is perfect. Everything about " + getHimHer(partnerSex)
                            + " just feels right.");
                    gs.addStat("love", 100);
                    gs.addStat("happiness", 60);
                    gs.setFlag("isMarried", true);
                    if (gs.getFlag("playsInstrument")) {
                        System.out.println("The proposal was perfect. You wrote " + getHimHer(partnerSex)
                                + " a song on the " + gs.getInstrument() + ", and performed it under the moonlight.");
                        gs.addStat("love", 120);
                        gs.addStat("confidence", 50);
                        gs.addStat("happiness", 80);
                    }
                }
            }

            if (gs.getChosenActions().contains("career")) {
                if (gs.getChosenActions().contains("degree")) {
                    System.out.println("Your passion for learning carried you through a degree in "
                            + generateCareer(gs, "degree"));
                    gs.addStat("knowledge", 100);
                    gs.addStat("fulfillment", 30);
                    gs.addStat("confidence", 30);
                }
                if (gs.getChosenActions().contains("pro-sport")) {
                    System.out.println(
                            "Your intrinsic physical ability, and motivation to work hard did not go unnoticed.");
                    System.out.println(
                            "It wasn't long before you were scouted by the pro leagues. You signed a contract to join a"
                                    + generateCareer(gs, "pro-sport") + " team.");
                    gs.addStat("strength", 100);
                    gs.addStat("fulfillment", 30);
                    gs.addStat("confidence", 30);
                    gs.addStat("finances", 100);
                }
                if (gs.getChosenActions().contains("work")) {
                    System.out.println("You took great pride in your work, always making sure to stand out.");
                    gs.addStat("finances", 50);
                }
                if (gs.getChosenActions().contains("socialize")) {
                    System.out.println(
                            "You always enjoyed interaction with others. People fascinate you, and you desire their company.");
                    System.out.println("You're a good speaker, and people enjoy talking with you.");
                    gs.addStat("confidence", 50);
                    gs.addStat("happiness", 30);

                }
                if (gs.getChosenActions().contains("hobby")) {
                    System.out.println(
                            "You realized it's important to have things you enjoy, things that are unique to your alone time");
                    System.out.println("You picked up " + randomSkill(gs));
                    gs.addStat("fulfillment", 30);
                    gs.addStat("happiness", 30);
                    gs.addStat("confidence", 30);
                    gs.subtractStat("finances", 5);
                }
                if (gs.getChosenActions().contains("create")) {
                    System.out.println("Your art has become more than a hobby now.");
                    System.out.println("You hang your paintings in your home, and have even sold a few to others.");
                    gs.addStat("happiness", 40);
                    gs.addStat("fulfillment", 40);
                    gs.addStat("confidence", 40);
                }
                if (gs.getChosenActions().contains("engagement") && !gs.getFlag("highschoolSweetheart")) {
                    String partnerSex = determineSex(gs);
                    System.out.println("You were a bit of a late bloomer, but you've finally found the one.");
                    System.out.println(getHeShe(partnerSex) + " makes you truly happy.");
                    System.out.println("You got engaged, and married!");
                    gs.addStat("happiness", 50);
                    gs.addStat("love", 80);
                    gs.addStat("fulfillment", 20);
                    gs.setFlag("isMarried", true);
                }
                if (gs.getChosenActions().contains("doctorate")) {
                    System.out.println("Your intellect, work ethic, and curiosity drove you to achieve a doctorate in "
                            + generateCareer(gs, "doctorate"));
                    System.out.println("You dedicate your life to doing what you love.");
                    gs.addStat("knowledge", 150);
                    gs.addStat("fulfillment", 80);
                    gs.addStat("confidence", 80);
                    gs.addStat("happiness", 60);
                }
                if (gs.getChosenActions().contains("activism")) {
                    System.out.println(
                            "You never stopped seeing the injustice in the world, and devoted a part of your life to fighting it.");
                    System.out.println(
                            "You donate whenever you can, you go to rallies, host events, and lead the community towards a more just world.");
                    System.out.println(
                            "The experience is humbling, but also saddening, knowing that no matter how hard you work, there will always be those in need.");
                    gs.addStat("fulfillment", 90);
                    gs.addStat("happiness", 100);
                    gs.addStat("sadness", 20);
                    gs.addStat("confidence", 40);
                }
                gs.advanceGamePhase("elder");
                initPhase = false;
                elderPhase("start", gs);
            }
        } else if (gs.getPlayerIs().equalsIgnoreCase("elder")) {
            if (gs.getChosenActions().contains("remember")) {
                System.out.println("You and your partner look back fondly on your lives.");
                System.out.println(getHeShe(gs.getPartnerIs()) + " looks at you, and smiles.");
                if (gs.getChosenActions().contains("relax")) {
                    System.out.println(
                            "You've done a lot in this life. You've paid your dues, and contributed to society.");
                    System.out.println("You take it easy.");
                    gs.addStat("happiness", 50);
                    gs.addStat("fulfillment", 50);
                }
                if (gs.getChosenActions().contains("educate")) {
                    System.out.println("You share with the youth your valuable experiences and stories.");
                    System.out.println("They are the future");
                    gs.addStat("happiness", 80);
                }
            }
            gs.advanceGamePhase("endgame");
            initPhase = false;
            endgamePhase("start", gs);
        } else if (gs.getPlayerIs().equalsIgnoreCase("leaving soon.")) {
            System.out.println("You may rest now.");
            endPhase(gs);
        } else {
            System.out.println("phase not found.");
        }
        // friendship, play, romance, career, work, socialize, hobby, create,
        // engagement, pro-sport, degree, doctorate, skill, express, activism
    }

    public static void endPhase(GameState gs) {
        System.out.println("Congratulations!");
        System.out.println("You lived.");
        System.out.println("Here are your accomplishments.");
        System.out.println("You learned: " + gs.getSkillsList());
        if (gs.getFlag("hasDegree")) {
            System.out.println("You got your degree in:" + gs.getDegreeArea());
        }
        if (gs.getFlag("isMarried")) {
            System.out.println("You got married!");
        } else {
            System.out.println("You did not get married!");
        }
        if (gs.getFlag("playsSport")) {
            System.out.println("You were excellent at" + gs.getSportPlayed());
        } else {
            System.out.println("You did not play sports!");
        }
        System.out.println("Here are your stats: ");
        gs.displayPlayerStatGraph();
        Life.exitGame();
    }

    public static String generateCareer(GameState gs, String area) {
        ArrayList<String> possibleCareers = new ArrayList<String>();
        String career = "being lazy";
        if (area.equalsIgnoreCase("degree")) {
            possibleCareers.add(" psychology");
            possibleCareers.add(" business");
            possibleCareers.add(" computer science");
            possibleCareers.add(" biology");
            possibleCareers.add(" mathematics");
            possibleCareers.add(" physics");
            possibleCareers.add(" english");
            possibleCareers.add(" engineering");
            Random r = new Random();
            int low = 0;
            int high = possibleCareers.size();
            int result = r.nextInt(high - low) + low;
            career = possibleCareers.get(result);
            gs.setDegreeArea(career);
            possibleCareers.clear();
        }
        if (area.equalsIgnoreCase("pro-sport")) {
            possibleCareers.add(" basketball");
            possibleCareers.add(" baseball");
            possibleCareers.add(" tennis");
            possibleCareers.add(" swimming");
            possibleCareers.add(" soccer");
            possibleCareers.add(" football");
            Random r = new Random();
            int low = 0;
            int high = possibleCareers.size();
            int result = r.nextInt(high - low) + low;
            career = possibleCareers.get(result);
            gs.setSportPlayed(career);
            gs.setFlag("playsSport", true);
            possibleCareers.clear();
        }
        return career;
    }

    public static String randomSkill(GameState gs) {
        ArrayList<String> skillsList = new ArrayList<String>();
        ArrayList<String> instrumentList = new ArrayList<String>();
        String returnedSkill = "";
        if (gs.getPlayerIs().equalsIgnoreCase("child")) {
            skillsList.add("painting");
            skillsList.add("singing");
            skillsList.add("chess");
            skillsList.add("playing the");
        }
        if (gs.getPlayerIs().equalsIgnoreCase("teenager")) {
            skillsList.add("cooking");
            skillsList.add("playing the");
            skillsList.add("writing");
        }
        if (gs.getPlayerIs().equalsIgnoreCase("adult")) {
            skillsList.add("woodworking");
            skillsList.add("cycling");
            skillsList.add("jewelry making");
            skillsList.add("jogging");
            skillsList.add("baking");
        }
        Random r = new Random();
        int low = 0;
        int high = skillsList.size();
        int result = r.nextInt(high - low) + low;
        returnedSkill = skillsList.get(result);
        String instrument;
        if (returnedSkill.equals("playing the") && !gs.getSkillsList().contains("playing the")) {
            instrumentList.add(" guitar");
            instrumentList.add(" piano");
            instrumentList.add(" flute");
            instrumentList.add(" ukulele");
            instrumentList.add(" saxophone");
            instrumentList.add(" trumpet");
            instrumentList.add(" banjo");
            r = new Random();
            low = 0;
            high = instrumentList.size();
            result = r.nextInt(high - low) + low;
            instrument = instrumentList.get(result);
            skillsList.add(skillsList.indexOf("playing the"), instrument);
            returnedSkill = "playing the" + instrument;
            gs.setFlag("playsInstrument", true);
            gs.learnedInstrument(instrument);
        }
        gs.addToSkills(returnedSkill);
        return returnedSkill;
    }

    public static String determineSex(GameState gs) {
        String sex;
        Random r = new Random();
        int low = 0;
        int high = 10;
        int result = r.nextInt(high - low) + low;
        if (result >= 5) {
            gs.setPartnerIs("male");
            return sex = "male";
        } else {
            gs.setPartnerIs("female");
            return sex = "female";
        }
    }

    public static String getHeShe(String s) {
        if (s.equalsIgnoreCase("male")) {
            return "he";
        } else {
            return "she";
        }
    }

    public static String getHimHer(String s) {
        if (s.equalsIgnoreCase("male")) {
            return "him";
        } else {
            return "her";
        }
    }

}