import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { OrdonnanceurService, ChefEquipeResponse } from '../../../../core/services/ordonnanceur.service';
import { TypeArbreResponse, FamilleArbreResponse } from '../../../../shared/models/type-arbre.models';

@Component({
  selector: 'app-intervention-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './intervention-create.component.html',
  styleUrls: ['./intervention-create.component.scss']
})
export class InterventionCreateComponent implements OnInit {
  form: FormGroup;
  arbre: TypeArbreResponse | null = null;
  famillesFiltrees: FamilleArbreResponse[] = [];
  typesDisponibles: any[] = [];
  chefs: ChefEquipeResponse[] = [];
  reclamationId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private ordonnanceurService: OrdonnanceurService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      familleId: [null, Validators.required],
      typeInterventionId: [null, Validators.required],
      chefEquipeId: [null, Validators.required],
      urgente: [false]
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['reclamationId']) {
        this.reclamationId = Number(params['reclamationId']);
      } else {
        this.router.navigate(['/ordonnanceur/claims/qualify']);
      }
    });

    this.ordonnanceurService.getArbreTypes().subscribe({
      next: (arbre) => {
        this.arbre = arbre;
        this.famillesFiltrees = arbre.familles;
      },
      error: (err) => console.error('Erreur chargement arbre des types', err)
    });

    this.ordonnanceurService.getChefsEquipe().subscribe({
      next: (chefs) => this.chefs = chefs,
      error: (err) => console.error('Erreur chargement chefs équipe', err)
    });
  }

  onFamilleChange() {
    const familleId = this.form.get('familleId')?.value;
    if (familleId && this.famillesFiltrees) {
      const famille = this.famillesFiltrees.find(f => f.id == familleId);
      this.typesDisponibles = famille ? famille.types : [];
      this.form.get('typeInterventionId')?.reset();
    }
  }

  creerIntervention() {
    if (this.form.invalid || !this.reclamationId) return;

    const formValue = this.form.value;
    const payload = {
      reclamationId: this.reclamationId,
      typeInterventionId: formValue.typeInterventionId,
      chefEquipeId: formValue.chefEquipeId,
      urgente: formValue.urgente
    };

    this.ordonnanceurService.creerIntervention(payload).subscribe({
      next: () => {
        this.router.navigate(['/ordonnanceur/claims/qualify']);
      },
      error: (err) => console.error('Erreur création intervention', err)
    });
  }

  annuler() {
    this.router.navigate(['/ordonnanceur/claims/qualify']);
  }
}