public interface GUIUser {

    int getId();

    String getName();

    default boolean checkDataBaseForId(int id) {
        if (id < 0) {
            return false;
        }
        Database database = Database.getInstance();
        return database.checkId(id);
    }
}
