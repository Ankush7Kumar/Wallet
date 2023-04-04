package model;




import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    int customerId;
    String firstName;
    String lastName;
    String emailId;
    String contactNo;

    @OneToOne
    @JoinColumn(name = "addressFk")
    Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    String password;
    @Transient
    String confirmPassword;
    private LocalDate registerationDate;

    @OneToMany
    private List<Account> accoutns = new ArrayList();

    public Customer() {
    }

    public Customer(int customerId, String firstName, String lastName, String emailId, String contactNo, Address address, Gender gender, String password, String confirmPassword, LocalDate registerationDate, List<Account> accoutns) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.address = address;
        this.gender = gender;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.registerationDate = registerationDate;
        this.accoutns = accoutns;
    }

    public Customer(int customerId, String firstName, String lastName, String emailId, String contactNo, Address address, Gender gender, String password, String confirmPassword, LocalDate registerationDate) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.address = address;
        this.gender = gender;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.registerationDate = registerationDate;
    }

    public String toString() {
        return "Customer [customerId=" + this.customerId + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", emailId=" + this.emailId + ", contactNo=" + this.contactNo + ", address=" + this.address + ", gender=" + this.gender + ", password=" + this.password + ", confirmPassword=" + this.confirmPassword + ", registerationDate=" + this.registerationDate + ", accoutns=" + this.accoutns + "]";
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDate getRegisterationDate() {
        return this.registerationDate;
    }

    public void setRegisterationDate(LocalDate registerationDate) {
        this.registerationDate = registerationDate;
    }

    public List<Account> getAccoutns() {
        return this.accoutns;
    }

    public void setAccoutns(List<Account> accoutns) {
        this.accoutns = accoutns;
    }
}
