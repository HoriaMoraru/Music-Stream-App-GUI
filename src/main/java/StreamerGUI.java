import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class StreamerGUI extends JFrame implements GUI {

    private final Streamer streamer;
    private JButton logoutButton;
    private JButton addStreamButton;
    private JPanel panel;
    private JList<String> streamList;

    public StreamerGUI(Streamer streamer) {
        super("User");
        this.streamer = streamer;

        createView();
        fit(this);
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public void display() {
        setVisible(true);
    }

    private void createView() {

        panel = createBackground(1000, 500, this);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(GUIHelper.COLOR_OUTLINE);
        panel.add(separator);
        separator.setBounds(400, 80, 1, 240);

        JLabel titleLabel = new JLabel("Go vibing, " + streamer.getName());
        titleLabel.setFont(GUIHelper.FONT_USER_TITLE);
        titleLabel.setForeground(Color.white);
        panel.add(titleLabel);
        titleLabel.setBounds(20, 20, 500, 40);

        JLabel idLabel = new JLabel("ID: " + streamer.getId());
        idLabel.setFont(GUIHelper.FONT_GENERAL_UI);
        idLabel.setForeground(Color.white);
        panel.add(idLabel);
        idLabel.setBounds(20, 80, 200, 40);

        JLabel nameLabel = new JLabel("Name: " + streamer.getName());
        nameLabel.setFont(GUIHelper.FONT_GENERAL_UI);
        nameLabel.setForeground(Color.white);
        panel.add(nameLabel);
        nameLabel.setBounds(20, 120, 200, 40);

        JLabel typeLabel = new JLabel("Type: " + streamer.getType());
        typeLabel.setFont(GUIHelper.FONT_GENERAL_UI);
        typeLabel.setForeground(Color.white);
        panel.add(typeLabel);
        typeLabel.setBounds(20, 160, 200, 40);

        List<Stream> streams = streamer.getStreams();
        String[] streamsArray = streams.stream()
                .map(Stream::getName)
                .toArray(String[]::new);
        streamList = new JList<>(streamsArray);
        streamList.setFont(GUIHelper.FONT_GENERAL_UI);
        streamList.setForeground(Color.white);
        streamList.setBackground(GUIHelper.COLOR_BACKGROUND);
        streamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        streamList.setLayoutOrientation(JList.VERTICAL);
        streamList.setVisibleRowCount(-1);
        streamList.setSelectionBackground(GUIHelper.COLOR_OUTLINE);
        streamList.setSelectionForeground(Color.white);


        JScrollPane scrollPane = new JScrollPane(streamList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(GUIHelper.COLOR_BACKGROUND);
        panel.add(scrollPane);
        scrollPane.setBounds(200, 200, 200, 240);


        JLabel overviewLabel = new JLabel();
        overviewLabel.setFont(GUIHelper.FONT_GENERAL_UI);
        overviewLabel.setForeground(Color.white);
        overviewLabel.setBackground(GUIHelper.COLOR_BACKGROUND);

        streamList.addListSelectionListener(e -> {
            Database data = Database.getInstance();
            if (streamList.getSelectedIndex() != -1) {
                Stream stream = data.getStream(data.getStreamIdByName(streamList.getSelectedValue()));
                overviewLabel.setText("<html>Stream name: " + stream.getName() + "<br>ID: "
                        + stream.getId() + "<br>NoOfListenings: "
                        + stream.getNoOfListenings() + "<br>Length: "
                        + stream.getLength() + "<br>Added: "
                        + stream.getDateAdded() + "</html>");
                overviewLabel.setVisible(true);
            }
        });
        panel.add(overviewLabel);
        overviewLabel.setBounds(400, 300, 200, 240);
        overviewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                overviewLabel.setVisible(false);
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.addActionListener(e -> {
            Database data = Database.getInstance();
            data.removeStream(streamer.getId(), data.getStreamIdByName(streamList.getSelectedValue()));
            updateStreamList();
        });
        popupMenu.add(removeItem);

        streamList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger() && streamList.getSelectedIndex() != -1) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        logoutButton = ButtonFactory.createButton("LOGOUTSTREAMER", this);
        addStreamButton = ButtonFactory.createButton("ADDSTREAM", this);

        panel.add(logoutButton);
        panel.add(addStreamButton);
        add(panel);
    }

    public void updateStreamList() {

       String[] streamsArray = streamer.getStreams()
                                       .stream()
                                       .map(Stream::getName)
                                       .toArray(String[]::new);
       streamList.setListData(streamsArray);
       panel.revalidate();
       panel.repaint();
    }
}