import {Problem} from "./problem";
import {SolutionStep} from "./solution-step";

export class Solution {
    solutionId: number = 0;
    problem: Problem = new Problem();
    solutionSteps: SolutionStep[] = [];
}
