import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ReclamationService } from '../../../../core/services/reclamation.service';
import { ToastService } from '../../../../core/services/toast.service';
import { PublicService } from '../../../../core/services/public.service';
import { SpecialiteResponse } from '../../../../shared/models/specialite.models';

@Component({
  selector: 'app-claims-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './claims-create.component.html',
  styleUrls: ['./claims-create.component.scss']
})
export class ClaimsCreateComponent implements OnInit {
  form!: FormGroup;
  specialites: SpecialiteResponse[] = [];
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private reclamationService: ReclamationService,
    private toast: ToastService,
    private publicService: PublicService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      description: ['', Validators.required],
      urgente: [false],
      specialiteConcerne: [null, Validators.required]
    });

    this.publicService.getSpecialites().subscribe({
      next: (data) => (this.specialites = data),
      error: () => this.toast.show('Impossible de charger les spécialités', 'error')
    });
  }

  soumettre() {
    if (this.form.invalid) {
      // Marquer tous les champs comme touchés pour afficher les erreurs
      Object.keys(this.form.controls).forEach(key => {
        this.form.get(key)?.markAsTouched();
      });
      this.toast.show('Veuillez remplir tous les champs obligatoires', 'error');
      return;
    }

    this.isLoading = true;

    const formValue = this.form.value;
    const specialite = formValue.specialiteConcerne;

    // Conversion du libellé en enum attendu par le backend
    const specialiteEnum = specialite?.libelle === 'Électricité' ? 'ELECTRICITE' : 'EAU_ASSAINISSEMENT';

    const payload = {
      description: formValue.description,
      urgente: formValue.urgente,
      specialiteConcerne: specialiteEnum
    };

    this.reclamationService.creerReclamation(payload).subscribe({
      next: () => {
        this.toast.show('Réclamation créée avec succès', 'success');
        this.form.reset();
        this.isLoading = false;
        // Optionnel : rediriger vers la liste des réclamations après 1 seconde
        setTimeout(() => this.router.navigate(['/client/claims']), 1500);
      },
      error: (err) => {
        console.error(err);
        this.toast.show('Erreur lors de la création', 'error');
        this.isLoading = false;
      }
    });
  }
}