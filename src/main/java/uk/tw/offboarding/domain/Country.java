package uk.tw.offboarding.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Country {
    UNITED_KINGDOM(1,"United Kingdom", "GBR", Status.ACTIVE),
    UNITED_STATES(2, "United States", "USA", Status.ACTIVE),
    AUSTRALIA(3, "Australia", "AUS", Status.ACTIVE),
    INDIA(4, "India", "IND", Status.ACTIVE),
    GERMANY(5, "Germany", "DEU", Status.ACTIVE),
    CHINA(6, "China", "CHN", Status.ACTIVE),
    BRAZIL(7, "Brazil", "BRA", Status.ACTIVE),
    CANADA(8, "Canada", "CAN", Status.CLOSED)
    ;
    private int ID;
    private String name;
    private String iso3Code;
    private Status status;

    Country(int ID, String name, String iso3Code, Status status) {
        this.ID = ID;
        this.name = name;
        this.iso3Code = iso3Code;
        this.status = status;
    }

    public List<ExitChecklistItem> getExitChecklist() {
        // Find all ExitChecklistItems of ExitChecklistItemType COUNTRY
        List<ExitChecklistItem> exitChecklistItems = Arrays.stream(ExitChecklistItem.values())
                .filter(exitChecklistItem -> exitChecklistItem.getRegionType() == ExitChecklistItemType.COUNTRY)
                .toList();

        // Filter exitChecklistItems based on this country id
        return exitChecklistItems.stream()
                .filter(exitChecklistItem -> exitChecklistItem.getRegionID() == this.ID)
                .toList();

    }
}
