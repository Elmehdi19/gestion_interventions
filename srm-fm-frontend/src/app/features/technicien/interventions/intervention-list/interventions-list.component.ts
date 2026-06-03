import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TechnicienService } from '../../../../core/services/technicien.service';
import { InterventionResponse } from '../../../../shared/models/intervention.models';

@Component({
  selector: 'app-interventions-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './interventions-list.component.html',
  styleUrls: ['./interventions-list.component.scss']
})
export class InterventionsListComponent implements OnInit {
  // Données brutes
  allInterventions: InterventionResponse[] = [];

  // Tableau actif (filtres + pagination)
  interventionsActives: InterventionResponse[] = [];
  activeFilters = { dateDebut: '', dateFin: '', statut: '' };
  activePage = 1;
  activePageSize = 5;
  activeTotalPages = 1;

  // Tableau historique (filtres + pagination)
  interventionsHistorique: InterventionResponse[] = [];
  histoFilters = { dateDebut: '', dateFin: '', statut: '' };
  histoPage = 1;
  histoPageSize = 5;
  histoTotalPages = 1;

  selectedInterventionId: number | null = null;
  clotureForm: FormGroup;
  empechementForm: FormGroup;

  constructor(
    private technicienService: TechnicienService,
    private fb: FormBuilder
  ) {
    this.clotureForm = this.fb.group({
      rapport: ['', Validators.required],
      dureeEffectiveMinutes: [0, Validators.required],
      materiauxUtilises: ['']
    });
    this.empechementForm = this.fb.group({
      motif: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadInterventions();
  }

  loadInterventions() {
    this.technicienService.getMesInterventions().subscribe(data => {
      this.allInterventions = data;
      this.applyActiveFilters();
      this.applyHistoFilters();
    });
  }

  // ========== PARTIE ACTIVES ==========
  applyActiveFilters() {
    let filtered = this.allInterventions.filter(i => i.statut === 'PRIS_EN_CHARGE' || i.statut === 'EN_COURS');

    if (this.activeFilters.dateDebut) {
      const debut = new Date(this.activeFilters.dateDebut);
      filtered = filtered.filter(i => new Date(i.dateCreation) >= debut);
    }
    if (this.activeFilters.dateFin) {
      const fin = new Date(this.activeFilters.dateFin);
      fin.setHours(23, 59, 59);
      filtered = filtered.filter(i => new Date(i.dateCreation) <= fin);
    }
    if (this.activeFilters.statut) {
      filtered = filtered.filter(i => i.statut === this.activeFilters.statut);
    }

    this.activeTotalPages = Math.ceil(filtered.length / this.activePageSize);
    const start = (this.activePage - 1) * this.activePageSize;
    this.interventionsActives = filtered.slice(start, start + this.activePageSize);
  }

  onActiveFilterChange() {
    this.activePage = 1;
    this.applyActiveFilters();
  }

  activePageSuivante() {
    if (this.activePage < this.activeTotalPages) {
      this.activePage++;
      this.applyActiveFilters();
    }
  }

  activePagePrecedente() {
    if (this.activePage > 1) {
      this.activePage--;
      this.applyActiveFilters();
    }
  }

  // ========== PARTIE HISTORIQUE ==========
  applyHistoFilters() {
    let filtered = this.allInterventions.filter(i => i.statut === 'CLOTURE' || i.statut === 'EMPECHE');

    if (this.histoFilters.dateDebut) {
      const debut = new Date(this.histoFilters.dateDebut);
      filtered = filtered.filter(i => new Date(i.dateCreation) >= debut);
    }
    if (this.histoFilters.dateFin) {
      const fin = new Date(this.histoFilters.dateFin);
      fin.setHours(23, 59, 59);
      filtered = filtered.filter(i => new Date(i.dateCreation) <= fin);
    }
    if (this.histoFilters.statut) {
      filtered = filtered.filter(i => i.statut === this.histoFilters.statut);
    }

    this.histoTotalPages = Math.ceil(filtered.length / this.histoPageSize);
    const start = (this.histoPage - 1) * this.histoPageSize;
    this.interventionsHistorique = filtered.slice(start, start + this.histoPageSize);
  }

  onHistoFilterChange() {
    this.histoPage = 1;
    this.applyHistoFilters();
  }

  histoPageSuivante() {
    if (this.histoPage < this.histoTotalPages) {
      this.histoPage++;
      this.applyHistoFilters();
    }
  }

  histoPagePrecedente() {
    if (this.histoPage > 1) {
      this.histoPage--;
      this.applyHistoFilters();
    }
  }

  // ========== ACTIONS ==========
  demarrer(id: number) {
    this.technicienService.demarrerIntervention(id).subscribe(() => this.loadInterventions());
  }

  afficherCloture(id: number) {
    this.selectedInterventionId = id;
    this.clotureForm.reset();
  }

  cloturer() {
    if (this.clotureForm.invalid || !this.selectedInterventionId) return;
    this.technicienService.cloturerIntervention(this.selectedInterventionId, this.clotureForm.value)
      .subscribe(() => {
        this.selectedInterventionId = null;
        this.loadInterventions();
      });
  }

  afficherEmpechement(id: number) {
    this.selectedInterventionId = id;
    this.empechementForm.reset();
  }

  empecher() {
    if (this.empechementForm.invalid || !this.selectedInterventionId) return;
    this.technicienService.empecherIntervention(this.selectedInterventionId, this.empechementForm.value)
      .subscribe(() => {
        this.selectedInterventionId = null;
        this.loadInterventions();
      });
  }

  getStatutLabel(statut: string): string {
    const labels: Record<string, string> = {
      'PRIS_EN_CHARGE': 'À démarrer',
      'EN_COURS': 'En cours',
      'CLOTURE': 'Clôturée',
      'EMPECHE': 'Empêchée'
    };
    return labels[statut] || statut;
  }
}