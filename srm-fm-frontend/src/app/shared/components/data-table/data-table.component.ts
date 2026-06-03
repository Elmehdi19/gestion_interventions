import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface ColumnConfig {
  key: string;       // nom de la propriété dans l'objet data
  label: string;     // titre de colonne
  type?: 'text' | 'status' | 'actions';
  // Vous pouvez ajouter des options pour le type status, etc.
}

@Component({
  selector: 'app-data-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent {
  @Input() columns: ColumnConfig[] = [];
  @Input() data: any[] = [];
  @Input() loading = false;
  @Input() emptyMessage = 'Aucune donnée trouvée.';

  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();
}