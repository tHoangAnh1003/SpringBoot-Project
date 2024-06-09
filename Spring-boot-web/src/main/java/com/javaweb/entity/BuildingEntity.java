package com.javaweb.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "district")
    private String district;
    @Column(name = "ward")
    private String ward;
    @Column(name = "street")
    private String street;
    @Column(name = "level")
    private String level;
    @Column(name = "direction")
    private String direction;
    @Column(name = "numberofbasement")
    private Long numberOfBasement;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhoneNumber;
    @Column(name = "floorarea")
    private Long floorArea;
    @Column(name = "rentprice")
    private Long rentPrice;
    @Column(name = "servicefee")
    private Long serviceFee;
    @Column(name = "brokeragefee")
    private Long brokerageFee;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "carfee")
    private Long carFee;
    @Column(name = "motofee")
    private Long motoFee;
    @Column(name = "overtimefee")
    private Long overtimeFee;
    @Column(name = "electricityfee")
    private Long electricityFee;
    @Column(name = "deposit")
    private Long deposit;
    @Column(name = "payment")
    private Long payment;
    @Column(name = "decorationtime")
    private String decorationTime;
    @Column(name = "note")
    private String note;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "structure")
    private String structure;
    @Column(name = "type")
    private String typeCode;

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getName() {
        return name;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public Long getCarFee() {
        return carFee;
    }

    public void setCarFee(Long carFee) {
        this.carFee = carFee;
    }

    public Long getMotoFee() {
        return motoFee;
    }

    public void setMotoFee(Long motoFee) {
        this.motoFee = motoFee;
    }

    public Long getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Long overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Long getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(Long electricityFee) {
        this.electricityFee = electricityFee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<RentEntity> rentEntities = new ArrayList<>();

    public List<RentEntity> getRentEntities() {
        return rentEntities;
    }

    public void setRentEntities(List<RentEntity> rentEntities) {
        this.rentEntities = rentEntities;
    }

    public List<AssignmentBuildingEntity> getAssignmentBuildingEntity() {
        return assignmentBuildingEntities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingEntity")
    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

    public void setAssignmentBuildingEntities(List<AssignmentBuildingEntity> assignmentBuildingEntities) {
        this.assignmentBuildingEntities = assignmentBuildingEntities;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<AssignmentBuildingEntity> getAssignmentBuildingEntities() {
        return assignmentBuildingEntities;
    }


}
