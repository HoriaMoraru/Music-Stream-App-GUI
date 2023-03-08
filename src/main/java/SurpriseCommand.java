import java.util.*;

public class SurpriseCommand implements Command {

    private final String[] commandInfo;

    public SurpriseCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    public void run() {

        final int userId = Integer.parseInt(commandInfo[0]);
        final Stream.streamType type = Stream.streamType.valueOf(commandInfo[2]);
        Database data = Database.getInstance();

        User user = data.getUsers().get(userId);
        if (user == null) return;

        Map<Integer, Stream> streams = data.getStreams();

        Queue<Stream> surpriseQueue = new PriorityQueue<>((s1, s2) -> {
            int dateCompare = s2.getDateForCompare().compareTo(s1.getDateForCompare());
            if (dateCompare != 0) {
                return dateCompare;
            } else {
                return (int) (s2.getNoOfListenings() - s1.getNoOfListenings());
            } });

        streams.values().forEach(stream -> {
            if (stream.whatType() == type &&
                    !user.getFavorites().contains(data.getStreamer(stream.getStreamerId())))
                surpriseQueue.add(stream);
        });

        List<Stream> surpriseStreams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (surpriseQueue.peek() != null)
                surpriseStreams.add(surpriseQueue.poll());
        }

        if (!surpriseStreams.isEmpty())
            new JsonParser().createJsonOutputList(surpriseStreams);
    }
}
