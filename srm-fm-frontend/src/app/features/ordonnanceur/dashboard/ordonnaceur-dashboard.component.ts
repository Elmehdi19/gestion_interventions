import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { OrdonnanceurService } from '../../../core/services/ordonnanceur.service';

Chart.register(...registerables);

@Component({
  selector: 'app-ordonnanceur-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './ordonnanceur-dashboard.component.html',
  styleUrls: ['./ordonnanceur-dashboard.component.scss']
})
export class OrdonnanceurDashboardComponent implements OnInit, AfterViewInit {
  nbReclamations = 0;
  nbInterventionsCreees = 0;
  nbInterventionsEnCours = 0;
  private chart: Chart | null = null;

  constructor(private ordonnanceurService: OrdonnanceurService) {}

  ngOnInit(): void {
    // Réclamations à qualifier (nouvelles)
    this.ordonnanceurService.getReclamationsNouvelles().subscribe(recs => {
      this.nbReclamations = recs.length;
    });

    // Interventions créées aujourd'hui (à adapter selon votre endpoint)
    this.ordonnanceurService.getInterventions().subscribe(interventions => {
      const today = new Date().toISOString().slice(0,10);
      this.nbInterventionsCreees = interventions.filter(i => i.dateCreation?.startsWith(today)).length;
      this.nbInterventionsEnCours = interventions.filter(i => i.statut !== 'CLOTURE').length;
    });
  }

  ngAfterViewInit(): void {
    this.initChart();
  }

  initChart(): void {
    const canvas = document.getElementById('evolutionChart') as HTMLCanvasElement;
    if (!canvas) return;
    if (this.chart) this.chart.destroy();

    this.chart = new Chart(canvas, {
      type: 'line',
      data: {
        labels: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'],
        datasets: [{
          label: 'Réclamations qualifiées',
          data: [5, 8, 12, 9, 15, 18, 22, 20, 25, 30, 28, 35],
          borderColor: '#3b82f6',
          backgroundColor: 'rgba(59,130,246,0.1)',
          fill: true,
          tension: 0.4
        }]
      },
      options: { responsive: true, maintainAspectRatio: true }
    });
  }
}