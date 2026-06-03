import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { ChefEquipeService } from '../../../core/services/chef-equipe.service';

Chart.register(...registerables);

@Component({
  selector: 'app-chef-equipe-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './chef-equipe-dashboard.component.html',
  styleUrls: ['./chef-equipe-dashboard.component.scss']
})
export class ChefEquipeDashboardComponent implements OnInit, AfterViewInit {
  nbInterventionsEnAttente = 0;
  nbTechniciens = 0;
  nbInterventionsEnCours = 0;
  private chart: Chart | null = null;

  constructor(private chefEquipeService: ChefEquipeService) {}

  ngOnInit(): void {
    // Interventions en attente
    this.chefEquipeService.getInterventionsEnAttenteCount().subscribe(count => {
      this.nbInterventionsEnAttente = count;
    });

    // Liste des techniciens (tous)
    this.chefEquipeService.getAllTechniciens().subscribe(techniciens => {
      this.nbTechniciens = techniciens.length;
    });

    // Pour le nombre d'interventions en cours (à adapter selon votre endpoint)
    // Si vous avez un endpoint dédié, utilisez-le ; sinon, vous pouvez récupérer la liste paginée et compter
    this.chefEquipeService.getInterventionsEnAttente(0, 1).subscribe(page => {
      // Simulons un nombre d'interventions en cours (à remplacer par un vrai compteur)
      this.nbInterventionsEnCours = Math.floor(Math.random() * 10) + 1;
    });
  }

  ngAfterViewInit(): void {
    this.initActivityChart();
  }

  initActivityChart(): void {
    const canvas = document.getElementById('activityChart') as HTMLCanvasElement;
    if (!canvas) return;
    if (this.chart) this.chart.destroy();

    this.chart = new Chart(canvas, {
      type: 'bar',
      data: {
        labels: ['Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam', 'Dim'],
        datasets: [{
          label: 'Interventions affectées',
          data: [3, 5, 2, 7, 4, 1, 0],
          backgroundColor: '#3b82f6',
          borderRadius: 6
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: { legend: { position: 'top' } }
      }
    });
  }
}