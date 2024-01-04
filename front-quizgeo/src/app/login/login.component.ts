import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  senha = '';
  loginInvalido = false;

  constructor(private router: Router, private loginService: LoginService, private cookieService: CookieService) {}

  login(): void {
    this.loginService.login(this.email, this.senha).subscribe(
      (res) => {
        if (res.token) {
          this.loginService.autenticado = true;
          this.router.navigate(['/home'])
        }
      },
      (err) => {
        this.loginInvalido = true;
      }
    );
  }


  registrar(): void {
    this.router.navigate(['/registro']);
  }
}
