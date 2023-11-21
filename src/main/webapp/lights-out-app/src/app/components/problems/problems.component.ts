import {Component, NgZone, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {HttpService} from "../../services/http.service";
import {Problem} from "../../models/problem";

@Component({
  selector: 'app-problems',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './problems.component.html',
  styleUrl: './problems.component.css'
})


export class ProblemsComponent implements OnInit{
  constructor(private httpService: HttpService) {}
  problemList: Problem[] = [];
    problem: Problem = {
        problemDefinition: [],
        problemSize: 0,
        id: 0
    };
    matrix: Cell[][] = [];
    fillMatrix(problem: Problem){
        this.problem = problem;
        this.matrix = Array.from({length: this.problem.problemSize}, () => Array(this.problem.problemSize).fill({value: 0, bordered: false}));
        console.log("Matrix:");
        console.log(this.matrix);
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
  ngOnInit(): void {
      this.httpService.getProblems().subscribe({
          next: problems => {
              this.problemList = problems.problems;
              this.problemList.sort((a, b) => (a.problemSize > b.problemSize) ? 1 : -1);
          },
          error: err => {
              console.log(err)
          }
      });
  }
}
type Cell = {
    value: number,
    bordered: boolean
}
