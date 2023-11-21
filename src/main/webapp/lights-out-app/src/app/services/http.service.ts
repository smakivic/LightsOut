import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Problem} from "../models/problem";
import {ProblemsResponse} from "../models/problems-response";
import {HttpClient} from "@angular/common/http";
import {Message} from "../models/message";
import {Solution} from "../models/solution";

@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient) {
    }

    apiUrl = "http://localhost:8080";

    getProblems(): Observable<ProblemsResponse> {
        return this.http.get<ProblemsResponse>(this.apiUrl + "/problems");
    }

    createProblem(problem: Problem): Observable<Message> {
        return this.http.post<Message>(this.apiUrl + "/problems", problem);
    }

    getProblemById(id: number): Observable<Problem> {
        return this.http.get<Problem>(this.apiUrl + "/problems/" + id);
    }

    getSolutions(): Observable<Solution[]> {
        return this.http.get<Solution[]>(this.apiUrl + "/solutions");
    }

    getSolutionByProblemId(problemId: number): Observable<Solution> {
        return this.http.get<Solution>(this.apiUrl + "/solutions/problem/" + problemId);
    }
}
