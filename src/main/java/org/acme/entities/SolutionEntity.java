package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class SolutionEntity extends PanacheEntity {
    // Assuming your problemSolution is an array of integers
    //private int[] problemSolution;

    @OneToOne
    @JoinColumn(name = "problem_id") // Define the foreign key column name
    private ProblemEntity problem;

    // Constructors, getters, setters, etc.
/*
    public int[] getProblemSolution() {
        return problemSolution;
    }

    public void setProblemSolution(int[] problemSolution) {
        this.problemSolution = problemSolution;
    }
*/
    public ProblemEntity getProblem() {
        return problem;
    }

    public void setProblem(ProblemEntity problem) {
        this.problem = problem;
    }
}
