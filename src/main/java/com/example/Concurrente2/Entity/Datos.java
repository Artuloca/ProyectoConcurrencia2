package com.example.Concurrente2.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "datos")
public class Datos {

    //Antes de importar los datos, se debe crear la tabla 'datos' en la base de datos.
    //Y usar la siguiente Query para modificar la columna 'id' para que sea autoincremental.
    //ALTER TABLE datos MODIFY COLUMN id BIGInteger AUTO_INCREMENT;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    private String workclass;
    private Integer fnlwgt;
    private String education;
    private Integer education_num;
    private String marital_status;
    private String occupation;
    private String relationship;
    private String race;
    private String sex;
    private Integer capital_gain;
    private Integer capital_loss;
    private Integer hours_per_week;
    private String native_country;
    private String income;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWorkclass() {
        return workclass;
    }

    public void setWorkclass(String workclass) {
        this.workclass = workclass;
    }

    public Integer getFnlwgt() {
        return fnlwgt;
    }

    public void setFnlwgt(Integer fnlwgt) {
        this.fnlwgt = fnlwgt;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getEducation_num() {
        return education_num;
    }

    public void setEducation_num(Integer education_num) {
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

    public Integer getCapital_gain() {
        return capital_gain;
    }

    public void setCapital_gain(Integer capital_gain) {
        this.capital_gain = capital_gain;
    }

    public Integer getCapital_loss() {
        return capital_loss;
    }

    public void setCapital_loss(Integer capital_loss) {
        this.capital_loss = capital_loss;
    }

    public Integer getHours_per_week() {
        return hours_per_week;
    }

    public void setHours_per_week(Integer hours_per_week) {
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

    @Override
    public String toString() {
        return "Datos{" +
                "id=" + id +
                ", age=" + age +
                ", workclass='" + workclass + '\'' +
                ", fnlwgt=" + fnlwgt +
                ", education='" + education + '\'' +
                ", education_num=" + education_num +
                ", marital_status='" + marital_status + '\'' +
                ", occupation='" + occupation + '\'' +
                ", relationship='" + relationship + '\'' +
                ", race='" + race + '\'' +
                ", sex='" + sex + '\'' +
                ", capital_gain=" + capital_gain +
                ", capital_loss=" + capital_loss +
                ", hours_per_week=" + hours_per_week +
                ", native_country='" + native_country + '\'' +
                ", income='" + income + '\'' +
                '}';
    }


}
