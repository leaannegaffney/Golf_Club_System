package clonmelGolfClubSystem;

import java.io.*;
public class Member implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String fName;
    private String lName;
    private String address;
    private int age;
    private String gender;
    private String contactNumber;
    private String membershipStatus = "paid";
    private int handicap;
    
    
    public void setFName(String fNameIn)
    {
        fName = fNameIn;
    }

    public void setLName(String lNameIn)
    {
        lName = lNameIn;
    }

    public void setAddress(String addressIn)
    {
        address = addressIn;
    }

    public void setAge(int ageIn)
    {
        age = ageIn;
    }
    
    public void setGender(String genderIn)
    {
       gender = genderIn; 
    }

    public void setContactNumber(String contactNumberIn)
    {
        contactNumber = contactNumberIn;
    }

    public void setMembershipStatus(String statusIn)
    {
        if(statusIn.equals(membershipStatus))
        {
            membershipStatus = "paid";
        }
        else
        {
            membershipStatus = "due"; 
        }
    }

    public void setHandicap(int handicapIn)
    {
        handicap = handicapIn;
    }

    public String getFName()
    {
        return fName;
    }

    public String getLName()
    {
        return lName;
    }

    public String getAddress()
    {
        return address;
    }

    public int getAge()
    {
        return age;
    }
    
    public String getGender()
    {
        return gender;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public String getMembershipStatus()
    {
        return membershipStatus;
    }

    public int getHandicap()
    {
        return handicap;
    }

    public String toString()
    {
        return "Name is " + getFName() + " " + getLName() +
        "\nAddress is " + getAddress() +
        "\nAge is " + getAge() +
        "\nContact Number is " + getContactNumber() +
        "\nGender is " + getGender() +
        "\nMembershipStatus is " + getMembershipStatus() +
        "\nHandicap is " + getHandicap();

    }
    
   
      
}

