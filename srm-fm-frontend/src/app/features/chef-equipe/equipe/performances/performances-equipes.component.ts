import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChefEquipeService } from '../../../../core/services/chef-equipe.service';

interface TechnicienLight {
  id: number;
  nom: string;
  prenom: string;
  nbInterventions: number;
  nbCloturees: number;
}

interface PerformanceEquipe {
  equipeId: number;
  nomEquipe: string;
  chefNom: string;
  nbTechniciens: number;
  nbInterventionsTotal: number;
  nbCloturees: number;
  nbEnCours: number;
  dureeMoyenneEquipe: number;
  techniciens: TechnicienLight[];
}

@Component({
  selector: 'app-performances-equipes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './performances-equipes.component.html',
  styleUrls: ['./performances-equipes.component.scss']
})
export class PerformancesEquipesComponent implements OnInit {
  performances: PerformanceEquipe[] = [];

  constructor(private chefEquipeService: ChefEquipeService) {}

  ngOnInit(): void {
    this.chefEquipeService.getPerformancesEquipes().subscribe(data => {
      this.performances = data;
    });
  }
}