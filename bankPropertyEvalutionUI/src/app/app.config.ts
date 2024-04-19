import { ApplicationConfig, Component } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { BankEvaluationApiService } from './shared/services/bank-evaluation-api.service';
import { HttpClientModule } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
  provideAnimationsAsync(),
  provideHttpClient(
    withInterceptorsFromDi()
  ),
    BankEvaluationApiService,
    HttpClientModule]
};
