import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../quiz.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.css'
})
export class QuizComponent implements OnInit {
  quiz: any = {};
  perguntaAtual = 0;
  respostaSelecionada: any = '';
  pontuacao = 0;
  pontuacaoQuestao: any = 0;
  respostas: any[] = [];
  nota = '';

  constructor(
    private activaRoute: ActivatedRoute,
    private quizService: QuizService,
    private router: Router,
    private cookieService: CookieService
  ) {}

  shuffleArray(array: string[]): string[] {
    const shuffledArray = array.slice();
    for (let i = shuffledArray.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [shuffledArray[i], shuffledArray[j]] = [shuffledArray[j], shuffledArray[i]];
    }
  
    return shuffledArray;
  }

  ngOnInit(): void {
    this.activaRoute.paramMap.subscribe({
      next: (rota: any) => {
        this.quizService.getQuizporID(rota.params.id).subscribe({
          next: (res: any) => {
            this.quiz = res;
            this.respostas = this.quiz.perguntas[0].respostas.split('&&');
            this.respostas = this.shuffleArray(this.respostas);
            this.pontuacaoQuestao = 100 / this.quiz.perguntas.length;
            console.log(this.respostas);
          }
        });
      }
    });

  }

  avancarPergunta() {
    if (this.perguntaAtual === this.quiz.perguntas.length - 1) {
      if (this.pontuacao < 20) {
        this.nota = 'F';
      } else if(this.pontuacao >= 20 && this.pontuacao < 40) {
        this.nota = 'D';
      } else if(this.pontuacao >= 40 && this.pontuacao < 60) {
        this.nota = 'C';
      } else if(this.pontuacao >= 60 && this.pontuacao < 80) {
        this.nota = 'B';
      } else if(this.pontuacao >= 80 && this.pontuacao <= 90) {
        this.nota = 'A';
      } else {
        this.nota = 'S';
      }
      this.quizService.cadastrarPontuacao(this.pontuacao, parseInt(this.cookieService.get('usuario_id')), this.quiz.id).subscribe({
        next: (res: any) => {
          this.router.navigate([`/resultado/${this.pontuacao}/${this.nota}`]);
        }
      });
    }
    this.perguntaAtual = this.perguntaAtual + 1;
    this.respostas = this.quiz.perguntas[this.perguntaAtual].respostas.split('&&');
    this.respostas = this.shuffleArray(this.respostas);
    this.respostaSelecionada = null;
  }

  verificarResposta() {
    const pergunta = this.quiz.perguntas[this.perguntaAtual];
    if (this.respostaSelecionada === pergunta.resposta_correta) {
      this.pontuacao = this.pontuacao + this.pontuacaoQuestao;
    }
    this.avancarPergunta();
  }

}
