import javax.swing.*;
import java.util.Random;

public class AddStreamButton extends JButton {
    public AddStreamButton(StreamerGUI frame) {
        GUIHelper.createStyledButton(GUIHelper.BUTON_TEXT_ADD_STREAM, this);
        setBounds(500, 200, 200, 40);
        addActionListener(e -> {
            String streamName = JOptionPane.showInputDialog(frame, "Enter stream name:", "Add Stream", JOptionPane.QUESTION_MESSAGE);
            if (streamName != null && !streamName.isEmpty()) {
                StreamBuilder streamBuilder = new StreamBuilder();
                Streamer streamer = frame.getStreamer();
                Stream stream = streamBuilder
                        .withName(streamName)
                        .withNoOfStreams(0)
                        .withLength(new Random().nextInt((240-120) + 1) + 120)
                        .withStreamerId(streamer.getId())
                        .withGUId()
                        .withDateAdded(System.currentTimeMillis())
                        .build();
                Database data = Database.getInstance();
                data.addStream(stream);
                streamer.getStreams().add(stream);
                frame.updateStreamList();
                System.out.println("Stream added: " + stream.getName());
            }
        });
    }
}
