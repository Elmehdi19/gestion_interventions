import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AdminTypeService } from '../../../../core/services/admin-type.service';
import { AdminFamilleService } from '../../../../core/services/admin-famille.service';
import { AdminSpecialiteService } from '../../../../core/services/admin-specialite.service';
import { TypeInterventionResponse } from '../../../../shared/models/type-intervention.models';
import { FamilleResponse } from '../../../../shared/models/famille.models';
import { SpecialiteResponse } from '../../../../shared/models/specialite.models';

@Component({
  selector: 'app-types-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './types-list.component.html',
  styleUrls: ['./types-list.component.scss']
})
export class TypesListComponent implements OnInit {
  types: TypeInterventionResponse[] = [];
  typesFiltres: TypeInterventionResponse[] = [];
  familles: FamilleResponse[] = [];
  specialites: SpecialiteResponse[] = [];
  searchTerm = '';
  selectedSpecialiteId: number | null = null;
  selectedFamilleId: number | null = null;

  constructor(
    private typeService: AdminTypeService,
    private familleService: AdminFamilleService,
    private specialiteService: AdminSpecialiteService
  ) {}

  ngOnInit(): void {
    this.loadSpecialites();
    this.loadFamilles();
    this.loadTypes();
  }

  loadSpecialites(): void {
    this.specialiteService.getAll().subscribe({
      next: (data) => (this.specialites = data),
      error: (err) => console.error('Erreur chargement spécialités', err)
    });
  }

  loadFamilles(): void {
    this.familleService.getAll().subscribe({
      next: (data) => (this.familles = data),
      error: (err) => console.error('Erreur chargement familles', err)
    });
  }

  loadTypes(): void {
    this.typeService.getAll().subscribe({
      next: (data) => {
        this.types = data;
        this.filtrer();
      },
      error: (err) => console.error('Erreur chargement types', err)
    });
  }

  filtrer(): void {
    this.typesFiltres = this.types.filter(t => {
      const matchSpecialite = !this.selectedSpecialiteId || t.specialiteId === this.selectedSpecialiteId;
      const matchFamille = !this.selectedFamilleId || t.familleId === this.selectedFamilleId;
      const matchLibelle = t.libelle.toLowerCase().includes(this.searchTerm.toLowerCase());
      return matchSpecialite && matchFamille && matchLibelle;
    });
  }

  deleteType(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer ce type ?')) {
      this.typeService.delete(id).subscribe({
        next: () => this.loadTypes(),
        error: (err) => console.error('Erreur suppression', err)
      });
    }
  }
}