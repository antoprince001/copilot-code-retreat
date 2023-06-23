package uk.tw.offboarding.domain;

import lombok.Getter;

@Getter
public enum ExitChecklistItem {
    DESK_KEYS(1, "Desk keys", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    FILE_CABINET_KEYS(2, "File cabinet keys", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    ACCESS_CARD(3, "Access card", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    CELL_PHONE(4, "Cell phone", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    SIM_CARD(5, "SIM card", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    LIBRARY_BOOKS(6, "Library books", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.COUNTRY, 4),
    SEZ_CARD(7, "SEZ card", ExitChecklistItemCategory.ADMIN, ExitChecklistItemType.OFFICE, 8),
    LAPTOP(8, "Laptop", ExitChecklistItemCategory.TECHOPS, ExitChecklistItemType.COUNTRY, 4),
    MONITORS(9, "Monitors", ExitChecklistItemCategory.TECHOPS, ExitChecklistItemType.COUNTRY, 4),
    CHARGER(10, "Charger", ExitChecklistItemCategory.TECHOPS, ExitChecklistItemType.COUNTRY, 4),
    RSA_FOB(11, "RSA FOB", ExitChecklistItemCategory.TECHOPS, ExitChecklistItemType.COUNTRY, 4),
    OUTSTANDING_EXPENSES(12, "Outstanding expenses", ExitChecklistItemCategory.FINANCE, ExitChecklistItemType.COUNTRY, 4),
    SODEXO_REIMBURSEMENT(13, "Sodexo reimbursement", ExitChecklistItemCategory.FINANCE, ExitChecklistItemType.COUNTRY, 4),
    EXIT_SURVEY(13, "Exit survey", ExitChecklistItemCategory.PEOPLE_TEAM, ExitChecklistItemType.COUNTRY, 4),
    LEAVE_ENCASHMENT(14, "Leave encashment", ExitChecklistItemCategory.PEOPLE_TEAM, ExitChecklistItemType.COUNTRY, 4),
    GRATUITY(15, "Gratuity", ExitChecklistItemCategory.PEOPLE_TEAM, ExitChecklistItemType.COUNTRY, 4),
    ;
    private int ID;
    private String description;
    private ExitChecklistItemCategory category;
    private ExitChecklistItemType regionType;
    private int regionID;

    ExitChecklistItem(int ID, String description, ExitChecklistItemCategory category, ExitChecklistItemType regionType, int regionID) {
        this.ID = ID;
        this.description = description;
        this.category = category;
        this.regionType = regionType;
        this.regionID = regionID;
    }
}
