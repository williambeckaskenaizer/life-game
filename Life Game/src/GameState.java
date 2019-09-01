import java.util.ArrayList;

class GameState {
    private ArrayList<String> acceptedCommands = new ArrayList<String>();
    private ArrayList<String> playerPosessions = new ArrayList<String>();
    private ArrayList<String> chosenActions = new ArrayList<String>();
    private ArrayList<String> playerSkills = new ArrayList<String>();
    private ArrayList<String> devCommands = new ArrayList<String>();
    private String playerIs;
    private String partnerIs;
    private String gamePhase;
    private String knownInstrument;
    private String degreeArea;
    private String sportPlayed;

    private int love;
    private int hope;
    private int knowledge;
    private int strength;
    private int sadness;
    private int sickness;
    private int fulfillment;
    private int happiness;
    private int confidence;
    private int finances;

    private ArrayList<Boolean> flags = new ArrayList<Boolean>();
    private boolean isMarried;
    private boolean hasJob;
    private boolean highschoolSweetheart;
    private boolean playsInstrument;
    private boolean hasDegree;
    private boolean playsSport;

    private int actionsRemaining;

    public GameState() {
        initializeFlags();
        this.playerIs = "";
        this.gamePhase = "infant";
        this.knownInstrument = "";

        this.happiness = 0;
        this.love = 0;
        this.hope = 0;
        this.knowledge = 0;
        this.strength = 0;
        this.sadness = 0;
        this.sickness = 0;
        this.fulfillment = 0;
        this.confidence = 0;
        this.finances = 0;
        this.partnerIs = "";
        this.degreeArea = "";
        this.sportPlayed = "";
    }

    public void initDevCommands() {
        devCommands.add("kill");
        devCommands.add("infLove");
        devCommands.add("infKnowledge");
        devCommands.add("infFulfillment");
        devCommands.add("infFincances");
        devCommands.add("infStrength");
        devCommands.add("infConfidence");
        devCommands.add("infHappiness");
    }

    public ArrayList<String> getDevCommands() {
        return this.devCommands;
    }

    public void setSportPlayed(String s) {
        this.sportPlayed = s;
    }

    public String getSportPlayed() {
        return this.sportPlayed;
    }

    public String getDegreeArea() {
        return this.degreeArea;
    }

    public void setDegreeArea(String s) {
        this.degreeArea = s;
    }

    public String getPartnerIs() {
        return this.partnerIs;
    }

    public void setPartnerIs(String s) {
        this.partnerIs = s;
    }

    public void addToChosenActions(String s) {
        this.chosenActions.add(s);
    }

    public void learnedInstrument(String s) {
        this.knownInstrument = s;
    }

    public String getInstrument() {
        return this.knownInstrument;
    }

    public void displayPlayerSkills() {
        String skillString = "";
        for (int i = 0; i < playerSkills.size(); i++) {
            skillString = skillString + playerSkills.get(i) + " | ";
        }
        System.out.println(skillString);
    }

    public void addToSkills(String skill) {
        playerSkills.add(skill);
    }

    public ArrayList<String> getSkillsList() {
        return this.playerSkills;
    }

    public void displayChosenActions() {
        String chosnAcString = "";
        for (int i = 0; i < chosenActions.size(); i++) {
            chosnAcString = chosnAcString + chosenActions.get(i) + " | ";
        }
        System.out.println(chosnAcString);
    }

    public String chosenActionString() {
        String chosnAcString = "| ";
        for (int i = 0; i < chosenActions.size(); i++) {
            chosnAcString = chosnAcString + chosenActions.get(i) + " | ";
        }
        return chosnAcString;
    }

    public void initializeFlags() {
        flags.add(isMarried);
        flags.add(hasJob);
        flags.add(highschoolSweetheart);
        flags.add(playsInstrument);
        flags.add(hasDegree);
        flags.add(playsSport);
    }

    public void setFlag(String flag, boolean value) {
        if (flag.equalsIgnoreCase("highschoolSweetheart")) {
            this.highschoolSweetheart = value;
        } else if (flag.equalsIgnoreCase("isMarried")) {
            this.isMarried = value;
        } else if (flag.equalsIgnoreCase("playsInstrument")) {
            this.playsInstrument = value;
        } else if (flag.equalsIgnoreCase("hasJob")) {
            this.hasJob = value;
        } else if (flag.equalsIgnoreCase("hasDegree")) {
            this.hasDegree = value;
        } else if (flag.equalsIgnoreCase("playsSport")) {
            this.playsSport = value;
        } else {
            System.out.println("Flag not recognized. Unable to set flag.");
        }

    }

