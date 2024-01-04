import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TemaService } from '../tema.service';
import { CookieService } from 'ngx-cookie-service';
import { QuizService } from '../quiz.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  quizzes: any[] = []
  quizzesCopia: any[] = []
  quizzesDestaque: any = [] =[]
  ativo: boolean = false;
  quizSelecionado: any = null;
  email: any = null;
  usuario: any = null;
  carregando: boolean = true;
  pontuacoes: any[] = [];
  pontuacoesCopia: any[] = [];
  pontuacaoPesquisada = '';
  quizPesquisado = '';

  constructor(private router: Router, private temaServico: TemaService, private cookieService: CookieService, private quizServico: QuizService) {}

  ngOnInit(): void {
    if (this.cookieService.check('dark')) {
      if (this.cookieService.get('dark') == 'true') {
        this.ativo = true;
      } else {
        this.ativo = false;
      }
    }
    this.onListar();
  }

  mudarTema(): void {
    let tema;
    if (this.ativo) {
      tema = 'md-dark-deeppurple';
    } else {
      tema = 'md-light-deeppurple';
    }
    this.cookieService.set('dark', this.ativo ? 'true' : 'false');
    this.temaServico.switchTheme(tema);
  }

  creditos(): void {
    this.router.navigate(['/creditos']);
  }

  onListar(): void {
    setTimeout(() => {
      this.quizServico.getQuizzes().subscribe((quizzes: any) => {
        this.quizzes = quizzes.content;
        this.quizzesCopia = quizzes.content;
        this.quizzesDestaque = quizzes.content.slice(0, 3);
        this.carregando = false;
      });
      this.quizServico.getUsuarioporEmail(this.cookieService.get('email')).subscribe((usuario: any) => {
        this.cookieService.set('usuario_id', usuario.id);
        this.cookieService.set('usuario_nome', usuario.nome);
        this.usuario = usuario.nome;
      });
      this.quizServico.getPontuacao(parseInt(this.cookieService.get('usuario_id'))).subscribe((pontuacoes: any) => {
        this.pontuacoes = pontuacoes;
        this.pontuacoesCopia = pontuacoes;
      });
      this.email = this.cookieService.get('email');
    }, 1000)
    
  }

  filtrarQuizzes(): void {
    if (this.quizPesquisado) {
      let quizpesquisa = this.quizPesquisado.toUpperCase();

      this.quizzes = this.quizzesCopia.filter((quiz: any) => {
        return quiz.nome.toUpperCase().includes(quizpesquisa);
      });
  } else {
    this.quizzes = [...this.quizzesCopia];
  }
}

filtrarPontuacoes(): void {
  if (this.pontuacaoPesquisada) {
    let pontuacaopesquisa = this.pontuacaoPesquisada.toUpperCase();

    this.pontuacoes = this.pontuacoesCopia.filter((pontuacao: any) => {
      return pontuacao.quiz.nome.toUpperCase().includes(pontuacaopesquisa);
    });
} else {
  this.pontuacoes = [...this.pontuacoesCopia];
}
}

  jogar(quizId: any) {
    this.router.navigate([`/quiz/${quizId}`]);
  }

}
