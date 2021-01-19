package lk.ijse.dep.orm.util;

import lk.ijse.dep.orm.annotation.Column;
import lk.ijse.dep.orm.annotation.Entity;
import lk.ijse.dep.orm.annotation.Id;

import java.io.Serializable;

/**
 * @author : Deshan Charuka <d.c.0729439631@gmail.com>
 * @since : 2021-01-20
 **/
@Entity
public class Customer implements Serializable {
    @Id
    @Column("id")
    private String cusId;
    @Column
    private String address;
    @Column
    private double salary;
    private String region;

    public Customer() {
    }

    public Customer(String cusId, String address, Double salary, String region) {
        this.cusId = cusId;
        this.address = address;
        this.salary = salary;
        this.region = region;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", region='" + region + '\'' +
                '}';
    }
}
