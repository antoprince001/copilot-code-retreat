package uk.tw.offboarding;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.tw.offboarding.domain.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class SeedingApplicationDataConfiguration {

    @Bean
    public List<Employee> getEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        int index = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-12-01");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        for (Office office: Office.values()) {
            for (Role role: Role.values()) {
                for (Grade grade: Grade.values()) {
                    Employee employee = new Employee();
                    employee.setId(++index);
                    employee.setGrade(grade);
                    employee.setName("Employee "+index);
                    employee.setOffice(office);
                    employee.setRole(role);
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    employee.setHireDate(cal.getTime());
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    //generate Terminations using the getEmployees method
    @Bean
    public List<Termination> getTerminations() throws Exception {
        List<Employee> employees = getEmployees();
        List<Termination> terminations = new ArrayList<>();
        int index = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-12-01");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        for (Employee employee: employees) {
            Termination termination = new Termination();
            termination.setId(++index);
            termination.setEmployee(employee);
            termination.setLastWorkingDate(cal.getTime());
            termination.setReason("Reason "+index);
            termination.setInitiatedOn(new Date());
            termination.setStatus(TerminationStatus.SUBMITTED);
            terminations.add(termination);
        }
        return terminations;
    }


}
