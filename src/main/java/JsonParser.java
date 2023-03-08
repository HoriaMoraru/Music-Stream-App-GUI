import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public void createJsonOutputList(List<Stream> streams) {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator generator = factory.createJsonGenerator(new BufferedWriter
                (new FileWriter("src/main/resources/output.txt",
                        true)))) {
            generator.writeStartArray();
            for (Stream stream : streams) {
                writeJson(generator, stream);
            }
            generator.writeEndArray();
            generator.writeRaw(System.lineSeparator());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void createJsonOutputMap(Map<Stream, Boolean> streams) {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator generator = factory.createJsonGenerator(new BufferedWriter
                (new FileWriter("src/main/resources/output.txt",
                        true)))) {
            generator.writeStartArray();
            for (Stream stream : streams.keySet()) {
                writeJson(generator, stream);
            }
            generator.writeEndArray();
            generator.writeRaw(System.lineSeparator());
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void writeJson(JsonGenerator generator, Stream stream) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("id", String.valueOf(stream.getId()));
        generator.writeStringField("name", stream.getName());
        generator.writeStringField("streamerName", stream.getStreamerName());
        generator.writeStringField("noOfListenings", String.valueOf(stream.getNoOfListenings()));
        generator.writeStringField("length", stream.getLength());
        generator.writeStringField("dateAdded", stream.getDateAdded());
        generator.writeEndObject();
    }
}
