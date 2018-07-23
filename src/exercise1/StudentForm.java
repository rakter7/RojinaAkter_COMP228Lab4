package exercise1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class StudentForm extends JFrame {
    //declaring all components
    private JLabel labelName, labelAddress, labelProvince, labelCity, labelPostalCode, labelPhoneNum, labelEmail;
    private JTextField textFieldName, textFieldAddress, textFieldProvince, textFieldCity, textFieldPostalCode, textFieldPhoneNum, textFieldEmail;
    private JRadioButton computerScienceJRadio, businessJRadio;
    private ButtonGroup radioGroup;
    private JList<String> courseJList;
    private JComboBox<String> courseJCombo;
    private JCheckBox studentCheckBox, volunteerCheckBox;
    private JPanel pLeft, pCenter, pRight, pBottom, pRightUp, pRightDown;
    private JTextArea textArea;
    private JButton display;
    private JScrollPane scrollPane;
    private List<String> courseChoice = new ArrayList<>();
    String[] courseArray;

//constructor

    public StudentForm() {
        super("Student Form");
        pLeft = new JPanel();
        pCenter = new JPanel();
        pRight = new JPanel();
        pRightUp = new JPanel();
        pRightDown = new JPanel();
        pBottom = new JPanel();

        //create new labels
        pLeft.setLayout(new GridLayout(7, 1, 5, 5));
        labelName = new JLabel("Name: ");
        labelAddress = new JLabel("Address: ");
        labelProvince = new JLabel("Province");
        labelCity = new JLabel("City: ");
        labelPostalCode = new JLabel("Postal Code: ");
        labelPhoneNum = new JLabel("Phone Number: ");
        labelEmail = new JLabel("Email: ");

        pLeft.add(labelName);
        pLeft.add(labelAddress);
        pLeft.add(labelProvince);
        pLeft.add(labelCity);
        pLeft.add(labelPostalCode);
        pLeft.add(labelPhoneNum);
        pLeft.add(labelEmail);

        //create textfield
        pCenter.setLayout(new GridLayout(7, 1, 5, 5));
        textFieldName = new JTextField(10);
        textFieldAddress = new JTextField(10);
        textFieldProvince = new JTextField(10);
        textFieldCity = new JTextField(10);
        textFieldPostalCode = new JTextField(10);
        textFieldPhoneNum = new JTextField(10);
        textFieldEmail = new JTextField(10);

        pCenter.add(textFieldName);
        pCenter.add(textFieldAddress);
        pCenter.add(textFieldProvince);
        pCenter.add(textFieldCity);
        pCenter.add(textFieldPostalCode);
        pCenter.add(textFieldPhoneNum);
        pCenter.add(textFieldEmail);

        //create JList
        courseJList = new JList();

        //create combo box
        courseJCombo = new JComboBox<String>();
        courseJCombo.setMaximumRowCount(5);
        courseJCombo.setPreferredSize(new Dimension(250, 60));
        courseJCombo.setSize(new Dimension(100, 150));
        courseJCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseSelected = courseJCombo.getSelectedItem().toString();
                if (!courseChoice.contains(courseSelected)) {
                    courseChoice.add(courseSelected);
                    courseArray = new String[courseChoice.size()];
                    for (int i = 0; i < courseArray.length; i++)

                        courseArray[i] = courseChoice.get(i);
                    courseJList.removeAll();
                    courseJList.setListData(courseArray);
                }
            }
        });



        //scroll pane
        scrollPane = new JScrollPane(textArea);

        //create radio button
        computerScienceJRadio = new JRadioButton("Computer Science", true);
        businessJRadio = new JRadioButton("Business", false);

        //setting panel
        pRight.setLayout(new BorderLayout());
        pRight.add(pRightUp, BorderLayout.NORTH);
        pRight.add(pRightDown, BorderLayout.SOUTH);

        pRightUp.setLayout(new FlowLayout());
        pRightUp.add(computerScienceJRadio);
        pRightUp.add(businessJRadio);
        pRightDown.setLayout(new FlowLayout());

        //creating radio button group
        radioGroup = new ButtonGroup();
        radioGroup.add(computerScienceJRadio);
        radioGroup.add(businessJRadio);

        // Register buttons for events
        RadioButtonHandler handler = new RadioButtonHandler();
        computerScienceJRadio.addActionListener(handler);
        businessJRadio.addActionListener(handler);

        //creating checkBox
        studentCheckBox = new JCheckBox("Student Council");
        volunteerCheckBox = new JCheckBox("Volunteer work");
        pRightUp.add(studentCheckBox);
        pRightUp.add(volunteerCheckBox);

        //combo box and Jlist
        pRightDown.add(courseJCombo);
        pRightDown.add(courseJList);

        //creating Text Area
        pBottom.setLayout(new FlowLayout());
        textArea = new JTextArea(5, 50);
        textArea.setEditable(false);
        pBottom.add(textArea);

        // Creating button to display information
        display = new JButton("Display");
        display.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        display.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //copyJList.setListData(courseJList.getSelectedValuesList());

                        String myCourses = "";
                        String otherActivities = "";
                        String program = "";

                        if (studentCheckBox.isSelected() && volunteerCheckBox.isSelected()) {
                            otherActivities = studentCheckBox.getText() + "-" + volunteerCheckBox.getText();
                        } else if (studentCheckBox.isSelected() && !volunteerCheckBox.isSelected()) {
                            otherActivities = studentCheckBox.getText();
                        } else if (volunteerCheckBox.isSelected() && !studentCheckBox.isSelected()) {
                            otherActivities = volunteerCheckBox.getText();
                        } else if (!studentCheckBox.isSelected() && !studentCheckBox.isSelected()) {
                            otherActivities = "No activities";
                        }

                        //checking for radio button

                        if (businessJRadio.isSelected()) {
                            program = businessJRadio.getText();
                        } else if (computerScienceJRadio.isSelected()) {
                            program = computerScienceJRadio.getText();
                        } else {
                            program = "";
                        }

                        for (int i = 0; i < courseArray.length; i++) {
                            myCourses += courseArray[i];
                        }

                        textArea.setText(String.format("%s, %s, %s, %s, %s, %s, %s, %s: %s, %n%s, %s, %s: %s", textFieldName.getText(), textFieldAddress.getText(),
                                textFieldEmail.getText(), textFieldPhoneNum.getText(), textFieldCity.getText(), textFieldProvince.getText(), textFieldPostalCode.getText(),
                                "Program Name", program, "Courses:", myCourses, "Other Activities", otherActivities));
                    }
                }
        );
        pBottom.add(display);

        // Add our panels to the window
        add(pLeft, BorderLayout.WEST);
        add(pCenter, BorderLayout.CENTER);
        add(pRight, BorderLayout.EAST);
        add(pBottom, BorderLayout.SOUTH);
        pack();
    }

    //radio button handler
    public class RadioButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            if (radioButton.getText() == "Computer Science") {
                courseJCombo.removeAllItems();
                courseJCombo.addItem("Java, ");
                courseJCombo.addItem("C#, ");
                courseJCombo.addItem("Database, ");
                courseJCombo.addItem("Web Interface Design, ");
                courseJCombo.addItem("Software Methodologies, ");
            } else if (radioButton.getText() == "Business") {
                courseJCombo.removeAllItems();
                courseJCombo.addItem("Accounting, ");
                courseJCombo.addItem("Human Resources Management, ");
                courseJCombo.addItem("Organizational Behavior, ");
                courseJCombo.addItem("Introduction to Finance, ");
                courseJCombo.addItem("Managerial Accounting, ");
            }
        }
    }
}
