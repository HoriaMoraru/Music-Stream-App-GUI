import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Database instance;
    private final Map<Integer, User> users;
    private final Map<Integer, Streamer> streamers;
    private final Map<Integer, Stream> streams;
    private final List<String> commands;

    private Database() {
        users = new HashMap<>();
        streamers = new HashMap<>();
        streams = new HashMap<>();
        commands = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Streamer> getStreamers() {
        return streamers;
    }

    public List<String> getCommands() {
        return commands;
    }

    public Map<Integer, Stream> getStreams() {
        return streams;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addStreamer(Streamer streamer) {
        streamers.put(streamer.getId(), streamer);
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void addStream(Stream stream) {
        streams.put(stream.getId(), stream);
    }

    public User getUser(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        return null;
    }

    public Streamer getStreamer(int id) {
        if (streamers.containsKey(id)) {
            return streamers.get(id);
        }
        return null;
    }

    public Stream getStream(int id) {
        if (streams.containsKey(id)) {
            return streams.get(id);
        }
        return null;
    }

    public void removeStream(int streamerId, int streamId) {
        Stream stream = getStream(streamId);
        if (stream == null) return;

        streams.remove(streamId);
        Streamer streamer = getStreamer(streamerId);
        if (streamer != null) {
            streamer.removeStream(stream);
        }
        users.values().stream().filter(user -> user.getStreams().containsKey(stream))
                               .forEach(user -> user.removeStream(stream));

    }

    public void listenStream(int userId, int streamId) {
        User user = getUser(userId);
        if (user != null) {
            Stream stream = getStream(streamId);
            if (stream == null) return;
            Streamer streamer = getStreamer(stream.getStreamerId());
            if (streamer == null) return;
            if (user.getStreams().containsKey(stream)) user.listenStream(stream);

            if (!user.getStreams().containsKey(stream)) {
                user.addListenedStream(stream);
                user.addFavorite(streamer);
            }

            stream.incrementNoOfStreams();
        }
    }

    public void flush() {
        users.clear();
        streamers.clear();
        streams.clear();
        commands.clear();
    }

    public boolean checkId(int id) {
        return users.containsKey(id) || streamers.containsKey(id);
    }

    public boolean checkStreamId(int id) {
        return streams.containsKey(id);
    }

    private boolean checkDBforUsers(String name) {
        return users.values().stream().anyMatch(user -> user.getName().equals(name));
    }

    private boolean checkDBforStreamers(String name) {
        return streamers.values().stream().anyMatch(streamer -> streamer.getName().equals(name));
    }

    public boolean checkDBforStreams(String name) {
        return streams.values().stream().anyMatch(stream -> stream.getName().equals(name));
    }

    public boolean existsDB(String name) {
        return checkDBforUsers(name) || checkDBforStreamers(name);
    }

    public int getStreamIdByName(String name) {
        return streams.values().stream().filter(stream -> stream.getName().equals(name))
                                      .findFirst().get().getId();
    }
}
