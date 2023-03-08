public class StreamBuilder {

    private final Stream stream;

    public StreamBuilder() {
        stream = new Stream();
    }

    public StreamBuilder withId(int id) {
        stream.setStreamId(id);
        return this;
    }

    public StreamBuilder withGUId() {
        stream.setStreamId(stream.assignRandomId());
        return this;
    }

    public StreamBuilder withName(String name) {
        stream.setName(name);
        return this;
    }

    public StreamBuilder withLength(long length) {
        stream.setLength(length);
        return this;
    }

    public StreamBuilder withStreamerId(int streamerId) {
        stream.setStreamerId(streamerId);
        return this;
    }

    public StreamBuilder withDateAdded(long dateAdded) {
        stream.setDateAdded(dateAdded);
        return this;
    }

    public StreamBuilder withNoOfStreams(long noOfStreams) {
        stream.setNoOfStreams(noOfStreams);
        return this;
    }

    public StreamBuilder withGenre(Stream.streamGenre gType) {
        stream.setGenre(gType);
        return this;
    }

    public StreamBuilder withType(Stream.streamType sType) {
        stream.setType(sType);
        return this;
    }

    public Stream build() {
        return stream;
    }

    public Stream.streamType findStreamType(int streamTypeId) {
        return Stream.streamType.values()[streamTypeId - 1];
    }

    public Stream.streamGenre findStreamGenre(Stream.streamType streamType, int streamGenreId) {
        switch (streamType) {
            case SONG:
                return Stream.streamGenre.values()[streamGenreId - 1];
            case PODCAST:
                return Stream.streamGenre.values()[streamGenreId + 5];
            case AUDIOBOOK:
                return Stream.streamGenre.values()[streamGenreId + 7];
            default:
                throw new IllegalArgumentException();
        }
    }
}
