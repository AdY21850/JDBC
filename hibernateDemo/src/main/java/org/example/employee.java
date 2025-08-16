package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class employee {
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    private int salary;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Id
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    private String job;


    @Override
    public String toString() {
        return "employee{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
