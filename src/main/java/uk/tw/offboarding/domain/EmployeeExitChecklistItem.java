package uk.tw.offboarding.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeExitChecklistItem {
    private int terminationID;
    private int exitChecklistItemID;
    private boolean isCompleted;
    private Date completedOn;
}
