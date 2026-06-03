import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AdminUserService } from '../../../../core/services/admin-user.service';
import { UserResponse } from '../../../../shared/models/user.model';

@Component({
  selector: 'app-users-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  users: UserResponse[] = [];

  constructor(private adminUserService: AdminUserService) {}

  ngOnInit(): void {
    this.adminUserService.getAll().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Erreur chargement utilisateurs', err)
    });
  }
}