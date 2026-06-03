import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReclamationService } from '../../../../core/services/reclamation.service';
import { ReclamationResponse } from '../../../../shared/models/reclamation.models';

@Component({
  selector: 'app-client-claims-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './claims-list.component.html',
  styleUrls: ['./claims-list.component.scss']
})
export class ClientClaimsListComponent implements OnInit {
  reclamations: ReclamationResponse[] = [];

  constructor(private reclamationService: ReclamationService) {}

  ngOnInit(): void {
    this.reclamationService.getMesReclamations().subscribe(data => this.reclamations = data);
  }
}