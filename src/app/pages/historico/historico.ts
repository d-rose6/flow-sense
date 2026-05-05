import { Component } from '@angular/core';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-historico',
  standalone: true,
  imports: [NgIf],
  templateUrl: './historico.html',
  styleUrl: './historico.css'
})
export class Historico {
  mostrarGrafico = true;

  toggleVisualizacao(tipo: 'grafico' | 'registros'): void {
    this.mostrarGrafico = tipo === 'grafico';
  }
}