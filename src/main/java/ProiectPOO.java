import java.io.File;

public class ProiectPOO {

    public static void main(String[] args) {

        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        final String source = "src/main/resources/";
        File streamersFile = new File(source + args[0]);
        File streamsFile = new File(source + args[1]);
        File usersFile = new File(source + args[2]);
        File commandsFile = new File(source + args[3]);
        File outputFile = new File(source + "/output.txt");

        CSVFileOperations csvFO = new CSVFileOperations();
        csvFO.readStreamers(streamersFile);
        csvFO.readStreams(streamsFile);
        csvFO.readUsers(usersFile);
        csvFO.readCommands(commandsFile);

        Database database = Database.getInstance();
        CommandExecutor commandExecutor = new CommandExecutor();
        for (String command : database.getCommands()) {
            String[] commandInfo = command.split(" ");
            Command.commandType commandType = Command.commandType.valueOf(commandInfo[1]);
            switch (commandType) {
                case ADD:
                    commandExecutor.execute(new AddCommand(commandInfo));
                    break;
                case LIST:
                    commandExecutor.execute(new ListCommand(commandInfo));
                    break;
                case DELETE:
                    commandExecutor.execute(new DeleteCommand(commandInfo));
                    break;
                case LISTEN:
                    commandExecutor.execute(new ListenCommand(commandInfo));
                    break;
                case RECOMMEND:
                    commandExecutor.execute(new RecommendCommand(commandInfo));
                    break;
                case SURPRISE:
                    commandExecutor.execute(new SurpriseCommand(commandInfo));
                    break;
                default:
                    throw new IllegalStateException("Unexpected command: " + commandType);
            }
        }
        csvFO.printOutput(outputFile);
        database.flush();
        outputFile.delete();
    }
}
