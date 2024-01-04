import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html',
  styleUrl: './resultado.component.css'
})
export class ResultadoComponent implements OnInit{
  pontuacao = 0;
  nota = '';

  constructor(
    private activaRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activaRoute.paramMap.subscribe({
      next: (rota: any) => {
        this.pontuacao = rota.params.pontuacao;
        this.nota = rota.params.nota;
      }
    });
  }

  voltar() {
    this.router.navigate(['/home']);
  }
}
