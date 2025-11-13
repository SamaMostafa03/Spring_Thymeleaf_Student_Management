package com.sama.Spring_Thymleaf_Student_Management.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentIdSequence {
    @Id
    private int year;
    private long count;

    public StudentIdSequence() {}

    public StudentIdSequence(int year, long count) {
        this.year = year;
        this.count = count;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
