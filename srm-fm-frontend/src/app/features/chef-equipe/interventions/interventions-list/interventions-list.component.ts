import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { AffectationModalComponent, AffectationData } from '../../affectation-modal/affectation-modal.component';
import { ChefEquipeService } from '../../../../core/services/chef-equipe.service';
import { InterventionResponse } from '../../../../shared/models/intervention.models';

@Component({
  selector: 'app-interventions-list',
  standalone: true,
  imports: [CommonModule], // plus besoin d'importer AffectationModalComponent ici
  templateUrl: './interventions-list.component.html'
})
export class InterventionsListComponent implements OnInit {
  interventions: InterventionResponse[] = [];
  currentPage = 0;
  totalPages = 0;
  pageSize = 10;

  constructor(
    private chefEquipeService: ChefEquipeService,
    @Inject(MatDialog) private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.loadInterventions();
  }

  loadInterventions() {
    this.chefEquipeService.getInterventionsEnAttente(this.currentPage, this.pageSize).subscribe(page => {
      this.interventions = page.content;
      this.totalPages = page.totalPages;
    });
  }

  openModal(intervention: InterventionResponse) {
    const dialogRef = this.dialog.open(AffectationModalComponent, {
      width: '500px',
      data: {
        interventionId: intervention.id,
        interventionNumero: intervention.numero
      } as AffectationData
    });
    dialogRef.afterClosed().subscribe(refresh => {
      if (refresh) {
        this.loadInterventions(); // recharge la liste après affectation
      }
    });
  }

  pageSuivante() {
    if (this.currentPage + 1 < this.totalPages) {
      this.currentPage++;
      this.loadInterventions();
    }
  }

  pagePrecedente() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadInterventions();
    }
  }
}