import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ChefEquipeService } from '../../../../core/services/chef-equipe.service';
import { TechnicienDTO } from '../../../../shared/models/equipe.models';
import { EquipeResponse } from '../../../../shared/models/equipe.models';

@Component({
  selector: 'app-demander-permutation',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './demander-permutation.component.html',
  styleUrls: ['./demander-permutation.component.scss']
})
export class DemanderPermutationComponent implements OnInit {
  form: FormGroup;
  techniciens: TechnicienDTO[] = [];
  equipes: EquipeResponse[] = [];

  constructor(
    private fb: FormBuilder,
    private chefEquipeService: ChefEquipeService,
    private router: Router
  ) {
    this.form = this.fb.group({
      technicienId: [null, Validators.required],
      equipeDestinationId: [null, Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      typePermutation: ['IMMEDIATE'],
      motif: ['']
    });
  }

  ngOnInit(): void {
    // Charger la liste des techniciens (tous, ou seulement ceux de l'équipe du chef)
    this.chefEquipeService.getAllTechniciens().subscribe({
      next: (data) => this.techniciens = data,
      error: (err) => console.error('Erreur chargement techniciens', err)
    });

    // Charger la liste des équipes (destinations possibles)
    this.chefEquipeService.getAllEquipes().subscribe({
      next: (data) => this.equipes = data,
      error: (err) => console.error('Erreur chargement équipes', err)
    });
  }

  onSubmit() {
    if (this.form.invalid) return;
    const demande = this.form.value;
    this.chefEquipeService.demanderPermutation(demande).subscribe({
      next: () => {
        alert('Demande de permutation envoyée avec succès');
        this.form.reset();
        this.router.navigate(['/chef-equipe/permutations']);
      },
      error: (err) => console.error('Erreur lors de la demande', err)
    });
  }
}