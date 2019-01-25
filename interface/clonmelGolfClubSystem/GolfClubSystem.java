package clonmelGolfClubSystem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;

public class GolfClubSystem extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	
	// main menu
	JButton mainDisplayMember = new JButton("Display a Member");
	JButton mainAddMember = new JButton("Add a New Member");
	JButton mainRemoveMember = new JButton("Remove a Member");
	JButton mainEditMember = new JButton("Edit a Member");
	JButton mainAverageAgeOfMembers = new JButton("Average Age of Members");
	JButton mainNumberOfSeniorMembers = new JButton("Number of Senior Members");
	JButton mainNumberOfJuniorMembers = new JButton("Number of Junior Members");
	JButton mainTotalNumberOfMembers = new JButton("Total Number of Members");
	JButton mainMembersWhoHaventPaid = new JButton("Members Whose Membership Is Due");
	JTextArea mainTextArea = new JTextArea(20, 60);
	JScrollPane mainScroll = new JScrollPane(mainTextArea);

	// add member panel
	JPanel addMemberPanel = new JPanel();
	JLabel addMemberFName = new JLabel("First Name");
	JTextField addMemberFNameBox = new JTextField(15);
	JLabel addMemberLName = new JLabel("Last Name");
	JTextField addMemberLNameBox = new JTextField(15);
	JLabel addMemberAddress = new JLabel("Address");
	JTextField addMemberAddressBox = new JTextField(38);
	JLabel addMemberAge = new JLabel("Age");
	JTextField addMemberAgeBox = new JTextField(15);
	JLabel addMemberContact = new JLabel("Contact Number");
	JTextField addMemberContactBox = new JTextField(15);
	String addMemberGender;
	JLabel addMemberGenderLabel = new JLabel("Gender");
	ButtonGroup addMemberGenderGroup = new ButtonGroup();
	JRadioButton addMemberFemale = new JRadioButton("Female");
	JRadioButton addMemberMale = new JRadioButton("Male");
	JLabel addMemberStatus = new JLabel("Membership Status - Enter Paid or Due");
	JTextField addMemberStatusBox = new JTextField(11);
	JLabel addMemberHandicap = new JLabel("Current Handicap");
	JTextField addMemberHandicapBox = new JTextField(10);
	JButton addMember = new JButton("Add New Member");
	JLabel addMemberExitButtonLabel = new JLabel("Please exit before choosing another option");
	JButton addMemberExitButton = new JButton("Exit");
	JTextArea addMemberTextArea = new JTextArea(10, 40);
	JScrollPane addMemberScroll = new JScrollPane(addMemberTextArea);
	LinkedList<Member> members = new LinkedList<Member>();

	// display member
	JPanel displayMemberPanel = new JPanel();
	JLabel displayMemberFName = new JLabel("First Name");
	JTextField displayMemberFNameBox = new JTextField(20);
	JLabel displayMemberLName = new JLabel("Last Name");
	JTextField displayMemberLNameBox = new JTextField(20);
	JLabel displayMemberAddress = new JLabel("Address");
	JTextField displayMemberAddressBox = new JTextField(20);
	JButton displayMember = new JButton("Display Member");
	JLabel displayMemberExitButtonLabel = new JLabel("Please exit before choosing another option");
	JButton displayMemberExitButton = new JButton("Exit");
	JTextArea displayMemberTextArea = new JTextArea(10, 20);
	JScrollPane displayMemberScroll = new JScrollPane(displayMemberTextArea);

	// edit member left of window
	JPanel leftEditMemberPanel = new JPanel();
	JLabel leftEditMemberFName = new JLabel("First Name");
	JTextField leftEditMemberFNameBox = new JTextField(5);
	JLabel leftEditMemberLName = new JLabel("Last Name");
	JTextField leftEditMemberLNameBox = new JTextField(5);
	JLabel leftEditMemberAddress = new JLabel("Address");
	JTextField editMemberAddressBox = new JTextField(5);
	JTextField leftEditMemberAddressBox = new JTextField(5);
	JButton searchMemberToEdit = new JButton("Search for Member to be Edited");
	JTextArea editMemberTextArea = new JTextArea(10, 10);
	JScrollPane editMemberScroll = new JScrollPane(editMemberTextArea);
	Member memberToBeEdited = null;

	// edit member right of window
	JPanel rightEditMemberPanel = new JPanel();
	JLabel rightEditMemberLName = new JLabel("Last Name");
	JTextField rightEditMemberLNameBox = new JTextField(5);
	JButton editLNameButton = new JButton("Change the Last Name");
	JLabel rightEditMemberAddress = new JLabel("Address");
	JTextField rightEditMemberAddressBox = new JTextField(5);
	JButton editAddressButton = new JButton("Change the Address");
	JLabel rightEditMemberContact = new JLabel("Contact Number");
	JTextField rightEditMemberContactBox = new JTextField(5);
	JButton editContactNumButton = new JButton("Change the contactNumber");
	JLabel rightEditMemberStatus = new JLabel("Membership Status - Enter Paid orDue");
	JTextField rightEditMemberStatusBox = new JTextField(5);
	JButton editStatusButton = new JButton("Change the Status");
	JLabel rightEditMemberHandicap = new JLabel("Current Handicap");
	JTextField rightEditMemberHandicapBox = new JTextField(5);
	JButton editHandicapButton = new JButton("Change the Handicap");
	JLabel editMemberExitButtonLabel = new JLabel("Please exit before choosing another option");
	JButton editMemberExitButton = new JButton("Exit");
	JTextArea rightEditMemberTextArea = new JTextArea(10, 10);
	JLabel whatNeedsToBeEdited = new JLabel("Enter the new data and click the corresponding button");

	// remove member
	JPanel removeMemberPanel = new JPanel();
	JLabel removeMemberFName = new JLabel("First Name");
	JTextField removeMemberFNameBox = new JTextField(10);
	JLabel removeMemberLName = new JLabel("Last Name");
	JTextField removeMemberLNameBox = new JTextField(10);
	JLabel removeMemberAddress = new JLabel("Address");
	JTextField removeMemberAddressBox = new JTextField(10);
	JButton removeMember = new JButton("Remove Member");
	JLabel removeMemberExitButtonLabel = new JLabel("Please exit before choosing another option");
	JButton removeMemberExitButton = new JButton("Exit");
	JTextArea removeMemberTextArea = new JTextArea(10, 20);
	JScrollPane removeMemberScroll = new JScrollPane(removeMemberTextArea);

	Container windowSurface = null;
	double numberOfMembers = 0;

	@SuppressWarnings("unchecked")
	public GolfClubSystem() {
		super("Clonmel Golf Club");
		setSize(950, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		windowSurface = getContentPane();
		windowSurface.setBackground(new Color(204, 255, 204));
		FlowLayout flowManager = new FlowLayout();
		windowSurface.setLayout(flowManager);

		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("myObjects.dat"));
			members = (LinkedList<Member>) objectIn.readObject();
			objectIn.close();

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Class cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(null, "Can't find the file", "Error", JOptionPane.ERROR_MESSAGE);

		}

		// adding buttons to main window
		mainDisplayMember.addActionListener(this);
		windowSurface.add(mainDisplayMember);
		mainAddMember.addActionListener(this);
		windowSurface.add(mainAddMember);
		mainRemoveMember.addActionListener(this);
		windowSurface.add(mainRemoveMember);
		mainEditMember.addActionListener(this);
		windowSurface.add(mainEditMember);
		mainAverageAgeOfMembers.addActionListener(this);
		windowSurface.add(mainAverageAgeOfMembers);
		mainNumberOfSeniorMembers.addActionListener(this);
		windowSurface.add(mainNumberOfSeniorMembers);
		mainNumberOfJuniorMembers.addActionListener(this);
		windowSurface.add(mainNumberOfJuniorMembers);
		mainTotalNumberOfMembers.addActionListener(this);
		windowSurface.add(mainTotalNumberOfMembers);
		mainMembersWhoHaventPaid.addActionListener(this);
		windowSurface.add(mainMembersWhoHaventPaid);

		// adding fields to add member panel
		BoxLayout addMemberLayout = new BoxLayout(addMemberPanel, BoxLayout.Y_AXIS);
		addMemberPanel.setLayout(addMemberLayout);
		addMemberPanel.add(addMemberFName);
		addMemberPanel.add(addMemberFNameBox);
		addMemberPanel.add(addMemberLName);
		addMemberPanel.add(addMemberLNameBox);
		addMemberPanel.add(addMemberAddress);
		addMemberPanel.add(addMemberAddressBox);
		addMemberPanel.add(addMemberAge);
		addMemberPanel.add(addMemberAgeBox);
		addMemberPanel.add(addMemberContact);
		addMemberPanel.add(addMemberContactBox);
		addMemberGenderGroup.add(addMemberFemale);
		addMemberGenderGroup.add(addMemberMale);
		addMemberFemale.addItemListener(this);
		addMemberPanel.add(addMemberFemale);
		addMemberMale.addItemListener(this);
		addMemberPanel.add(addMemberMale);
		addMemberPanel.add(addMemberStatus);
		addMemberPanel.add(addMemberStatusBox);
		addMemberPanel.add(addMemberHandicap);
		addMemberPanel.add(addMemberHandicapBox);
		addMember.addActionListener(this);
		addMemberPanel.add(addMember);		
		addMemberExitButton.addActionListener(this);
		addMemberPanel.add(addMemberExitButtonLabel);
		addMemberPanel.add(addMemberExitButton);		
		addMemberPanel.add(addMemberScroll);

		// adding fields to display member panel
		BoxLayout displayMemberLayout = new BoxLayout(displayMemberPanel, BoxLayout.Y_AXIS);
		displayMemberPanel.setLayout(displayMemberLayout);
		displayMemberPanel.add(displayMemberFName);
		displayMemberPanel.add(displayMemberFNameBox);
		displayMemberPanel.add(displayMemberLName);
		displayMemberPanel.add(displayMemberLNameBox);
		displayMemberPanel.add(displayMemberAddress);
		displayMemberPanel.add(displayMemberAddressBox);
		displayMember.addActionListener(this);
		displayMemberPanel.add(displayMember);
		displayMemberExitButton.addActionListener(this);
		displayMemberPanel.add(displayMemberExitButtonLabel);
		displayMemberPanel.add(displayMemberExitButton);
		displayMemberPanel.add(displayMemberScroll);

		// adding fields to remove member panel
		BoxLayout removeMemberLayout = new BoxLayout(removeMemberPanel, BoxLayout.Y_AXIS);
		removeMemberPanel.setLayout(removeMemberLayout);
		removeMemberPanel.add(removeMemberFName);
		removeMemberPanel.add(removeMemberFNameBox);
		removeMemberPanel.add(removeMemberLName);
		removeMemberPanel.add(removeMemberLNameBox);
		removeMemberPanel.add(removeMemberAddress);
		removeMemberPanel.add(removeMemberAddressBox);
		removeMember.addActionListener(this);
		removeMemberPanel.add(removeMember);
		removeMemberExitButton.addActionListener(this);
		removeMemberPanel.add(removeMemberExitButtonLabel);
		removeMemberPanel.add(removeMemberExitButton);
		removeMemberPanel.add(removeMemberScroll);

		// adding fields to left side panel of edit member window
		BoxLayout leftEditMemberLayout = new BoxLayout(leftEditMemberPanel, BoxLayout.Y_AXIS);
		leftEditMemberPanel.setLayout(leftEditMemberLayout);
		leftEditMemberPanel.add(leftEditMemberFName);
		leftEditMemberPanel.add(leftEditMemberFNameBox);
		leftEditMemberPanel.add(leftEditMemberLName);
		leftEditMemberPanel.add(leftEditMemberLNameBox);
		leftEditMemberPanel.add(leftEditMemberAddress);
		leftEditMemberPanel.add(leftEditMemberAddressBox);
		searchMemberToEdit.addActionListener(this);
		leftEditMemberPanel.add(searchMemberToEdit);
		leftEditMemberPanel.add(editMemberScroll);

		// adding fields to right side panel of edit member window
		BoxLayout rightEditMemberLayout = new BoxLayout(rightEditMemberPanel, BoxLayout.Y_AXIS);
		rightEditMemberPanel.setLayout(rightEditMemberLayout);
		rightEditMemberPanel.add(whatNeedsToBeEdited);
		rightEditMemberPanel.add(rightEditMemberLName);
		rightEditMemberPanel.add(rightEditMemberLNameBox);
		editLNameButton.addActionListener(this);
		rightEditMemberPanel.add(editLNameButton);
		rightEditMemberPanel.add(rightEditMemberAddress);
		rightEditMemberPanel.add(rightEditMemberAddressBox);
		editAddressButton.addActionListener(this);
		rightEditMemberPanel.add(editAddressButton);
		rightEditMemberPanel.add(rightEditMemberContact);
		rightEditMemberPanel.add(rightEditMemberContactBox);
		editContactNumButton.addActionListener(this);
		rightEditMemberPanel.add(editContactNumButton);
		rightEditMemberPanel.add(rightEditMemberStatus);
		rightEditMemberPanel.add(rightEditMemberStatusBox);
		editStatusButton.addActionListener(this);
		rightEditMemberPanel.add(editStatusButton);
		rightEditMemberPanel.add(rightEditMemberHandicap);
		rightEditMemberPanel.add(rightEditMemberHandicapBox);
		editHandicapButton.addActionListener(this);
		rightEditMemberPanel.add(editHandicapButton);
		editMemberExitButton.addActionListener(this);
		rightEditMemberPanel.add(editMemberExitButtonLabel);
		rightEditMemberPanel.add(editMemberExitButton);
		rightEditMemberPanel.add(rightEditMemberTextArea);

		setContentPane(windowSurface);

	}

	public void actionPerformed(ActionEvent event) {
		try {

			if (event.getSource() == mainAddMember) {
				mainScroll.setVisible(false);
				addMemberPanel.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(addMemberPanel);
				setContentPane(windowSurface);

			}

			if (event.getSource() == addMember) {
				if (addMemberFNameBox.getText().isEmpty() || addMemberLNameBox.getText().isEmpty()
						|| addMemberAddressBox.getText().isEmpty() || addMemberAgeBox.getText().isEmpty()
						|| addMemberContactBox.getText().isEmpty() || addMemberStatusBox.getText().isEmpty()
						|| addMemberHandicapBox.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a value for all fields", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
				if (addMemberStatusBox.getText().equals("due") == false && addMemberStatusBox.getText().equals("paid") == false) {
					JOptionPane.showMessageDialog(null, "Please only enter due or paid for membership status", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Member memberAdded = addMember();
					addMemberTextArea.setText("Member added successfully \n" + memberAdded);
					addMemberFNameBox.setText("");
					addMemberLNameBox.setText("");
					addMemberAddressBox.setText("");
					addMemberAgeBox.setText("");
					addMemberContactBox.setText("");
					addMemberGenderGroup.clearSelection();
					addMemberStatusBox.setText("");
					addMemberHandicapBox.setText("");
				}
				savingFile();

			}

			if (event.getSource() == addMemberExitButton) {
				addMemberPanel.setVisible(false);
				addMemberTextArea.setText("");
			}

			if (event.getSource() == mainDisplayMember) {
				mainScroll.setVisible(false);
				displayMemberPanel.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(displayMemberPanel);
				setContentPane(windowSurface);

			}

			if (event.getSource() == displayMember) {
				if (displayMemberFNameBox.getText().isEmpty() || displayMemberLNameBox.getText().isEmpty()
						|| displayMemberAddressBox.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a value for all fields", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String fName;
					String lName;
					String address;
					for (Member member : members) {
						fName = member.getFName();
						lName = member.getLName();
						address = member.getAddress();
						if (fName.equals(displayMemberFNameBox.getText())
								&& lName.equals(displayMemberLNameBox.getText())
								&& address.equals(displayMemberAddressBox.getText())) {
							displayMemberTextArea.setText(member.toString());
						}

					}
					if (displayMemberTextArea.getText().isEmpty()) {
						displayMemberTextArea.setText("Cannot find this member in the system");
					}
				}

			}
			if (event.getSource() == displayMemberExitButton) {
				displayMemberPanel.setVisible(false);
				displayMemberFNameBox.setText(null);
				displayMemberLNameBox.setText(null);
				displayMemberAddressBox.setText(null);
				displayMemberTextArea.setText(null);
			}

			if (event.getSource() == mainRemoveMember) {
				mainScroll.setVisible(false);
				removeMemberPanel.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(removeMemberPanel);
				setContentPane(windowSurface);
			}

			if (event.getSource() == removeMember) {
				if (removeMemberFNameBox.getText().isEmpty() || removeMemberLNameBox.getText().isEmpty()
						|| removeMemberAddressBox.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a value for all fields", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					String fName;
					String lName;
					String address;
					for (Member member : members) {
						
						fName = member.getFName();
						lName = member.getLName();
						address = member.getAddress();
						if (fName.equals(removeMemberFNameBox.getText()) && lName.equals(removeMemberLNameBox.getText()) && address.equals(removeMemberAddressBox.getText())) {
							int position = members.indexOf(member);
							members.remove(position);
							removeMemberTextArea.setText("The following member has been removed \n" + member.toString());
							savingFile();
						}
					}
					if (removeMemberTextArea.getText().isEmpty()) {
						removeMemberTextArea.setText("Cannot find this member in the system");
					}
				}

			}

			if (event.getSource() == removeMemberExitButton) {
				removeMemberPanel.setVisible(false);
				removeMemberFNameBox.setText("");
				removeMemberLNameBox.setText("");
				removeMemberAddressBox.setText("");
				removeMemberTextArea.setText("");

			}

			if (event.getSource() == mainEditMember) {
				mainScroll.setVisible(false);
				leftEditMemberPanel.setVisible(true);
				rightEditMemberPanel.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(leftEditMemberPanel);
				windowSurface.add(rightEditMemberPanel);
				setContentPane(windowSurface);

			}

			if (event.getSource() == searchMemberToEdit) {
				if (leftEditMemberFNameBox.getText().isEmpty() || leftEditMemberLNameBox.getText().isEmpty()
						|| leftEditMemberAddressBox.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a value for all fields", "Error", JOptionPane.ERROR_MESSAGE);

				} else {
					String fName;
					String lName;
					String address;

					for (Member member : members) {
						fName = member.getFName();
						lName = member.getLName();
						address = member.getAddress();
						if (fName.equals(leftEditMemberFNameBox.getText())
								&& lName.equals(leftEditMemberLNameBox.getText())
								&& address.equals(leftEditMemberAddressBox.getText())) {
							numberOfMembers++;
							memberToBeEdited = member;
							editMemberTextArea.setText(memberToBeEdited.toString());
						}
					}
					if (editMemberTextArea.getText().isEmpty()) {
						editMemberTextArea.setText("Cannot find this member in the system");
					}
				}
			}

			if (event.getSource() == editLNameButton) {
				memberToBeEdited.setLName(rightEditMemberLNameBox.getText());
				rightEditMemberTextArea.setText("Member successfully edited \n" + memberToBeEdited.toString());
				rightEditMemberLNameBox.setText("");
				savingFile();

			}

			if (event.getSource() == editAddressButton) {
				memberToBeEdited.setAddress(rightEditMemberAddressBox.getText());
				rightEditMemberTextArea.setText("Member successfully edited \n" + memberToBeEdited.toString());
				rightEditMemberAddressBox.setText("");
				savingFile();
			}

			if (event.getSource() == editContactNumButton) {
				memberToBeEdited.setContactNumber(rightEditMemberContactBox.getText());
				rightEditMemberTextArea.setText("Member successfully edited \n" + memberToBeEdited.toString());
				rightEditMemberContactBox.setText("");
				savingFile();
			}

			if (event.getSource() == editHandicapButton) {
				memberToBeEdited.setHandicap(Integer.parseInt(rightEditMemberHandicapBox.getText()));
				rightEditMemberTextArea.setText("Member successfully edited \n" + memberToBeEdited.toString());
				rightEditMemberHandicapBox.setText("");
				savingFile();
			}

			if (event.getSource() == editStatusButton) {
				memberToBeEdited.setMembershipStatus(rightEditMemberStatusBox.getText());
				rightEditMemberTextArea.setText("Member successfully edited \n" + memberToBeEdited.toString());
				rightEditMemberStatusBox.setText("");
				savingFile();
			}

			if (event.getSource() == editMemberExitButton) {
				leftEditMemberPanel.setVisible(false);
				rightEditMemberPanel.setVisible(false);
				leftEditMemberFNameBox.setText("");
				leftEditMemberLNameBox.setText("");
				leftEditMemberAddressBox.setText("");
				rightEditMemberLNameBox.setText("");
				rightEditMemberAddressBox.setText("");
				rightEditMemberContactBox.setText("");
				rightEditMemberLNameBox.setText("");
				rightEditMemberStatusBox.setText("");
				rightEditMemberHandicapBox.setText("");
				editMemberTextArea.setText("");
			}

			if (event.getSource() == mainAverageAgeOfMembers) {
				DecimalFormat df = new DecimalFormat("##.##");
				mainScroll.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(mainScroll);
				setContentPane(windowSurface);

				double age = 0;

				double averageAge = 0.0;
				for (Member member : members) {
					age = age + member.getAge();
					numberOfMembers++;

				}
				if (numberOfMembers == 0) {
					mainTextArea.setText("There are no members in the system");
				} else {
					averageAge = age / numberOfMembers;
					mainTextArea.setText("The average age of members is " + df.format(averageAge));
				}

			}

			if (event.getSource() == mainNumberOfSeniorMembers) {
				mainScroll.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(mainScroll);
				setContentPane(windowSurface);

				int numberOfSeniorMembers = 0;
				for (Member member : members) {
					if (member.getAge() > 65) {
						numberOfMembers++;
						numberOfSeniorMembers++;
					}
				}
				if (numberOfMembers == 0) {
					mainTextArea.setText("There are no members in the system");
				} else {
					mainTextArea.setText("The number of senior members is " + numberOfSeniorMembers);
				}
			}

			if (event.getSource() == mainNumberOfJuniorMembers) {
				mainScroll.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(mainScroll);
				setContentPane(windowSurface);

				int numberOfJuniorMembers = 0;
				for (Member member : members) {
					if (member.getAge() < 18) {
						numberOfMembers++;
						numberOfJuniorMembers++;
					}
				}
				if (numberOfMembers == 0) {
					mainTextArea.setText("There are no members in the system");
				} else {
					mainTextArea.setText("The number of junior members is " + numberOfJuniorMembers);
				}
			}

			if (event.getSource() == mainTotalNumberOfMembers) {
				mainScroll.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(mainScroll);
				setContentPane(windowSurface);
				
				int totalNumberOfMembers = 0;
				for (Member member : members) {
					numberOfMembers++;
					totalNumberOfMembers++;
				}
				if (numberOfMembers == 0) {
					mainTextArea.setText("There are no members in the system");
				} else {
					mainTextArea.setText("The total number of members is " + totalNumberOfMembers);
				}
			}

			if (event.getSource() == mainMembersWhoHaventPaid) {
				mainScroll.setVisible(true);
				windowSurface = getContentPane();
				windowSurface.add(mainScroll);
				setContentPane(windowSurface);

				String membersWhoHaventPaid = "";
				for (Member member : members) {
					numberOfMembers++;
					if (member.getMembershipStatus().equals("due")) {
						membersWhoHaventPaid = membersWhoHaventPaid + "\n" + member.toString();
						mainTextArea.setText("The members who havent paid are \n" + membersWhoHaventPaid);
					}
					if (member.getMembershipStatus().equals("paid")) {
						mainTextArea.setText("All members have paid their membership");
					}

				}
				if (numberOfMembers == 0) {
					mainTextArea.setText("There are no members in the system");
				}
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a number for age and handicap fields", "Error", JOptionPane.ERROR_MESSAGE);

		}

		catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No member exists to edit", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void itemStateChanged(ItemEvent event) {
		if (event.getItem() == addMemberFemale) {
			addMemberGender = "female";
		}

		if (event.getItem() == addMemberMale) {
			addMemberGender = "male";
		}

	}

	public Member addMember() {
		Member memberToBeAdded = new Member();
		memberToBeAdded.setFName(addMemberFNameBox.getText());
		memberToBeAdded.setLName(addMemberLNameBox.getText());
		memberToBeAdded.setAddress(addMemberAddressBox.getText());
		memberToBeAdded.setContactNumber(addMemberContactBox.getText());
		memberToBeAdded.setAge(Integer.parseInt(addMemberAgeBox.getText()));
		memberToBeAdded.setGender(addMemberGender);
		memberToBeAdded.setHandicap(Integer.parseInt(addMemberHandicapBox.getText()));
		memberToBeAdded.setMembershipStatus(addMemberStatusBox.getText());
		members.add(memberToBeAdded);
		return memberToBeAdded;
	}

	public void savingFile() {
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("/Users/leanne/Documents/Programming/ClonmelGolfClubSystem/myObjects.dat"));
			objectOut.writeObject(members);
			objectOut.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can't find the file", "Error",	JOptionPane.ERROR_MESSAGE);

		}
	}
}
