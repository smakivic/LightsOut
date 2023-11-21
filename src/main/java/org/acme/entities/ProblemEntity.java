package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProblemEntity extends PanacheEntity {
    private int[] problemDefinition;
    private int problemSize;
    // Constructors, getters, setters, etc.
    public ProblemEntity(int[] problemDefinition, int problemSize) {
        this.problemDefinition = problemDefinition;
        this.problemSize = problemSize;
    }
    //setters and getters
    public int[] getProblemDefinition() {
        return problemDefinition;
    }
    public void setProblemDefinition(int[] problemDefinition) {
        this.problemDefinition = problemDefinition;
    }
    public int getProblemSize() {
        return problemSize;
    }
    public void setProblemSize(int problemSize) {
        this.problemSize = problemSize;
    }

    public ProblemEntity() {

    }
}
