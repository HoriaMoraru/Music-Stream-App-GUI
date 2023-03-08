import java.util.*;

public class User implements GUIUser {

    private int id;
    private String name;
    private Map<Stream, Boolean> streams;
    private List<Streamer> favorites;

    public User() {
        this.streams = new HashMap<>();
    }

    public User(String name) {
        this();
        this.name = name;
        assignRandomId();
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.streams = new LinkedHashMap<>();
        this.favorites = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map<Stream, Boolean> getStreams() {
        return streams;
    }

    public int getId() {
        return id;
    }

    public List<Streamer> getFavorites() {
        return favorites;
    }

    public void addStream(Stream stream) {
        streams.put(stream, true);
    }

    public void addListenedStream(Stream stream) {
        streams.put(stream, true);
    }

    public void addFavorite(Streamer streamer) {
        favorites.add(streamer);
    }

    public void listenStream(Stream stream) {
        if (!streams.containsKey(stream)) {
            return;
        }
        streams.put(stream, true);
    }

    public void removeStream(Stream stream) {
        streams.remove(stream);
    }

    private void assignRandomId() {
        this.id = (int) (Math.random() * 1000000);
        while (checkDataBaseForId(this.id)) {
            this.id = (int) (Math.random() * 1000000);
        }
    }

    public static boolean checkUser(int id) {
        return true;
    }
}
