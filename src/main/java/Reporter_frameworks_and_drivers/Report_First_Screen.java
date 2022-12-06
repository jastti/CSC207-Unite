package Reporter_frameworks_and_drivers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import Reporter_Database.*;
import Reporter_Interface.UserReporterController;
import Reporter_Interface.UserReporterPresenter;
import Reporter_entities.UserFactory;

public class Report_First_Screen extends JFrame implements ActionListener {
    public Report_First_Screen() {

        JLabel title = new JLabel("Report System");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton report = new JButton("Report User");
        JButton block = new JButton("Back");

        JPanel buttons = new JPanel();
        buttons.add(report);
        buttons.add(block);

        report.addActionListener(this);
        block.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(buttons);
        this.setContentPane(main);
        this.pack();
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getActionCommand().equals("Report User")) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            // Build the main program window
            JFrame application = new JFrame("Report Example");
            CardLayout cardLayout = new CardLayout();
            JPanel screens = new JPanel(cardLayout);
            application.add(screens);

            GroupRepoInt user;
            try {
                user = new UserDataAccess("java.report.csv");
            } catch (IOException e) {
                throw new RuntimeException("Could not create file.");
            }
            UserReporterPresenter presenter = new UserReporterPresenter();
            UserFactory userFactory = new UserFactory();

            InputBoundary interactor = new UserReporterInteractor(
                    user, presenter, userFactory);
            UserReporterController userReporterController = new UserReporterController(
                    interactor
            );


            User_Report_Screen reportScreen = new User_Report_Screen(userReporterController);
            screens.add(reportScreen, "welcome");
            cardLayout.show(screens, "register");
            application.pack();
            application.setVisible(true);
        }else if (evt.getActionCommand().equals("Back")) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
            JFrame application2 = new LoggedinScreen("");
            application2.pack();
            application2.setVisible(true);
        }
    }
}