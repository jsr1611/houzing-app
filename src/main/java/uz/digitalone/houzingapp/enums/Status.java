package uz.digitalone.houzingapp.enums;

public enum Status {

    FOR_SALE("for_sale"),
    FOR_RENT("for_rent"),
    FOR_PENDING("for_pending");

    private final String description;


    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Status getStatus(String isSolid){
        for(Status status : values()){
            if(status.getDescription().equals(isSolid)){
                return status;
            }
        }
        throw new IllegalArgumentException(isSolid + " is not a valid plant size");
    }
}
