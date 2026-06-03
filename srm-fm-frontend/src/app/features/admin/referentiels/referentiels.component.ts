import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpecialitesListComponent } from '../specialites/specialites-list/specialites-list.component';
import { FamillesListComponent } from '../familles/familles-list/familles-list.component';
import { TypesListComponent } from '../types/types-list/types-list.component';

@Component({
  selector: 'app-referentiels',
  standalone: true,
  imports: [CommonModule, SpecialitesListComponent, FamillesListComponent, TypesListComponent],
  templateUrl: './referentiels.component.html',
  styleUrls: ['./referentiels.component.scss']
})
export class ReferentielsComponent {
  activeTab: 'specialites' | 'familles' | 'types' = 'specialites';

  setTab(tab: 'specialites' | 'familles' | 'types') {
    this.activeTab = tab;
  }
}