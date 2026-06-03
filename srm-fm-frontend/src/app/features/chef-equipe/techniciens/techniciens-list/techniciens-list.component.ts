import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChefEquipeService } from '../../../../core/services/chef-equipe.service';
import { TechnicienDTO } from '../../../../shared/models/equipe.models';

interface TechnicienParEquipe {
  equipeNom: string;
  techniciens: TechnicienDTO[];
}

@Component({
  selector: 'app-techniciens-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './techniciens-list.component.html',
  styleUrls: ['./techniciens-list.component.scss']
})
export class TechniciensListComponent implements OnInit {
  techniciensParEquipe: TechnicienParEquipe[] = [];

  constructor(private chefEquipeService: ChefEquipeService) {}

  ngOnInit(): void {
    this.chefEquipeService.getAllTechniciens().subscribe(techniciens => {
      // Grouper par équipe (utiliser equipeNom, qui est une chaîne)
      const map = new Map<string, TechnicienDTO[]>();
      techniciens.forEach(tech => {
        // technicien model may vary; try several possible fields safely
        const t: any = tech as any;
        const equipe = t.equipe?.nom ?? t.equipeNom ?? t.equipe_name ?? 'Sans équipe';
        if (!map.has(equipe)) map.set(equipe, []);
        map.get(equipe)!.push(tech);
      });
      this.techniciensParEquipe = Array.from(map.entries()).map(([equipeNom, techniciens]) => ({ equipeNom, techniciens }));
    });
  }
}