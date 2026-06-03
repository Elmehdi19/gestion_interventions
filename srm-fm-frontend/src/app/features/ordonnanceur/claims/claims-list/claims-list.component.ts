import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { OrdonnanceurService } from '../../../../core/services/ordonnanceur.service';
import { ReclamationResponse } from '../../../../shared/models/reclamation.models';

@Component({
  selector: 'app-claims-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './claims-list.component.html',
  styleUrls: ['./claims-list.component.scss']
})
export class ClaimsListComponent implements OnInit {
  reclamations: ReclamationResponse[] = [];

  constructor(private ordonnanceurService: OrdonnanceurService) {}

  ngOnInit(): void {
    this.ordonnanceurService.getReclamationsNouvelles().subscribe(data => this.reclamations = data);
  }
}