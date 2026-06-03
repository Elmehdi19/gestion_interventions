import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AdminFamilleService } from '../../../../core/services/admin-famille.service';
import { AdminSpecialiteService } from '../../../../core/services/admin-specialite.service';
import { FamilleResponse } from '../../../../shared/models/famille.models';
import { SpecialiteResponse } from '../../../../shared/models/specialite.models';

@Component({
  selector: 'app-familles-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './familles-list.component.html',
  styleUrls: ['./familles-list.component.scss']
})
export class FamillesListComponent implements OnInit {
  familles: FamilleResponse[] = [];
  famillesFiltrees: FamilleResponse[] = [];
  specialites: SpecialiteResponse[] = [];
  searchTerm = '';
  selectedSpecialiteId: number | null = null;

  constructor(
    private familleService: AdminFamilleService,
    private specialiteService: AdminSpecialiteService
  ) {}

  ngOnInit(): void {
    this.loadSpecialites();
    this.loadFamilles();
  }

  loadSpecialites(): void {
    this.specialiteService.getAll().subscribe({
      next: (data) => (this.specialites = data),
      error: (err) => console.error('Erreur chargement spécialités', err)
    });
  }

  loadFamilles(): void {
    this.familleService.getAll().subscribe({
      next: (data) => {
        this.familles = data;
        this.filtrer();
      },
      error: (err) => console.error('Erreur chargement familles', err)
    });
  }

  // Récupère le libellé de la spécialité à partir de son id
  getSpecialiteLibelle(specialiteId: number): string {
    const spec = this.specialites.find(s => s.id === specialiteId);
    return spec ? spec.libelle : '—';
  }

  filtrer(): void {
    if (!this.familles) {
      this.famillesFiltrees = [];
      return;
    }
    this.famillesFiltrees = this.familles.filter(f => {
      const matchSpecialite = !this.selectedSpecialiteId || f.specialiteId === this.selectedSpecialiteId;
      const matchLibelle = f.libelle.toLowerCase().includes(this.searchTerm.toLowerCase());
      return matchSpecialite && matchLibelle;
    });
  }

  deleteFamille(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette famille ?')) {
      this.familleService.delete(id).subscribe({
        next: () => this.loadFamilles(),
        error: (err) => console.error('Erreur suppression', err)
      });
    }
  }
}