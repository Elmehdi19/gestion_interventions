import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-consommation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consomation.component.html',
  styleUrls: ['./consomation.component.scss']
})
export class ConsommationComponent implements OnInit {
  periode: 'jour' | 'mois' | 'annee' = 'mois';
  selectedMois: string = '';
  selectedAnnee: number = new Date().getFullYear();
  mois: string[] = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
  annees: number[] = [];

  // Données simulées (exemple : consommation d'électricité en kWh)
  consoJour: number[] = [12.5, 14.2, 13.8, 15.0, 14.5, 13.2, 12.9, 14.1, 13.5, 14.8, 15.2, 13.7, 12.3, 14.0, 13.9, 15.1, 14.6, 13.3, 12.8, 14.2, 13.6, 14.9, 15.3, 13.8, 12.4, 14.1, 14.0, 15.2, 14.7, 13.4, 12.9];
  consoMois: number[] = [120, 135, 128, 142, 138, 130, 125, 140, 132, 145, 150, 136];
  consoAnnee: number[] = [1450, 1520, 1480, 1600, 1550, 1490, 1440, 1580, 1510, 1620, 1680, 1540];

  chart: any;

  constructor() {
    const currentYear = new Date().getFullYear();
    for (let i = currentYear - 3; i <= currentYear + 1; i++) {
      this.annees.push(i);
    }
    this.selectedAnnee = currentYear;
    this.selectedMois = this.mois[new Date().getMonth()];
  }

  ngOnInit(): void {
    this.creerGraphique();
  }

  onPeriodeChange(): void {
    this.mettreAJourGraphique();
  }

  onMoisChange(): void {
    this.mettreAJourGraphique();
  }

  onAnneeChange(): void {
    this.mettreAJourGraphique();
  }

  mettreAJourGraphique(): void {
    if (this.chart) {
      this.chart.destroy();
    }
    this.creerGraphique();
  }

  creerGraphique(): void {
    let labels: string[] = [];
    let data: number[] = [];

    if (this.periode === 'jour') {
      // Pour l’exemple, on affiche les 31 jours du mois sélectionné
      labels = Array.from({ length: 31 }, (_, i) => `Jour ${i+1}`);
      data = this.consoJour.slice(0, 31);
    } else if (this.periode === 'mois') {
      labels = this.mois;
      data = this.consoMois;
    } else {
      labels = this.annees.map(a => a.toString());
      data = this.consoAnnee;
    }

    const ctx = document.getElementById('chartConsommation') as HTMLCanvasElement;
    if (ctx) {
      this.chart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [{
            label: 'Consommation (kWh)',
            data: data,
            borderColor: '#3b82f6',
            backgroundColor: 'rgba(59,130,246,0.1)',
            fill: true,
            tension: 0.4
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            tooltip: { mode: 'index', intersect: false },
            legend: { position: 'top' }
          }
        }
      });
    }
  }
}