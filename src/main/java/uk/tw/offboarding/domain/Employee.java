package uk.tw.offboarding.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee {
    private int id;
    private String name;
    private Role role;
    private Grade grade;
    private Office office;
    private Date hireDate;
    //Get country from office
    public Country getCountry() {
        return office.getCountry();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", grade=" + grade +
                ", office=" + office +
                ", hireDate=" + hireDate +
                '}';
    }
}
