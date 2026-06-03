import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ChefEquipeService, EquipeResponse } from '../../../../core/services/chef-equipe.service';

@Component({
  selector: 'app-equipe-list',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './equipe-list.component.html',
  styleUrls: ['./equipe-list.component.scss']
})
export class EquipeListComponent implements OnInit {
  equipes: EquipeResponse[] = [];
  equipesFiltrees: EquipeResponse[] = [];
  searchTerm = '';
  selectedSpecialite = '';

  specialites = ['ELECTRICITE', 'EAU_ASSAINISSEMENT'];

  constructor(private chefEquipeService: ChefEquipeService) {}

  ngOnInit(): void {
    this.loadEquipes();
  }

  loadEquipes() {
    this.chefEquipeService.getAllEquipes().subscribe({
      next: (data) => {
        this.equipes = data;
        this.filtrer();
      },
      error: (err) => console.error(err)
    });
  }

  filtrer() {
    this.equipesFiltrees = this.equipes.filter(equipe => {
      const matchNom = equipe.nom.toLowerCase().includes(this.searchTerm.toLowerCase());
      const matchSpecialite = !this.selectedSpecialite || equipe.specialite === this.selectedSpecialite;
      return matchNom && matchSpecialite;
    });
  }

  deleteEquipe(id: number) {
    if (confirm('Supprimer cette équipe définitivement ?')) {
      this.chefEquipeService.deleteEquipe(id).subscribe(() => this.loadEquipes());
    }
  }
}