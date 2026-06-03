import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChefEquipeService } from '../../../core/services/chef-equipe.service';

interface PerformanceTechnicien {
  technicienId: number;
  nomComplet: string;
  nbInterventions: number;
  dureeMoyenneMinutes: number;
  nbCloturees: number;
  nbEmpechements: number;
  tauxCloture: number;
}

@Component({
  selector: 'app-performances-techniciens',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './performances.component.html',
  styleUrls: ['./performances.component.scss']
})
export class PerformancesTechniciensComponent implements OnInit {
  performances: PerformanceTechnicien[] = [];

  constructor(private chefEquipeService: ChefEquipeService) {}

  ngOnInit(): void {
    this.chefEquipeService.getPerformancesTechniciens().subscribe(data => {
      this.performances = data;
    });
  }
}