import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  autenticado = false;
  baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  login(email: string, senha: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { email, senha })
    .pipe(
      tap((res: any) => {
        if (res && res.token) {
          this.cookieService.set('token', res.token);
          this.cookieService.set('email', email);
        }
      })
    );
  }

  logout(): void {
    this.cookieService.deleteAll();
    this.autenticado = false;
  }

  isAutenticado(): boolean {
    const token = this.cookieService.get('token');
    if (token) {
      this.autenticado = true;
      return true;
    }
    this.autenticado = false;
    return false;
  }

  novoUsuario(nome: string, email: string, senha:  string): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuario`, { nome, email, senha });
  }
}
