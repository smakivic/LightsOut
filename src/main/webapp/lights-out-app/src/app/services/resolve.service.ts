import { Injectable } from '@angular/core';
import {Resolve} from "@angular/router";
import {Problem} from "../models/problem";
import {HttpService} from "./http.service";
import {Observable} from "rxjs";
import {ProblemsResponse} from "../models/problems-response";

@Injectable({
  providedIn: 'root'
})
export class ResolveService implements Resolve<ProblemsResponse> {
  constructor(private httpService: HttpService) {}

  resolve(): Observable<ProblemsResponse> {
    return this.httpService.getProblems();
  }
}
