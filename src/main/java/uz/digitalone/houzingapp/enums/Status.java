package uz.digitalone.houzingapp.enums;

public enum Status {

    SALE("for_sale"),
    RENT("for_rent");

    private final String description;


    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Status getStatus(String status){
        for (Status houseStatus : values()){
            if (houseStatus.getDescription().equals(status)){
                return houseStatus;
            }
        }
        throw new IllegalArgumentException(status + "is not a valid status");
    }
}
