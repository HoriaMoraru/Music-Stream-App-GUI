public class CommandExecutor {

    public void execute(Command command) {
        if (command == null) {
            return;
        }
        command.run();
    }
}
