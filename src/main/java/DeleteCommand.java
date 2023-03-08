public class DeleteCommand implements Command {

    private final String[] commandInfo;

    public DeleteCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    public void run() {
        Database.getInstance().removeStream(Integer.parseInt(commandInfo[0]),
                                            Integer.parseInt(commandInfo[2]));
    }
}
