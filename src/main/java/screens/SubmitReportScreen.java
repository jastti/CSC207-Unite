package screens;
import Block_User_Application.interface_adapters.UserReporterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

// Frameworks/Drivers layer
public class SubmitReportScreen extends JPanel implements ActionListener{

    JTextField reportedUsername = new JTextField(15);

    JTextField yourUsername = new JTextField(15);
    JTextField message = new JTextField(15);
    JTextField messageID = new JTextField(15);
    UserReporterController userReporterController;


    /**
     * A window with a title and a JButton.
     */
    public SubmitReportScreen(UserReporterController Controller) {
        this.userReporterController = Controller;

        JLabel title = new JLabel("User Report Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);



        LabelTextPanelforReporter messageIDInfo = new LabelTextPanelforReporter(
                new JLabel("Message ID"), messageID);
        LabelTextPanelforReporter reportedUsernameInfo = new LabelTextPanelforReporter(
                new JLabel("Reported Username"), reportedUsername);
        LabelTextPanelforReporter usernameInfo = new LabelTextPanelforReporter(
                new JLabel("Your Username"), yourUsername);
        LabelTextPanelforReporter messageInfo = new LabelTextPanelforReporter(
                new JLabel("Reported Message"), message);



        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(submit);
        buttons.add(cancel);

        submit.addActionListener(this);
        cancel.addActionListener(this);



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(messageIDInfo);
        this.add(usernameInfo);
        this.add(messageInfo);
        this.add(reportedUsernameInfo);
        this.add(buttons);


    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getActionCommand().equals("Submit")) {
            try {
                userReporterController.create(messageID.getText(),
                        reportedUsername.getText(),
                        message.getText(),
                        yourUsername.getText());
                JOptionPane.showMessageDialog(this, String.format("%s Report Success.", yourUsername.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getActionCommand().equals("Cancel")) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            JFrame application2 = new ReportFirstScreen();
            application2.pack();
            application2.setVisible(true);
        }
    }
}