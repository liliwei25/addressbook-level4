package seedu.address.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Creates a birthday notification
 */
public class BirthdayPopup {

    String message = "There are birthdays today: \n";
    String header = "Birthday Alert!";
    JDialog frame = new JDialog();
    GridBagConstraints constraints = new GridBagConstraints();

    public BirthdayPopup(String[] person) {
        createFrame(person.length);
        createIcon();
        createCloseButton();
        createMessage(person);
        createPopup();
    }

    void createFrame(int size) {
        frame.setSize(300, 125 + size * 50);
        frame.setUndecorated(true);
        frame.setLayout(new GridBagLayout());
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
        frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());
        frame.setAlwaysOnTop(true);
    }

    void createIcon() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        Icon headingIcon = new ImageIcon("src/main/resources/images/birthday_cake.png", "birthday icon");
        JLabel headingLabel = new JLabel(header);
        headingLabel.setIcon(headingIcon);
        headingLabel.setOpaque(false);
        frame.add(headingLabel, constraints);
    }

    void createCloseButton() {
        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        JButton closeButton = new JButton(new AbstractAction("x") {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
            }
        });
        closeButton.setMargin(new Insets(1, 4, 1, 4));
        closeButton.setFocusable(false);
        frame.add(closeButton, constraints);
    }

    private void createMessage(String[] person) {
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel messageLabel = new JLabel( message);
        frame.add(messageLabel, constraints);
        for(String e: person) {
            constraints.gridy++;
            messageLabel = new JLabel(e);
            frame.add(messageLabel, constraints);
        }
        frame.setVisible(true);
    }

    private void createPopup() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // time after which pop up will be disappeared.
                    frame.dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }
}
