import java.util.*;

public class RecommendCommand implements Command {

    private final String[] commandInfo;

    public RecommendCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }
    public void run() {

        final int userId = Integer.parseInt(commandInfo[0]);
        final Stream.streamType type = Stream.streamType.valueOf(commandInfo[2]);
        Database data = Database.getInstance();

        User user = data.getUsers().get(userId);
        if (user == null) return;

        Map<Integer, Stream> streams = data.getStreams();

        Map<Stream, Boolean> userStreams = user.getStreams();
        Queue<Stream> recommendedQueue = new PriorityQueue<>((s1, s2) ->
                (int) (s2.getNoOfListenings() - s1.getNoOfListenings()));

        streams.values().forEach(stream -> {
            if (stream.whatType() == type &&
                    user.getFavorites().contains(data.getStreamer(stream.getStreamerId())))
                recommendedQueue.add(stream);
        });

        List<Stream> recommendedStreams = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (recommendedQueue.peek() != null)
                recommendedStreams.add(recommendedQueue.poll());
        }

        userStreams.keySet().forEach(stream -> {
            if (userStreams.get(stream)) {
                recommendedStreams.remove(stream);
                if (recommendedQueue.peek() != null)
                    recommendedStreams.add(recommendedQueue.poll());
            }
        });
        if (!recommendedStreams.isEmpty())
            new JsonParser().createJsonOutputList(recommendedStreams);
    }
}
