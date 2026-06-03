import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InterventionService } from '../../../../core/services/intervention.service';
import { InterventionResponse } from '../../../../shared/models/intervention.models';

@Component({
  selector: 'app-interventions-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './interventions-list.component.html',
  styleUrls: ['./interventions-list.component.scss']
})
export class InterventionsListComponent implements OnInit {
  interventions: InterventionResponse[] = [];

  constructor(private interventionService: InterventionService) {}

  ngOnInit(): void {
    this.interventionService.getAll().subscribe({
      next: (data) => (this.interventions = data),
      error: (err) => console.error('Erreur chargement des interventions', err)
    });
  }
}