import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter, RouteReuseStrategy} from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import {HttpClientModule, provideHttpClient} from "@angular/common/http";
import {provideToastr} from "ngx-toastr";
import {ScrollTopService} from "./services/scroll-top.service";


export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(),
    provideToastr(),
    ScrollTopService]
};
