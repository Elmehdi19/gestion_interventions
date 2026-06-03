import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PermutationService } from '../../../../core/services/permutation.service';
import { PermutationResponse } from '../../../../shared/models/permutation.models';

@Component({
  selector: 'app-permutations-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './permutations-list.component.html',
  styleUrls: ['./permutations-list.component.scss']
})
export class PermutationsListComponent implements OnInit {
  permutations: PermutationResponse[] = [];

  constructor(private permutationService: PermutationService) {}

  ngOnInit(): void {
    this.permutationService.getAll().subscribe(data => this.permutations = data);
  }

  valider(id: number): void {
    this.permutationService.validerPermutation(id).subscribe(() => {
      this.ngOnInit();
    });
  }
}