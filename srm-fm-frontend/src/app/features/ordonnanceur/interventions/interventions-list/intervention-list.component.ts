import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdonnanceurService } from '../../../../core/services/ordonnanceur.service';
import { InterventionResponse } from '../../../../shared/models/intervention.models';

@Component({
  selector: 'app-interventions-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './intervention-list.component.html'
})
export class InterventionsListComponent implements OnInit {
  interventions: InterventionResponse[] = [];

  constructor(private ordonnanceurService: OrdonnanceurService) {}

  ngOnInit(): void {
    this.ordonnanceurService.getInterventions().subscribe({
      next: (data) => this.interventions = data,
      error: (err) => console.error('Erreur chargement interventions', err)
    });
  }
  getStatutLabel(statut: string): string {
  const labels: Record<string, string> = {
    'EN_ATTENTE': 'En attente',
    'PRIS_EN_CHARGE': 'Pris en charge',
    'EN_COURS': 'En cours',
    'EMPECHE': 'Empêché',
    'CLOTURE': 'Clôturé'
  };
  return labels[statut] || statut;
}
}