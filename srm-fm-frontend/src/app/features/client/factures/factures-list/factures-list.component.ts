import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FactureService } from '../../../../core/services/facture.service';
import { FactureResponse } from '../../../../shared/models/facture.models';

@Component({
  selector: 'app-factures-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './factures-list.component.html',
  styleUrls: ['./factures-list.component.scss']
})
export class FacturesListComponent implements OnInit {
  factures: FactureResponse[] = [];

  constructor(private factureService: FactureService) {}

  ngOnInit(): void {
    this.factureService.getMesFactures().subscribe(data => this.factures = data);
  }
}