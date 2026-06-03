import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienDashboardComponent } from './technicien-dashboard.component';

describe('Dashboard', () => {
  let component: TechnicienDashboardComponent;
  let fixture: ComponentFixture<TechnicienDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicienDashboardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TechnicienDashboardComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
