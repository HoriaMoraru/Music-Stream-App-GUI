import java.util.ArrayList;
import java.util.List;

public class Streamer implements GUIUser {

    private final List<Stream> streams;
    private int id;
    private String name;
    private streamerType type;
    public Streamer() {
        this.streams = new ArrayList<>();
    }

    public Streamer(String name, streamerType type) {
        this();
        this.name = name;
        this.type = type;
        assignRandomId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public streamerType getType() {
        return type;
    }

    public void setType(streamerType type) {
        this.type = type;
    }

    public List<Stream> getStreams() {
        return this.streams;
    }

    public void addStream(Stream stream) {
        streams.add(0, stream);
    }

    public void removeStream(Stream stream) {
        streams.remove(stream);
    }

    public streamerType findStreamerType(int streamerTypeId) {
        return streamerType.values()[streamerTypeId - 1];
    }

    enum streamerType {
        MUSICIAN, PODCASTER, AUDIOBOOK_AUTHOR
    }

    private void assignRandomId() {
        this.id = (int) (Math.random() * 1000000);
        while (checkDataBaseForId(this.id)) {
            this.id = (int) (Math.random() * 1000000);
        }
    }
}
