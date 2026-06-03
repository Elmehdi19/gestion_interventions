import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-kpi-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './kpi-card.component.html',
  styleUrls: ['./kpi-card.component.scss']
})
export class KpiCardComponent {
  @Input() title = '';
  @Input() value: string | number = '';
  @Input() icon = 'bar_chart';
  @Input() colorClass = 'bg-primary-container text-on-primary-container';
}