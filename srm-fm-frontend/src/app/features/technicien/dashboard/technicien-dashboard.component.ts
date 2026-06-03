import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { TechnicienService } from '../../../core/services/technicien.service';

Chart.register(...registerables);

@Component({
  selector: 'app-technicien-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './technicien-dashboard.component.html',
  styleUrls: ['./technicien-dashboard.component.scss']
})
export class TechnicienDashboardComponent implements OnInit, AfterViewInit {
  nbACommencer = 3;     // fictif
  nbEnCours = 2;        // fictif
  nbCloturees = 5;      // fictif
  nbEmpechees = 1;      // fictif
  private chart: Chart | null = null;

  constructor(private technicienService: TechnicienService) {}

  ngOnInit(): void {
    // Pour la démo, on utilise des valeurs fictives
    this.createOrUpdateChart();

    // this.technicienService.getMesInterventions().subscribe(interventions => {
    //   this.nbACommencer = interventions.filter(i => i.statut === 'PRIS_EN_CHARGE').length;
    //   this.nbEnCours = interventions.filter(i => i.statut === 'EN_COURS').length;
    //   this.nbCloturees = interventions.filter(i => i.statut === 'CLOTURE').length;
    //   this.nbEmpechees = interventions.filter(i => i.statut === 'EMPECHE').length;
    //   this.createOrUpdateChart();
    // });
  }

  ngAfterViewInit(): void {
  }

  createOrUpdateChart(): void {
    const canvas = document.getElementById('activiteChart') as HTMLCanvasElement;
    if (!canvas) return;
    if (this.chart) this.chart.destroy();

    this.chart = new Chart(canvas, {
      type: 'doughnut',
      data: {
        labels: ['À commencer', 'En cours', 'Clôturées', 'Empêchées'],
        datasets: [{
          data: [this.nbACommencer, this.nbEnCours, this.nbCloturees, this.nbEmpechees],
          backgroundColor: ['#f59e0b', '#3b82f6', '#10b981', '#ef4444'],
          hoverOffset: 4
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: { legend: { position: 'bottom' } }
      }
    });
  }
}