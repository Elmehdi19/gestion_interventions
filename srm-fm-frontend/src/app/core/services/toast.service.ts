import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ToastService {
  show(message: string, type: 'success' | 'error' | 'info' = 'info'): void {
    alert(`${type.toUpperCase()}: ${message}`);
  }
}