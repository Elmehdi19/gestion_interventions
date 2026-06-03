import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { ToastService } from '../../../core/services/toast.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router,
    private toast: ToastService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) return;
    this.auth.login(this.loginForm.value).subscribe({
      next: () => {
        const role = this.auth.getRole();
        if (role === 'ADMIN') this.router.navigate(['/admin/dashboard']);
        else if (role?.startsWith('ORDONNANCEUR')) this.router.navigate(['/ordonnanceur/dashboard']);
        else if (role === 'CHEF_EQUIPE') this.router.navigate(['/chef-equipe/dashboard']);
        else if (role === 'TECHNICIEN') this.router.navigate(['/technicien/dashboard']);
        else if (role === 'CLIENT') this.router.navigate(['/client/dashboard']);
        else this.router.navigate(['/']);
      },
      error: () => this.toast.show('Email ou mot de passe incorrect', 'error')
    });
  }
}