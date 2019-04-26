package com.example.agright;

public class Plant {
    private String plantName;
    private String plantId;
    private String plantStatus;

    public Plant(){

    }

    public Plant(String plantName, String plantId, String plantStatus) {
        this.plantName = plantName;
        this.plantId = plantId;
        this.plantStatus = plantStatus;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }
}
