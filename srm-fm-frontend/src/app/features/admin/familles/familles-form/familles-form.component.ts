import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AdminFamilleService } from '../../../../core/services/admin-famille.service';
import { AdminSpecialiteService } from '../../../../core/services/admin-specialite.service';
import { SpecialiteResponse } from '../../../../shared/models/specialite.models';

@Component({
  selector: 'app-familles-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './familles-form.component.html',
  styleUrls: ['./familles-form.component.scss']
})
export class FamillesFormComponent implements OnInit {
  form: FormGroup;
  specialites: SpecialiteResponse[] = [];
  isEditMode = false;
  familleId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private familleService: AdminFamilleService,
    private specialiteService: AdminSpecialiteService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required],
      description: [''],
      specialiteId: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    // Charger la liste des spécialités pour le select
    this.specialiteService.getAll().subscribe({
      next: (data) => this.specialites = data,
      error: (err) => console.error('Erreur chargement spécialités', err)
    });

    // Détecter le mode édition
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.familleId = +id;
      this.loadFamille(this.familleId);
    }
  }

  loadFamille(id: number): void {
    this.familleService.getById(id).subscribe({
      next: (data: any) => {
        this.form.patchValue({
          libelle: data.libelle,
          description: data.description ?? '',
          specialiteId: data.specialiteId
        });
      },
      error: (err) => console.error('Erreur chargement famille', err)
    });
  }
  
  annuler(): void {
    this.router.navigate(['/admin/familles']);
  }
  onSubmit(): void {
    if (this.form.invalid) return;

    const familleData = this.form.value;

    if (this.isEditMode && this.familleId) {
      this.familleService.update(this.familleId, familleData).subscribe({
        next: () => this.router.navigate(['/admin/familles']),
        error: (err) => console.error('Erreur mise à jour', err)
      });
    } else {
      this.familleService.create(familleData).subscribe({
        next: () => this.router.navigate(['/admin/familles']),
        error: (err) => console.error('Erreur création', err)
      });
    }
  }
}