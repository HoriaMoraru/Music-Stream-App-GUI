import java.util.List;
import java.util.Map;

public class ListCommand implements Command {

    private final String[] commandInfo;

    public ListCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;

    }
    public void run() {
        JsonParser parser = new JsonParser();
        final int id = Integer.parseInt(commandInfo[0]);
        List<Stream> streams = null;
        Map<Stream, Boolean> streamsMap = null;
        if (check(id) == 1) {
            streams = Database.getInstance().getStreamers()
                     .get(id)
                     .getStreams();
        }
        else if (check(id) == 2) {
            streamsMap = Database.getInstance().getUsers()
                        .get(id)
                        .getStreams();
        }
        else if (check(id) == 0)
            return;

        if (streams != null) {
            parser.createJsonOutputList(streams);
        }
        if (streamsMap != null) {
            parser.createJsonOutputMap(streamsMap);
        }
    }
    private int check(int id) {
        Database data = Database.getInstance();
        if (data.getStreamers().containsKey(id)) {
            return 1;
        }
        if (data.getUsers().containsKey(id)) {
            return 2;
        }
        return 0;
    }
}
