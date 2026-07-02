import { AuthService } from './service/auth-service';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navigation } from './component/navigation/navigation';

@Component({
  selector: 'app-root',
  imports: [ RouterOutlet, Navigation ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected authService: AuthService = inject(AuthService)
}
