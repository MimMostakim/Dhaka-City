package com.example.mim.dhakacity;

public class Hospit {

    private String id;
    private String hospitalsName;
    private String hospitalAddress;
    private String hospitalContanctNo;

    public Hospit(String id, String hospitalsName,String hospitalAddress,String hospitalContanctNo) {
        this.id = id;
        this.hospitalsName = hospitalsName;
        this.hospitalAddress=hospitalAddress;
        this.hospitalContanctNo=hospitalContanctNo;
    }

    public Hospit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalsName() {
        return hospitalsName;
    }

    public void setHospitalsName(String hospitalsName) {
        this.hospitalsName = hospitalsName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalContanctNo() {
        return hospitalContanctNo;
    }

    public void setHospitalContanctNo(String hospitalContanctNo) {
        this.hospitalContanctNo = hospitalContanctNo;
    }
}
