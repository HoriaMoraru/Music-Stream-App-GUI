import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVFileOperations {

    public static String[] splitByCommaInsideSpecificChars(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        boolean insideParentheses = false;
        boolean insideQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\"') {
                insideQuotes = !insideQuotes;
                currentPart.append(c);
            } else if (c == '(') {
                insideParentheses = true;
                currentPart.append(c);
            } else if (c == ')') {
                insideParentheses = false;
                currentPart.append(c);
            } else if (c == ',' && !insideParentheses && !insideQuotes) {
                parts.add(currentPart.toString());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(c);
            }
        }
        parts.add(currentPart.toString());
        return parts.toArray(new String[0]);
    }

    public void readStreams(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = splitByCommaInsideSpecificChars(line.replaceAll("\"", ""));
                StreamBuilder streamBuilder = new StreamBuilder();
                final Stream.streamType sType = streamBuilder.findStreamType(Integer.parseInt(info[0]));
                final Stream.streamGenre sGenre = streamBuilder.findStreamGenre(sType, Integer.parseInt(info[2]));
                int streamerId = Integer.parseInt(info[4]);
                Stream stream = streamBuilder.withType(sType)
                        .withId(Integer.parseInt(info[1]))
                        .withGenre(sGenre)
                        .withNoOfStreams(Long.parseLong(info[3]))
                        .withLength(Long.parseLong(info[5]))
                        .withStreamerId(streamerId)
                        .withDateAdded(Long.parseLong(info[6]))
                        .withName(info[7])
                        .build();
                Database.getInstance().getStreamer(streamerId).addStream(stream);
                Database.getInstance().addStream(stream);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void readUsers(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            Database data = Database.getInstance();
            while ((line = br.readLine()) != null) {
                String[] info = line.replaceAll("\"", "").split(",");
                User user = new User(Integer.parseInt(info[0]), info[1]);
                Arrays.stream(info[2].split(" "))
                        .map(Integer::parseInt)
                        .map(data::getStream)
                        .filter(Objects::nonNull)
                        .forEach(s -> {
                            user.addStream(s);
                            user.addFavorite(data.getStreamer(s.getStreamerId()));
                        });
                data.addUser(user);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void readStreamers(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.replaceAll("\"", "").split(",");
                Streamer streamer = new Streamer();
                Streamer.streamerType sType = streamer.findStreamerType(Integer.parseInt(info[0]));
                streamer.setId(Integer.parseInt(info[1]));
                streamer.setName(info[2]);
                streamer.setType(sType);
                Database.getInstance().addStreamer(streamer);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void readCommands(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Database.getInstance().addCommand(line);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void printOutput(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        catch (IOException io) {
            io.printStackTrace();
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
