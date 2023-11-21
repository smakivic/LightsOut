package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.ProblemEntity;
import org.acme.entities.SolutionEntity;
import org.acme.entities.SolutionStepEntity;

import java.util.Arrays;
import java.util.List;

//custom documentation



@Path("/solutions")
public class SolutionResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<SolutionResponse> getSolutions() {
        List<SolutionEntity> solutions = SolutionEntity.listAll(); // Get all solutions
        List<SolutionResponse> response = Arrays.asList(new SolutionResponse[solutions.size()]);// Create a list of SolutionResponse objects
        for (int i = 0; i < solutions.size(); i++) {
            SolutionEntity solution = solutions.get(i);
            List<SolutionStepEntity> steps = SolutionStepEntity.find("solution.id", solution.id).list();
            response.set(i, new SolutionResponse(solution, steps));
        }
        return response;
    }

    @GET
    @Path("/problem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SolutionResponse getSolutionByProblemID(@PathParam("id") Long id) {
        SolutionEntity solution = SolutionEntity.find("problem.id", id).firstResult();
        List<SolutionStepEntity> steps = SolutionStepEntity.find("solution.id", solution.id).list();
        return new SolutionResponse(solution, steps);
    }


    @POST
    @Path("/problem/{id}")
    @Consumes(MediaType.APPLICATION_JSON) // Assuming you are sending JSON in the request body
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public SolutionEntity createSolution(SolutionEntity solution, @PathParam("id") Long id) {
        ProblemEntity problem = ProblemEntity.findById(id);
        solution.setProblem(problem);
        solution.persist();
        return solution;
    }

}
