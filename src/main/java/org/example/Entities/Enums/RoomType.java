package org.example.Entities.Enums;

public enum RoomType {
    SINGLE ("Single", 20),
    TWIN("Twin" , 35),
    MATRIMONIAL("Matrimonial", 45),
    TRIPLE("Triple", 55),
    QUAD("Quad", 65);

    private final String key;
    private final Integer value;

    RoomType (String key, Integer value){
        this.key=key;
        this.value=value;

    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
