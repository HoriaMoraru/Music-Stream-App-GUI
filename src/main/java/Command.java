public interface Command {

    void run();

    enum commandType {
        ADD,
        LIST,
        DELETE,
        RECOMMEND,
        SURPRISE,
        LISTEN
    }
}
