public class ListenCommand implements Command {

    private final String[] commandInfo;

    public ListenCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    public void run() {

        final int userId = Integer.parseInt(commandInfo[0]);
        final int streamId = Integer.parseInt(commandInfo[2]);
        Database.getInstance().listenStream(userId, streamId);
    }
}
