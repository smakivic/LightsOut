import { Routes} from '@angular/router';
import {CreateProblemComponent} from "./components/create-problem/create-problem.component";
import {ProblemsComponent} from "./components/problems/problems.component";
import {SolveProblemComponent} from "./components/solve-problem/solve-problem.component";
import {ResolveService} from "./services/resolve.service";
import {HomeComponent} from "./components/home/home.component";

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'problems', component: ProblemsComponent },
    { path: 'create-problem', component: CreateProblemComponent },
    { path: 'solve-problem/:id', component: SolveProblemComponent },
    { path: 'problems', component: ProblemsComponent, resolve: {problems: ResolveService}},
];

