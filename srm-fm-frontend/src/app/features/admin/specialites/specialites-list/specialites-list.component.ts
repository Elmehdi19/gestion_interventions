import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AdminSpecialiteService } from '../../../../core/services/admin-specialite.service';
import { SpecialiteResponse } from '../../../../shared/models/specialite.models';

@Component({
  selector: 'app-specialites-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './specialites-list.component.html',
  styleUrls: ['./specialites-list.component.scss']
})
export class SpecialitesListComponent implements OnInit {
  specialites: SpecialiteResponse[] = [];

  constructor(private adminSpecialiteService: AdminSpecialiteService) {}

  ngOnInit(): void {
    this.loadSpecialites();
  }

  loadSpecialites(): void {
    this.adminSpecialiteService.getAll().subscribe({
      next: (data) => (this.specialites = data),
      error: (err) => console.error('Erreur chargement spécialités', err)
    });
  }

  deleteSpecialite(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette spécialité ?')) {
      this.adminSpecialiteService.delete(id).subscribe({
        next: () => {
          // recharger la liste après suppression
          this.loadSpecialites();
        },
        error: (err) => console.error('Erreur suppression spécialité', err)
      });
    }
  }
}