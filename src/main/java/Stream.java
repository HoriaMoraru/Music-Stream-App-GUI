import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Stream implements Serializable {

    private streamType sType;
    private streamGenre sGenre;
    private long noOfStreams;
    private int streamId;
    private long length;
    private int streamerId;
    private long dateAdded;
    private String name;

    public long getNoOfListenings() {
        return noOfStreams;
    }

    public int getId() {
        return streamId;
    }

    public String getLength() {
        Duration duration = Duration.ofSeconds(length);
        if (duration.toHours() > 0)
            return String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        else if (duration.toMinutes() > 0)
            return String.format("%02d:%02d", duration.toMinutes(), duration.toSecondsPart());
        else return String.format("%02d", duration.toSecondsPart());
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getDateAdded() {
        Instant instant = Instant.ofEpochSecond(dateAdded);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dtf.format(instant.atZone(ZoneId.of("UTC")));
    }

    public LocalDateTime getDateForCompare() {
        Instant instant = Instant.ofEpochSecond(dateAdded);
        return instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreamerName() {
        Streamer streamer = Database.getInstance().getStreamer(streamerId);
        if (streamer != null) {
            return streamer.getName();
        }
        return null;
    }

    public streamType whatType() {
        return sType;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }

    public void setType(streamType sType) {
        this.sType = sType;
    }

    public void setGenre(streamGenre sGenre) {
        this.sGenre = sGenre;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public void incrementNoOfStreams() {
        noOfStreams++;
    }

    private boolean checkDataBaseForId(int id) {
        if (id < 0) {
            return false;
        }
        Database database = Database.getInstance();
        return database.checkStreamId(id);
    }

    public int assignRandomId() {
        int id = (int) (Math.random() * 1000000);
        while (checkDataBaseForId(id)) {
            id = (int) (Math.random() * 1000000);
        }
        return id;
    }

    public String getOverview() {
        return String.format("%-5d %-20s %-10s %-10s %-10s", streamId, name, getLength(), getStreamerName(), getDateAdded());
    }

    enum streamType {
        SONG, PODCAST, AUDIOBOOK
    }

    enum streamGenre {
        POP, LATIN, HOUSE, DANCE, TRAP, DOCUMENTARY, CELEBRITIES, TECH, FICTION, PERSONAL_DEVELOPMENT, CHILDREN
    }
}

