import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './users-form.component.html',
  styleUrls: ['./users-form.component.scss']
})
export class UsersFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  userId?: number;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required],
      role: ['', Validators.required],
      specialite: ['']
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEdit = true;
        this.userId = +id;
        // Charger les données utilisateur existantes et patcher le formulaire
        // Exemple : this.form.patchValue({...})
      }
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    // Appeler le service pour créer ou mettre à jour
    console.log(this.form.value);
    this.router.navigate(['/admin/users']);
  }
}