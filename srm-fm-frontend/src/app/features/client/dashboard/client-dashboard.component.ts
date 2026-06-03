import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ReclamationService } from '../../../core/services/reclamation.service';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

@Component({
  selector: 'app-client-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.scss']
})
export class ClientDashboardComponent implements OnInit {
  nbReclamationsActives = 0;

  constructor(private reclamationService: ReclamationService) {}

  ngOnInit(): void {
    this.reclamationService.getMesReclamations().subscribe(recs => {
      this.nbReclamationsActives = recs.filter(r => r.statut !== 'CLOTUREE').length;
    });
    const ctx = document.getElementById('miniChart') as HTMLCanvasElement;
new Chart(ctx, {
  type: 'line',
  data: {
    labels: ['Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam', 'Dim'],
    datasets: [{
      label: 'kWh',
      data: [12, 19, 15, 17, 14, 10, 13],
      borderColor: '#3b82f6',
      tension: 0.4
    }]
    }
  });
  }
}