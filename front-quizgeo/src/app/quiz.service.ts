import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  baseUrl = 'http://localhost:8080';
  headers = { 'Authorization': `Bearer ${this.cookieService.get('token')}`};

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getQuizzes(): Observable<any> {
    return this.http.get(`${this.baseUrl}/quiz`, { headers: this.headers });
  }

  getQuizporID(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/quiz/${id}`, { headers: this.headers });
  }

  getPontuacao(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/pontuacao/id/${id}`, { headers: this.headers });
  }

  getUsuarioporEmail(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/usuario/email/${email}`, { headers: this.headers });
  }

  cadastrarPontuacao(pontuacao: number, usuario_id: number, quiz_id: number): Observable<any> {
    let reqPontuacao = {pontuacao: pontuacao, usuario: {id: usuario_id}, quiz: {id: quiz_id}}
    return this.http.post(`${this.baseUrl}/pontuacao`, reqPontuacao, { headers: this.headers });
  }
}
