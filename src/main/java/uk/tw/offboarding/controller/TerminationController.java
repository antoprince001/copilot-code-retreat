package uk.tw.offboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.tw.offboarding.SeedingApplicationDataConfiguration;
import uk.tw.offboarding.domain.Employee;
import uk.tw.offboarding.domain.EmployeeExitChecklistItem;
import uk.tw.offboarding.domain.Termination;
import uk.tw.offboarding.domain.TerminationStatus;
import uk.tw.offboarding.dto.EmployeeDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TerminationController {

    @Autowired
    SeedingApplicationDataConfiguration seedingApplicationDataConfiguration;

    // GET endpoint to return 200 status code and JSON response body as a list of employees
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/terminations")
    public ResponseEntity getEmployees() throws Exception {
        List<Employee> employees = seedingApplicationDataConfiguration.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

    // GET endpoint to return 200 status code and JSON response body as a single employee
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/terminations/{id}")
    public ResponseEntity getEmployee(@PathVariable long id) throws Exception {
        List<Employee> employees = seedingApplicationDataConfiguration.getEmployees();
        Employee employee = employees.get((int) id - 1);
        return ResponseEntity.ok().body(employee);
    }

    //Patch endpoint to accept last day of work and return 200 status code
    @CrossOrigin(origins = "http://localhost:8081")
    @PatchMapping("/terminations/{id}")
    public ResponseEntity patchEmployees(@RequestBody EmployeeDto employeeDto, @PathVariable long id) throws Exception {
        //Find all terminations with employee id
        List<Termination> terminations = seedingApplicationDataConfiguration.getTerminations();
        for (Termination termination : terminations) {
            if (termination.getId() == id) {
                //convert string to Date last working date for employee
                Date lastWorkingDate = new SimpleDateFormat("yyyy-MM-dd").parse(employeeDto.getLastWorkingDay());
                termination.setLastWorkingDate(lastWorkingDate);
            }
        }
        System.out.println("Last working day is " + employeeDto.getLastWorkingDay() + " for employee " + id);
        return ResponseEntity.ok().build();
    }

    // POST endpoint to accept new termination and return 201 status code
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/terminations")
    public ResponseEntity postEmployees(@RequestBody Termination termination) throws Exception {
        //Find all terminations with employee id
        List<Termination> terminations = seedingApplicationDataConfiguration.getTerminations();
        termination.setStatus(TerminationStatus.SUBMITTED);
        terminations.add(termination);
        return ResponseEntity.ok().build();
    }

    // PATCH endpoint on /terminations/{id}/accept to accept termination id, notes string and return 200 status code
    @CrossOrigin(origins = "http://localhost:8081")
    @PatchMapping("/terminations/{id}/accept")
    public ResponseEntity patchEmployees(@RequestBody String notes, @PathVariable long id) throws Exception {
        //Find first terminations with employee id and update its notes and status using java streams
        List<Termination> terminations = seedingApplicationDataConfiguration.getTerminations();
        terminations.stream()
                .filter(termination -> termination.getId() == id)
                .findFirst()
                .ifPresent(termination -> {
                    termination.setNotes(notes);
                    termination.setStatus(TerminationStatus.ACCEPTED);
                });
        System.out.println("Termination " + id + " has been accepted with notes " + notes);
        return ResponseEntity.ok().build();
    }

    // PATCH endpoint on /terminations/{id}/initiate-checklist to accept termination id and return 200 status code
    // Should identify the country to which the employee belongs to and trigger the country specific exit checklist for that employee
    @CrossOrigin(origins = "http://localhost:8081")
    @PatchMapping("/terminations/{id}/initiate-checklist")
    public ResponseEntity patchEmployees(@PathVariable long id) throws Exception {
        //Find first terminations with termination id and update its exit checklist based on the country in office using java streams
        List<Termination> terminations = seedingApplicationDataConfiguration.getTerminations();
        terminations.stream()
                .filter(termination -> termination.getId() == id)
                .findFirst()
                .ifPresent(termination -> {
                    termination.setExitChecklist(
                            termination.getEmployee().getCountry().getExitChecklist().stream()
                                    .map(exitChecklistItem -> {
                                        // create a new EmployeeExitChecklistItem object
                                        return EmployeeExitChecklistItem.builder()
                                                .terminationID((int) id)
                                                .completedOn(null)
                                                .exitChecklistItemID(exitChecklistItem.getID())
                                                .isCompleted(false)
                                                .build();

                                    })
                                    .toList()
                    );
                });
        System.out.println("Termination " + id + " checklist has been initiated");
        return ResponseEntity.ok().build();
    }

}