    public boolean getFlag(String flag) {

        if (flag.equalsIgnoreCase("highschoolSweetheart")) {
            return this.highschoolSweetheart;
        } else if (flag.equalsIgnoreCase("hasJob")) {
            return this.hasJob;
        } else if (flag.equalsIgnoreCase("isMarried")) {
            return this.isMarried;
        } else if (flag.equalsIgnoreCase("playsInstrument")) {
            return this.playsInstrument;
        } else if (flag.equalsIgnoreCase("playsSport")) {
            return this.playsSport;
        } else if (flag.equalsIgnoreCase("hasDegree")) {
            return this.hasDegree;
        } else {
            System.out.println("Flag not recognized. Unable to get flag.");
            return false;
        }

    }

    public int getActionsRemaining() {
        return this.actionsRemaining;
    }

    public ArrayList<String> getChosenActions() {
        return this.chosenActions;
    }

    public void displayActionsRemaining() {
        System.out.println("You have " + this.actionsRemaining + " actions remaining in this phase of life.");
    }

    public void subPhaseActionCounter() {
        this.actionsRemaining = this.actionsRemaining - 1;
    }

    public boolean statCheck(String stat, int reqStat) {
        if (getStat(stat) >= reqStat) {
            return true;
        } else {
            return false;
        }
    }

    public int getStat(String stat) {
        if (stat.equalsIgnoreCase("happiness")) {
            return this.happiness;
        }
        if (stat.equalsIgnoreCase("love")) {
            return this.love;
        }
        if (stat.equalsIgnoreCase("hope")) {
            return this.hope;
        }
        if (stat.equalsIgnoreCase("knowledge")) {
            return this.knowledge;
        }
        if (stat.equalsIgnoreCase("strength")) {
            return this.strength;
        }
        if (stat.equalsIgnoreCase("sadness")) {
            return this.sadness;
        }
        if (stat.equalsIgnoreCase("sickness")) {
            return this.sickness;
        }
        if (stat.equalsIgnoreCase("fulfillment")) {
            return this.fulfillment;
        }
        if (stat.equalsIgnoreCase("confidence")) {
            return this.confidence;
        } else {
            return 100000;
        }
    }

    public void addStat(String stat, int amount) {
        if (stat.equalsIgnoreCase("love")) {
            this.love = this.love + amount;
        }
        if (stat.equalsIgnoreCase("happiness")) {
            this.happiness = this.happiness + amount;
        }
        if (stat.equalsIgnoreCase("hope")) {
            this.hope = this.hope + amount;
        }
        if (stat.equalsIgnoreCase("knowledge")) {
            this.knowledge = this.knowledge + amount;
        }
        if (stat.equalsIgnoreCase("strength")) {
            this.strength = this.strength + amount;
        }
        if (stat.equalsIgnoreCase("sadness")) {
            this.sadness = this.sadness + amount;
        }
        if (stat.equalsIgnoreCase("sickness")) {
            this.sickness = this.sickness + amount;
        }
        if (stat.equalsIgnoreCase("fulfillment")) {
            this.fulfillment = this.fulfillment + amount;
        }
        if (stat.equalsIgnoreCase("confidence")) {
            this.confidence += amount;
        }
        if (stat.equalsIgnoreCase("finances")) {
            this.finances += amount;
        }
        if (amount <= 3) {
            System.out.println("+" + stat);
        } else if (amount >= 4 && amount < 10) {
            System.out.println("++" + stat);
        } else if (amount >= 10) {
            System.out.println("+++" + stat);
        }
    }

    public void subtractStat(String stat, int amount) {
        if (stat.equalsIgnoreCase("love")) {
            this.love = this.love - amount;
        }
        if (stat.equalsIgnoreCase("happiness")) {
            this.happiness = this.happiness - amount;
        }
        if (stat.equalsIgnoreCase("hope")) {
            this.hope = this.hope - amount;
        }
        if (stat.equalsIgnoreCase("knowledge")) {
            this.knowledge = this.knowledge - amount;
        }
        if (stat.equalsIgnoreCase("strength")) {
            this.strength = this.strength - amount;
        }
        if (stat.equalsIgnoreCase("sadness")) {
            this.sadness = this.sadness - amount;
        }
        if (stat.equalsIgnoreCase("sickness")) {
            this.sickness = this.sickness - amount;
        }
        if (stat.equalsIgnoreCase("fulfillment")) {
            this.fulfillment = this.fulfillment - amount;
        }
        if (stat.equalsIgnoreCase("confidence")) {
            this.confidence -= amount;
        }
        if (stat.equalsIgnoreCase("finances")) {
            this.finances -= amount;
        }
        if (amount <= 3) {
            System.out.println("-" + stat);
        } else if (amount >= 4 && amount < 10) {
            System.out.println("--" + stat);
        } else if (amount >= 10) {
            System.out.println("---" + stat);
        }
    }

