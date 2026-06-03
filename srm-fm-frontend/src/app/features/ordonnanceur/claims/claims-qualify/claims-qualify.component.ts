import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrdonnanceurService } from '../../../../core/services/ordonnanceur.service';

@Component({
  selector: 'app-claims-qualify',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './claims-qualify.component.html',
  styleUrls: ['./claims-qualify.component.scss']
})
export class ClaimsQualifyComponent implements OnInit {
  reclamationId!: number;
  form: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ordonnanceurService: OrdonnanceurService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      urgente: [false]
    });
  }

  ngOnInit(): void {
    this.reclamationId = Number(this.route.snapshot.paramMap.get('id'));
  }

  qualifier() {
    this.ordonnanceurService.qualifierReclamation(this.reclamationId, this.form.value).subscribe(() => {
      // Rediriger vers la création d'intervention avec l'ID de la réclamation
      this.router.navigate(['/ordonnanceur/interventions/new'], {
        queryParams: { reclamationId: this.reclamationId }
      });
    });
  }
}