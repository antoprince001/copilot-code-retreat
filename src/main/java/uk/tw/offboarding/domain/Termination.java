package uk.tw.offboarding.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.List;

@Data
public class Termination {
    private int id;
    private Employee employee;
    private Date lastWorkingDate;
    // Default value of reason is null when not provided in Request body
    private String reason = "";
    private Date initiatedOn;
    private TerminationStatus status;
    private List<EmployeeExitChecklistItem> exitChecklist = List.of();
    private String notes;

    @Override
    public String toString() {
        return "Termination{" +
                "ID=" + id +
                ", employee=" + employee +
                ", lastWorkingDate=" + lastWorkingDate +
                ", reason='" + reason + '\'' +
                ", initiatedOn=" + initiatedOn +
                ", status=" + status +
                ", exitChecklist=" + exitChecklist +
                ", notes='" + notes + '\'' +
                '}';
    }
}

// create a sample json for Termination
// {
//     "ID": 1,
//     "employee": {
//         "ID": 1,
//         "name": "John",
//         "role": "DEVELOPER",
//         "grade": "CONSULTANT",
//         "office": "SYDNEY",
//         "hireDate": "2018-12-01",
//     },
//     "lastWorkingDate": "2019-12-01",
//     "reason": "Reason 1",
//     "initiatedOn": "2019-12-01",
//     "status": "SUBMITTED",
//     "exitChecklist": [
//         {
//             "ID": 1,
//             "name": "Laptop",
//             "status": "NOT_APPLICABLE"
//         },
//         {
//             "ID": 2,
//             "name": "Monitor",
//             "status": "NOT_APPLICABLE"
//         }
//     ]
// }
