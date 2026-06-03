import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AdminTypeService } from '../../../../core/services/admin-type.service';
import { AdminFamilleService } from '../../../../core/services/admin-famille.service';
import { FamilleResponse } from '../../../../shared/models/famille.models';

@Component({
  selector: 'app-types-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './types-form.component.html',
  styleUrls: ['./types-form.component.scss']
})
export class TypesFormComponent implements OnInit {
  form: FormGroup;
  familles: FamilleResponse[] = [];
  isEditMode = false;
  typeId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private typeService: AdminTypeService,
    private familleService: AdminFamilleService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required],
      dureeEstimeeMinutes: [null, [Validators.required, Validators.min(1)]],
      familleId: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    // Charger les familles pour le select
    this.familleService.getAll().subscribe({
      next: (data) => (this.familles = data),
      error: (err) => console.error('Erreur chargement familles', err)
    });

    // Vérifier si on est en mode édition
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.typeId = +id;
        this.loadType(this.typeId);
      }
    });
  }

  loadType(id: number): void {
    this.typeService.getById(id).subscribe({
      next: (data: any) => {
        this.form.patchValue({
          libelle: data.libelle,
          dureeEstimeeMinutes: data.dureeEstimeeMinutes,
          familleId: data.familleId
        });
      },
      error: (err) => console.error('Erreur chargement type', err)
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    const typeData = this.form.value;

    if (this.isEditMode && this.typeId) {
      this.typeService.update(this.typeId, typeData).subscribe({
        next: () => this.router.navigate(['/admin/types']),
        error: (err) => console.error('Erreur mise à jour', err)
      });
    } else {
      this.typeService.create(typeData).subscribe({
        next: () => this.router.navigate(['/admin/types']),
        error: (err) => console.error('Erreur création', err)
      });
    }

  }
  annuler(): void {
    this.router.navigate(['/admin/types']);
  }
}