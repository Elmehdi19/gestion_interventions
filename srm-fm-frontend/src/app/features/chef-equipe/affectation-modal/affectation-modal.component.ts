import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog'; 
import { ChefEquipeService } from '../../../core/services/chef-equipe.service';
export interface AffectationData {
  interventionId: number;
  interventionNumero: string;
}

@Component({
  selector: 'app-affectation-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="p-4 bg-white rounded shadow-lg">
      <h2 class="text-lg font-bold mb-4">Affecter un technicien</h2>
      <p>Intervention {{ data.interventionNumero }}</p>
      <form [formGroup]="form" (ngSubmit)="affecter()">
        <div class="mb-3">
          <label class="block text-sm font-medium">Technicien</label>
          <select formControlName="technicienId" class="w-full border p-2 rounded">
            <option [ngValue]="null">-- Choisir --</option>
            <option *ngFor="let tech of techniciens" [ngValue]="tech.id">
              {{ tech.prenom }} {{ tech.nom }} ({{ tech.interventionsEnCours }} en cours)
            </option>
          </select>
        </div>
        <div class="flex justify-end gap-2">
          <button type="button" (click)="fermer()" class="btn-secondary">Annuler</button>
          <button type="submit" [disabled]="form.invalid" class="btn-primary">Valider</button>
        </div>
      </form>
    </div>
  `
})
export class AffectationModalComponent {
  form: FormGroup;
  techniciens: any[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: AffectationData,
    private fb: FormBuilder,
    @Inject(MatDialogRef) private dialogRef: MatDialogRef<AffectationModalComponent>,
    private chefEquipeService: ChefEquipeService
  ) {
    this.form = this.fb.group({ technicienId: [null, Validators.required] });
    this.chefEquipeService.getTechniciensDisponibles().subscribe(techs => this.techniciens = techs);
  }

  affecter() {
    if (this.form.invalid) return;
    this.chefEquipeService.affecterTechnicien(this.data.interventionId, this.form.value.technicienId).subscribe({
      next: () => this.dialogRef.close(true),
      error: (err) => console.error(err)
    });
  }

  fermer() { this.dialogRef.close(false); }
}