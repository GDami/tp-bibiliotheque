import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'ajc-navigation',
  imports: [ RouterLink ],
  templateUrl: './navigation.html',
  styleUrl: './navigation.css',
})
export class Navigation {
  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);

  public disconnect() {
    this.authService.disconnect();
    this.router.navigate([ 'login' ]);
  }
}
