import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpService} from "../../services/http.service";
import {Problem} from "../../models/problem";
import {Solution} from "../../models/solution";
import {SolutionStep} from "../../models/solution-step";

@Component({
    selector: 'app-solve-problem',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './solve-problem.component.html',
    styleUrl: './solve-problem.component.css'
})
export class SolveProblemComponent implements OnInit{

    constructor(private httpService: HttpService,
                private router: Router,
                private route: ActivatedRoute) {
    }
    solved = false;

    id = 1;
    // initialize empty problem and soulution
    problem: Problem = {
        problemDefinition: [],
        problemSize: 0,
        id: 0
    };
    solution: Solution = {
        solutionId: 0,
        problem: new Problem(),
        solutionSteps: []
    };

    /*makes an array with all fieldindexes from solution*/
    solutionSteps: number[] = [];
    matrix: Cell[][] = [];
    ngOnInit() {
        // Subscribe to the route parameters observable
        this.solved = false;
        this.route.params.subscribe(params => {
            // Access the 'id' parameter
            this.id = params['id'];
        });
        this.httpService.getProblemById(this.id).subscribe({
            next: problem => {
                this.problem = problem;
                this.fillMatrix();
            },
            error: err => {
                console.log(err)
            }
        });


    }

    fillMatrix(){
        this.matrix = Array.from({length: this.problem.problemSize}, () => Array(this.problem.problemSize).fill({value: 0, bordered: false}));
        this.setMatrix();
    }

     setMatrix() {
        for (let i = 0; i < this.problem.problemSize; i++) {
            for (let j = 0; j < this.problem.problemSize; j++) {
                //this.matrix[i][j] = this.getCell(i, j);
                this.matrix[i][j] = {
                    value: this.problem.problemDefinition[i * this.problem.problemSize + j],
                    bordered: false
                };
            }
        }
    }

    getSolution() {
        this.setMatrix();
        this.httpService.getSolutionByProblemId(this.id).subscribe({
            next: solution => {
                this.solution = solution;
                this.setBorders();
            },
            error: err => {
                console.log(err)
            }
        });
    }

    setBorders() {
        this.solutionSteps = this.solution.solutionSteps.map(solutionStep => solutionStep.field_index);
        this.solutionSteps.forEach(solutionStep => {
            const row = Math.floor(solutionStep / this.problem.problemSize);
            const col = solutionStep % this.problem.problemSize;
            this.matrix[row][col].bordered = true;
        });
    }
    toggleValues(row: number, col: number) {

        let currentValue = this.matrix[row][col].value;
        this.matrix[row][col].value = currentValue === 0 ? 1 : 0;
        if (row - 1 >= 0) {
            currentValue = this.matrix[row - 1][col].value;
            this.matrix[row - 1][col].value = currentValue === 0 ? 1 : 0;
        }
        if (row + 1 < this.problem.problemSize) {
            currentValue = this.matrix[row + 1][col].value;
            this.matrix[row + 1][col].value = currentValue === 0 ? 1 : 0;
        }
        if (col - 1 >= 0) {
            currentValue = this.matrix[row][col - 1].value;
            this.matrix[row][col - 1].value = currentValue === 0 ? 1 : 0;
        }
        if (col + 1 < this.problem.problemSize) {
            currentValue = this.matrix[row][col + 1].value;
            this.matrix[row][col + 1].value = currentValue === 0 ? 1 : 0;
        }

        this.matrix[row][col].bordered = false;

        setTimeout(() => {
            if (this.checkIfSolved()) {
                alert("You solved the problem!");
                this.router.navigate(['/problems']);
                window.scrollTo(0, 0);
            }
        }, 10);

    }
    private checkIfSolved() {
        if (this.matrix.every(row => row.every(col => col.value === 1))) {
            this.solved = true;
            return true;
        }
        return false;
    }
}

type Cell = {
    value: number,
    bordered: boolean
}
