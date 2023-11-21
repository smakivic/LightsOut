package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.ProblemEntity;
import org.acme.entities.SolutionEntity;
import org.acme.entities.SolutionStepEntity;

import java.util.List;

@Path("/problems")

public class ProblemResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ProblemListResponse getProblems() {
        List<ProblemEntity> problems = ProblemEntity.listAll();

        ProblemListResponse response = new ProblemListResponse();
        response.count = problems.size();
        response.problems = problems;

        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ProblemEntity getProblemByID(@PathParam("id") int id) {
        return ProblemEntity.findById(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProblem(ProblemEntity problem) {
        SolverWrapper solver = new SolverWrapper();
        List<SolutionStepEntity> steps = solver.solve(problem);

        if (steps == null) {
            String jsonResult = "{\"message\": \"Problem is not solvable.\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResult).build();
        }
        problem.persist();
        SolutionEntity solution = new SolutionEntity();
        solution.setProblem(problem);
        solution.persist();

        for (SolutionStepEntity step : steps) {
            step.setSolution(solution);
        }

        String jsonResult = "{\"message\": \"Problem created with ID: " + problem.id + "\"}";

        return Response.ok(jsonResult).build();
    }
}
