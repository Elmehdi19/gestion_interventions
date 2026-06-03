import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdonnanceurDashboardComponent } from './ordonnaceur-dashboard.component';

describe('OrdonnanceurDashboardComponent', () => {
  let component: OrdonnanceurDashboardComponent;
  let fixture: ComponentFixture<OrdonnanceurDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrdonnanceurDashboardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(OrdonnanceurDashboardComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
