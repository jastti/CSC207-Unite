package random_grouper.frameworks_and_drivers;

import entities.User;
import random_grouper.interface_adapters.RanGroupCreateControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RandomGroupCreationUI extends JPanel implements ItemListener, ActionListener {
    JTextField groupName = new JTextField(15);
    List userInterestList;
    JTextArea selectedInterests;
    User loggedInUser;
    String currentSelectedInterestsString = "";
    java.util.List<String> finalSelectedInterests = new ArrayList<>();
    RanGroupCreateControl groupCreateControl;

    public RandomGroupCreationUI(RanGroupCreateControl controller) {
        this.groupCreateControl = controller;

        JLabel title = new JLabel("Random Group Creator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel groupNameLabel = new JLabel("Choose group name:");
        groupNameLabel.setLabelFor(groupName);
        groupNameLabel.setHorizontalTextPosition(JLabel.LEFT);


        // ToDo: Edit when have access to user class
        loggedInUser = new User("Test");
        String[] creatorInterests = loggedInUser.getInterests().toArray(new String[0]);

        userInterestList = new List(creatorInterests.length, true);
        for (String interest : creatorInterests){
            userInterestList.add(interest);
        }

        JLabel existingInterestsLabel = new JLabel("Select the interests group members must have:");
        existingInterestsLabel.setLabelFor(userInterestList);

        userInterestList.addItemListener(this);

        selectedInterests = new JTextArea();
        selectedInterests.setEditable(false);
        JLabel selectedInterestsLabel = new JLabel("Current interests selected:");
        selectedInterestsLabel.setLabelFor(selectedInterests);


        JButton createGroup = new JButton("Create Group");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(createGroup);
        buttons.add(cancel);

        createGroup.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(title);
        this.add(groupNameLabel);
        this.add(groupName);
        this.add(existingInterestsLabel);
        this.add(userInterestList);
        this.add(selectedInterestsLabel);
        this.add(selectedInterests);
        this.add(buttons);
    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param evt the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent evt) {
        String[] currentSelectedInterests = userInterestList.getSelectedItems();
        currentSelectedInterestsString = Arrays.toString(currentSelectedInterests);
        currentSelectedInterestsString = currentSelectedInterestsString.replace("[", "");
        currentSelectedInterestsString = currentSelectedInterestsString.replace("]", "");
        selectedInterests.setText(currentSelectedInterestsString);
    }

    /**
     * Invoked when a button is pressed and results in evt.
     *
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());

        if (evt.getActionCommand().equals("Create Group")) {
            try {
                String[] selectedInterestsArray = currentSelectedInterestsString.split(", ");
                for (String interest : selectedInterestsArray) {
                    if (!(finalSelectedInterests.contains(interest))) {
                        finalSelectedInterests.add(interest);
                    }
                }
                groupCreateControl.createGroup(groupName.getText(), finalSelectedInterests, loggedInUser.getName());
                JOptionPane.showMessageDialog(this, String.format("%s created", groupName.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (evt.getActionCommand().equals("Cancel")) {
            JComponent component = (JComponent) evt.getSource();
            Window win = SwingUtilities.getWindowAncestor(component);
            win.dispose();
        }
    }
}
