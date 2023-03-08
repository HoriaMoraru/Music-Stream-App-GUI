public class AddCommand implements Command {

    private final String[] commandInfo;

    public AddCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }
    public void run() {
        final int streamerId = Integer.parseInt(commandInfo[0]);
        StreamBuilder streamBuilder = new StreamBuilder();
        final Stream.streamType sType = streamBuilder.findStreamType(Integer.parseInt(commandInfo[2]));
        final Stream.streamGenre sGenre = streamBuilder.findStreamGenre(sType, Integer.parseInt(commandInfo[4]));
        StringBuilder name = new StringBuilder(commandInfo[6]);
        for (int i = 7; i < commandInfo.length; i++) {
            name.append(" ").append(commandInfo[i]);
        }
        Stream stream = streamBuilder.withId(Integer.parseInt(commandInfo[3]))
                        .withType(sType)
                        .withGenre(sGenre)
                        .withLength(Long.parseLong(commandInfo[5]))
                        .withName(name.toString())
                        .withNoOfStreams(0)
                        .withStreamerId(streamerId)
                        .build();
        Database.getInstance().getStreamers()
                              .get(streamerId)
                              .addStream(stream);
    }
}
