package com.example.Concurrente2.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "datos")
public class Datos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int age;

    private String workclass;

    private int fnlwgt;

    private String education;

    private int education_num;

    private String marital_status;

    private String occupation;

    private String relationship;

    private String race;

    private String sex;

    private int capital_gain;

    private int capital_loss;

    private int hours_per_week;

    private String native_country;

    private String income;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWorkclass() {
        return workclass;
    }

    public void setWorkclass(String workclass) {
        this.workclass = workclass;
    }

    public int getFnlwgt() {
        return fnlwgt;
    }

    public void setFnlwgt(int fnlwgt) {
        this.fnlwgt = fnlwgt;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getEducation_num() {
        return education_num;
    }

    public void setEducation_num(int education_num) {
        this.education_num = education_num;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getCapital_gain() {
        return capital_gain;
    }

    public void setCapital_gain(int capital_gain) {
        this.capital_gain = capital_gain;
    }

    public int getCapital_loss() {
        return capital_loss;
    }

    public void setCapital_loss(int capital_loss) {
        this.capital_loss = capital_loss;
    }

    public int getHours_per_week() {
        return hours_per_week;
    }

    public void setHours_per_week(int hours_per_week) {
        this.hours_per_week = hours_per_week;
    }


    public String getNative_country() {
        return native_country;
    }

    public void setNative_country(String native_country) {
        this.native_country = native_country;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }


}
