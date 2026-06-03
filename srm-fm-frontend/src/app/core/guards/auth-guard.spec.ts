import { TestBed } from '@angular/core/testing';
import { CanActivateFn, Router } from '@angular/router';

import { AuthGuard } from './auth-guard';
import { AuthService } from '../services/auth.service';
import { provideHttpClient } from '@angular/common/http';

describe('authGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
    TestBed.runInInjectionContext(() => TestBed.inject(AuthGuard).canActivate());

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthGuard, AuthService, provideHttpClient()]
    });
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
