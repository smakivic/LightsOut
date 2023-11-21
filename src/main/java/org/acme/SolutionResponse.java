package org.acme;

import org.acme.entities.ProblemEntity;
import org.acme.entities.SolutionEntity;
import org.acme.entities.SolutionStepEntity;

import java.util.List;

public class SolutionResponse {
    public Long solutionId;
    public ProblemEntity problem;

    public List<SolutionStepEntity> solutionSteps;

    // Constructors, getters, setters, etc.
    public SolutionResponse(SolutionEntity solution, List<SolutionStepEntity> solutionSteps) {
        this.solutionId = solution.id;
        this.problem = solution.getProblem();
        this.solutionSteps = solutionSteps;
    }
}
