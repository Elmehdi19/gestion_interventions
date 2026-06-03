import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EquipeService } from '../../../../core/services/equipe.service';
import { EquipeResponse } from '../../../../shared/models/equipe.models';

@Component({
  selector: 'app-equipes-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './equipes-list.component.html',
  styleUrls: ['./equipes-list.component.scss']
})
export class EquipesListComponent implements OnInit {
  equipes: EquipeResponse[] = [];

  constructor(private equipeService: EquipeService) {}

  ngOnInit(): void {
    this.equipeService.getAll().subscribe(data => this.equipes = data);
  }
}