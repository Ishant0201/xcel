import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from "./shared/components/footer/footer.component";
import { HeaderComponent } from "./shared/components/header/header.component";
import { BankEvaluationApiService } from './shared/services/bank-evaluation-api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  imports: [RouterOutlet, FooterComponent, HeaderComponent],
  providers: [BankEvaluationApiService]
})
export class AppComponent {
  title = 'bankPropertyEvalutionUI';
}
