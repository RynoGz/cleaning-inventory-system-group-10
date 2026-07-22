package za.belgiumcampus.cleaninginventory.model;

public class Cleaner {
    
     private int cleanerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private boolean isActive;

    public Cleaner(){}

    //Add CONSTRUCTOR
    public Cleaner(String firstName, String lastName, String email, String phone, String department, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.isActive = isActive;
    }
    //Fetch CONSTRUCTOR
    public Cleaner(int cleanerId, String firstName, String lastName, String email, String phone, String department, boolean isActive) {
        this.cleanerId = cleanerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.isActive = isActive;
    }
    //GETTERS AND SETTERS

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