    public ArrayList<String> getAcceptedCommands() {
        return this.acceptedCommands;
    }

    public ArrayList<String> getPlayerPosessions() {
        return this.playerPosessions;
    }

    public void addToPosessions(String s) {
        playerPosessions.add(s);
    }

    public void removeFromPosessions(String s) {
        playerPosessions.remove(s);
    }

    public void addToActions(String s) {
        if (!this.acceptedCommands.contains(s)) {
            this.acceptedCommands.add(s);
        }
    }

    public void removeFromActions(String s) {
        if (this.acceptedCommands.contains(s)) {
            this.acceptedCommands.remove(s);
        }
    }

    public GameState getCurrentState() {
        return this;
    }

    public String getPlayerIs() {
        return this.playerIs;
    }

    public void advanceGamePhase(String s) {
        this.gamePhase = s;
        if (s.equalsIgnoreCase("infant")) {
            this.actionsRemaining = 2;
        }
        if (s.equalsIgnoreCase("child")) {
            this.actionsRemaining = 4;
        }
        if (s.equalsIgnoreCase("teenager")) {
            this.actionsRemaining = 6;
        }
        if (s.equalsIgnoreCase("adult")) {
            this.actionsRemaining = 6;
        }
        if (s.equalsIgnoreCase("elder")) {
            this.actionsRemaining = 2;
        }
        if (s.equalsIgnoreCase("endgame")) {
            this.actionsRemaining = 1;
        }
        this.acceptedCommands.clear();
        this.chosenActions.clear();
        this.addToActions("exit");
    }

    public void clearAcceptedCommands() {
        this.acceptedCommands.clear();
    }

    public String getGamePhase() {
        return this.gamePhase;
    }

    public void displayAvailableCommands() {
        String avCmnds = "";
        for (int i = 0; i < acceptedCommands.size(); i++) {
            avCmnds = avCmnds + acceptedCommands.get(i) + " | ";
        }
        System.out.println("You may: | " + avCmnds);
    }

    public void displayPlayerPosessions() {
        String currentPosessions = "";
        for (int i = 0; i < playerPosessions.size(); i++) {
            currentPosessions = currentPosessions + playerPosessions.get(i) + " | ";
        }
        System.out.println("You have: | " + currentPosessions);
    }

    public void setPlayerIs(String s) {
        this.playerIs = s;
    }

    public void displayPlayerStatGraph() {
        // love hope knowledge strength sadness sickness fullfillment happiness
        String loveStat = "|#";
        String hopeStat = "|#";
        String knowledgeStat = "|#";
        String strengthStat = "|#";
        String sadnessStat = "|#";
        String sicknessStat = "|#";
        String fulfillmentStat = "|#";
        String happinessStat = "|#";
        String confidenceStat = "|#";
        String financeStat = "|#";
        for (int i = 0; i < this.love / 2; i++) {
            loveStat = loveStat + "#";
        }
        for (int i = 0; i < this.hope / 2; i++) {
            hopeStat = hopeStat + "#";
        }
        for (int i = 0; i < this.knowledge / 2; i++) {
            knowledgeStat = knowledgeStat + "#";
        }
        for (int i = 0; i < this.strength / 2; i++) {
            strengthStat = strengthStat + "#";
        }
        for (int i = 0; i < this.sadness / 2; i++) {
            sadnessStat = sadnessStat + "#";
        }
        for (int i = 0; i < this.sickness / 2; i++) {
            sicknessStat = sicknessStat + "#";
        }
        for (int i = 0; i < this.fulfillment / 2; i++) {
            fulfillmentStat = fulfillmentStat + "#";
        }
        for (int i = 0; i < this.happiness / 2; i++) {
            happinessStat = happinessStat + "#";
        }
        for (int i = 0; i < this.confidence / 2; i++) {
            confidenceStat = confidenceStat + "#";
        }
        for (int i = 0; i < this.finances / 2; i++) {
            financeStat = financeStat + "#";
        }

        loveStat += " love";
        hopeStat += " hope";
        knowledgeStat += " knowledge";
        strengthStat += " strength";
        sadnessStat += " sadness";
        sicknessStat += " sickness";
        fulfillmentStat += " fulfillment";
        happinessStat += " happiness";
        confidenceStat += " confidence";
        financeStat += " finances";

        System.out.println("\n");
        System.out.println(loveStat);
        // System.out.println(hopeStat);
        System.out.println(knowledgeStat);
        System.out.println(strengthStat);
        System.out.println(sadnessStat);
        System.out.println(sicknessStat);
        System.out.println(fulfillmentStat);
        System.out.println(happinessStat);
        System.out.println(confidenceStat);
        System.out.println(financeStat);
    }
}
