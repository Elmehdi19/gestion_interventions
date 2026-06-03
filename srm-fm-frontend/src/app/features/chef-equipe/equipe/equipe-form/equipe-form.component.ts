import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ChefEquipeService } from '../../../../core/services/chef-equipe.service';
import { ChefEquipeDTO } from '../../../../shared/models/equipe.models';
// Validateur personnalisé pour le créneau horaire (HH:MM-HH:MM)
function horaireValidator(control: AbstractControl): { [key: string]: any } | null {
  if (!control.value) return null;
  const regex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
  return regex.test(control.value) ? null : { formatHoraire: true };
}

@Component({
  selector: 'app-equipe-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './equipe-form.component.html',
  styleUrls: ['./equipe-form.component.scss']
})
export class EquipeFormComponent implements OnInit {
  form: FormGroup;
  isEditMode = false;
  equipeId: number | null = null;
  chefs: ChefEquipeDTO[] = [];

  constructor(
    private fb: FormBuilder,
    private chefEquipeService: ChefEquipeService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      creneauHoraire: ['', [Validators.required, horaireValidator]],
      chefId: [null],
      specialite: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.chefEquipeService.getAllChefs().subscribe(data => this.chefs = data);
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.equipeId = +id;
      this.chefEquipeService.getEquipeById(this.equipeId).subscribe(equipe => {
        this.form.patchValue({
          nom: equipe.nom,
          creneauHoraire: equipe.creneauHoraire,
          chefId: equipe.chefId,
          specialite: equipe.specialite
        });
      });
    }
  }

  onSubmit() {
    if (this.form.invalid) return;
    const data = this.form.value;
    if (this.isEditMode && this.equipeId) {
      this.chefEquipeService.updateEquipe(this.equipeId, data).subscribe({
        next: () => this.router.navigate(['/chef-equipe/equipes']),
        error: (err) => console.error(err)
      });
    } else {
      this.chefEquipeService.createEquipe(data).subscribe({
        next: () => this.router.navigate(['/chef-equipe/equipes']),
        error: (err) => console.error(err)
      });
    }
  }
}