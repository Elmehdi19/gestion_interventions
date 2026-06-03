import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { OrdonnanceurService } from '../../../../core/services/ordonnanceur.service';
import { ReclamationResponse } from '../../../../shared/models/reclamation.models';

@Component({
  selector: 'app-claims-qualify-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './claims-qualify-list.component.html',
  styleUrls: ['./claims-qualify-list.component.scss']
})
export class ClaimsQualifyListComponent implements OnInit {
  reclamations: ReclamationResponse[] = [];
  paginatedReclamations: ReclamationResponse[] = [];
  currentPage = 1;
  itemsPerPage = 10;
  totalPages = 0;

  constructor(
    private ordonnanceurService: OrdonnanceurService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ordonnanceurService.getReclamationsQualifiees().subscribe({
      next: (data) => {
        this.reclamations = data;
        this.totalPages = Math.ceil(this.reclamations.length / this.itemsPerPage);
        this.updatePaginatedList();
      },
      error: (err) => console.error('Erreur chargement réclamations qualifiées', err)
    });
  }

  updatePaginatedList(): void {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    this.paginatedReclamations = this.reclamations.slice(start, end);
  }

  goToPage(page: number): void {
    if (page < 1 || page > this.totalPages) return;
    this.currentPage = page;
    this.updatePaginatedList();
  }

  creerIntervention(reclamationId: number): void {
    this.router.navigate(['/ordonnanceur/interventions/new'], { queryParams: { reclamationId } });
  }
}