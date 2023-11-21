package org.acme;

import org.acme.core.base.GridInterface;
import org.acme.core.base.PatternInterface;
import org.acme.core.solver.Solver;
import org.acme.core.solver.solution.Solution;
import org.acme.core.utils.Coord;
import org.acme.core.utils.GridUtils;
import org.acme.core.utils.PatternUtils;
import org.acme.entities.ProblemEntity;
import org.acme.entities.SolutionStepEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolverWrapper {
    public List<SolutionStepEntity> solve(ProblemEntity problem) {


        //creating a custom grid from the problem definition
        List<Coord> koordinate = new ArrayList<>();
        int[] problemDefinition = problem.getProblemDefinition();
        int size = problem.getProblemSize();
        for (int i = 0; i < size * size; i++) {
            if (problemDefinition[i] == 1)
                koordinate.add(Coord.of(i / size, i % size));
        }

        GridInterface startGrid = GridUtils.getGridWithSomeActivatedCoords(size, size, koordinate);
        GridInterface finalGrid = GridUtils.getFullGrid(problem.getProblemSize(), problem.getProblemSize());
        PatternInterface pattern = PatternUtils.getClassicPattern();
        Solver solver = new Solver(startGrid, finalGrid, pattern);
        // compute only the first one solution (if exists)
        Optional<Solution> firstSolution = solver.findFirstSolution();
        if (firstSolution.isEmpty()) {
            return null;
        }
        List<Integer> coords = firstSolution.get().coordinatesToArray();
        List<SolutionStepEntity> steps = new ArrayList<>();
        for (int i = 0; i < coords.size(); i++) {
            int n = coords.get(i);
            SolutionStepEntity step = new SolutionStepEntity(n, i);
            step.persist();
            steps.add(step);
        }

        return steps;
    }

    public static void main(String[] args) {

    }
}
