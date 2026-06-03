import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AdminSpecialiteService } from '../../../../core/services/admin-specialite.service';

@Component({
  selector: 'app-specialites-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './specialites-form.component.html',
  styleUrls: ['./specialites-form.component.scss']
})
export class SpecialitesFormComponent implements OnInit {
  form: FormGroup;
  isEditMode = false;
  specialiteId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private specialiteService: AdminSpecialiteService
  ) {
    this.form = this.fb.group({
      libelle: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.specialiteId = +id;
      this.loadSpecialite(this.specialiteId);
    }
  }

  loadSpecialite(id: number): void {
    this.specialiteService.getById(id).subscribe({
      next: (data) => this.form.patchValue({ libelle: data.libelle }),
      error: (err) => console.error('Erreur chargement spécialité', err)
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    const data = this.form.value;
    if (this.isEditMode && this.specialiteId) {
      this.specialiteService.update(this.specialiteId, data).subscribe({
        next: () => this.router.navigate(['/admin/specialites']),
        error: (err) => console.error('Erreur mise à jour', err)
      });
    } else {
      this.specialiteService.create(data).subscribe({
        next: () => this.router.navigate(['/admin/specialites']),
        error: (err) => console.error('Erreur création', err)
      });
    }
  }

  annuler(): void {
    this.router.navigate(['/admin/specialites']);
  }
}