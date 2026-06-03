import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData } from 'chart.js';
import { Chart, DoughnutController, ArcElement, CategoryScale, BarController, BarElement, LinearScale, Legend, Title, Tooltip } from 'chart.js';

Chart.register(DoughnutController, ArcElement, CategoryScale, BarController, BarElement, LinearScale, Legend, Title, Tooltip);

@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [CommonModule, BaseChartDirective],
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})
export class AnalyticsComponent implements OnInit {
  // KPI cards (données simulées)
  kpis = [
    { label: 'Réclamations ouvertes', value: 142, icon: 'report_problem', color: 'bg-primary-container text-on-primary-container' },
    { label: 'Interventions en cours', value: 67, icon: 'engineering', color: 'bg-tertiary-container text-on-tertiary' },
    { label: 'Taux de résolution SLA', value: '84%', icon: 'check_circle', color: 'bg-secondary-container text-on-secondary-container' },
    { label: 'Tps moyen de prise en charge', value: '2.3 h', icon: 'schedule', color: 'bg-error-container text-on-error-container' }
  ];

  // Graphique 1 : Répartition par spécialité (doughnut)
  specialiteChartData: ChartData<'doughnut'> = {
    labels: ['Eau', 'Assainissement', 'Électricité'],
    datasets: [{ data: [45, 30, 25], backgroundColor: ['#00429d', '#a63b01', '#7f2b00'] }]
  };
  specialiteChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: { legend: { position: 'bottom' } }
  };

  // Graphique 2 : Évolution mensuelle (barres)
  evolutionChartData: ChartData<'bar'> = {
    labels: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin'],
    datasets: [
      { label: 'Réclamations', data: [40, 55, 48, 70, 62, 80], backgroundColor: '#0a58ca' },
      { label: 'Interventions', data: [35, 50, 40, 65, 55, 75], backgroundColor: '#a63b01' }
    ]
  };
  evolutionChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    scales: { y: { beginAtZero: true } }
  };

  // Graphique 3 : Performance par équipe (horizontal bar)
  equipeChartData: ChartData<'bar'> = {
    labels: ['Équipe Matin', 'Équipe Nuit'],
    datasets: [
      { label: 'Interventions réalisées', data: [120, 95], backgroundColor: '#0a58ca' },
      { label: 'Empêchements', data: [8, 12], backgroundColor: '#ba1a1a' }
    ]
  };
  equipeChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    indexAxis: 'y',
    scales: { x: { beginAtZero: true } }
  };

  ngOnInit(): void {
    // Plus tard, appel service pour charger les vraies données
  }
}