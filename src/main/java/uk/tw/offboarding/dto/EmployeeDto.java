package uk.tw.offboarding.dto;

public class EmployeeDto {
    private String lastWorkingDay;

    public EmployeeDto() {
    }

    public EmployeeDto(String message) {
        this.lastWorkingDay = message;
    }

    public String getLastWorkingDay() {
        return lastWorkingDay;
    }
}
