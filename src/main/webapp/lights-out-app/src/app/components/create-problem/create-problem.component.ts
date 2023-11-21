import {Component, OnInit, SimpleChanges} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { Router } from '@angular/router';
import {HttpService} from "../../services/http.service";


@Component({
    selector: 'app-create-problem',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, FormsModule],
    templateUrl: './create-problem.component.html',
    styleUrl: './create-problem.component.css',

})
export class CreateProblemComponent {

    matrixSize: number = 4;
    sizes = [3, 4, 5, 6, 7, 8];
    matrix: number[][] = Array.from({length: this.matrixSize}, () => Array(this.matrixSize).fill(0));

    constructor(private httpService: HttpService,
                private router: Router,
                ) {
    }

    toggleValue(row: number, col: number) {
        const currentValue = this.matrix[row][col];
        this.matrix[row][col] = currentValue === 0 ? 1 : 0;
    }

    resetProblem() {
        for (let i = 0; i < this.matrixSize; i++) {
            this.matrix[i] = [];
            for (let j = 0; j < this.matrixSize; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    createProblem() {
        //flatten the matrix
        let flatMatrix = this.matrix.reduce((acc, val) => acc.concat(val), []);

        const jsonObject = {
            problemDefinition: flatMatrix,
            problemSize: this.matrixSize,
        };
        this.httpService.createProblem(jsonObject).subscribe({
            next: message => {
                console.log(message);
                alert("Problem created successfully!")
                this.router.navigate(['/problems']);
                window.scrollTo(0, 0);
            },
            error: err => {
                console.log(err);
                alert("Problem is not solvable!")
                this.resetProblem();
            }
        });
    }


    updateMatrix() {
        // Check if matrixSize is a valid positive integer
        this.matrix = [];

        // Initialize the matrix with all zeroes
        for (let i = 0; i < this.matrixSize; i++) {
            this.matrix[i] = [];
            for (let j = 0; j < this.matrixSize; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }
}
