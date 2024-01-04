import { Component } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  constructor(private loginService: LoginService, private router: Router) {}

  logout(): void {
    this.loginService.logout();
    window.location.reload();
  }

  home(): void {
    this.router.navigate(['/home']);
  }

  sobre(): void {
    this.router.navigate(['/sobre']);
  }
}
