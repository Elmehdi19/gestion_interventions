import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData } from 'chart.js';
import 'chart.js/auto';

import { AdminDashboardService } from '../../../core/services/admin-dashboard.service';
import { AdminUserService } from '../../../core/services/admin-user.service';
import { UserResponse } from '../../../shared/models/user.model';
import { DashboardKPIsResponse } from '../../../shared/models/dashboard.models';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, BaseChartDirective],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {

  // ────────────── KPIs ──────────────
  totalReclamations = 0;
  reclamationsOuvertes = 0;
  interventionsEnCours = 0;
  interventionsCloturees = 0;

  // ────────────── Données du doughnut ──────────────
  doughnutChartData: ChartData<'doughnut'> = {
    labels: [],
    datasets: [{ data: [], backgroundColor: [] }]
  };
  doughnutChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio: true,
    plugins: {
      legend: { position: 'bottom', labels: { padding: 20, usePointStyle: true } }
    }
  };

  // ────────────── Évolution mensuelle (barres) ──────────────
  barChartData: ChartData<'bar'> = {
    labels: [],
    datasets: []
  };
  barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio: true,
    scales: { y: { beginAtZero: true, grid: { color: '#e1e2ec' } } },
    plugins: { legend: { position: 'bottom', labels: { usePointStyle: true } } }
  };

  // ────────────── Utilisateurs récents ──────────────
  users: UserResponse[] = [];

  constructor(
    private dashboardService: AdminDashboardService,
    private userService: AdminUserService
  ) {}

  ngOnInit(): void {
    this.loadKPIs();
    this.loadRecentUsers();
  }

  private loadKPIs(): void {
    this.dashboardService.getKPIs().subscribe({
      next: (kpis: DashboardKPIsResponse) => {
        this.totalReclamations = kpis.totalReclamations;
        this.reclamationsOuvertes = kpis.reclamationsOuvertes;
        this.interventionsEnCours = kpis.interventionsEnCours;
        this.interventionsCloturees = kpis.interventionsCloturees;

        // Mise à jour du doughnut
        const specialties = kpis.reclamationsParSpecialite || {};
        this.doughnutChartData = {
          labels: Object.keys(specialties),
          datasets: [{
            data: Object.values(specialties),
            backgroundColor: ['#00429d', '#7f2b00', '#575f67'],
            borderWidth: 0
          }]
        };

        // Mise à jour du bar chart (si les maps existent)
        const reclamationsParMois = kpis.reclamationsParMois || {};
        const interventionsParMois = kpis.interventionParMois|| {};
        const mois = Object.keys(reclamationsParMois);
        if (mois.length === 0) {
          // fallback si pas de données
          this.barChartData = {
            labels: ['Aucune donnée'],
            datasets: [{ data: [0], label: 'Réclamations' }]
          };
          return;
        }
        this.barChartData = {
          labels: mois,
          datasets: [
            {
              data: mois.map(m => reclamationsParMois[m] || 0),
              label: 'Réclamations',
              backgroundColor: '#0a58ca'
            },
            {
              data: mois.map(m => interventionsParMois[m] || 0),
              label: 'Interventions',
              backgroundColor: '#a63b01'
            }
          ]
        };
      },
      error: (err) => console.error('Erreur chargement KPIs', err)
    });
  }

  private loadRecentUsers(): void {
    this.userService.getAll().subscribe({
      next: (users) => {
        // Affiche les 5 derniers utilisateurs (par date d'inscription) si la propriété existe
        this.users = users
          .sort((a, b) => new Date(b.dateInscription).getTime() - new Date(a.dateInscription).getTime())
          .slice(0, 5)
          .map(u => ({
            ...u,
            // mapping pour le template (adaptez selon les colonnes du tableau)
            name: `${u.nom} ${u.prenom}`,
            email: u.email,
            role: u.role,
            specialty: u.specialite?.label || '',
            active: u.actif
          }));
      },
      error: (err) => console.error('Erreur chargement utilisateurs', err)
    });
  }
}