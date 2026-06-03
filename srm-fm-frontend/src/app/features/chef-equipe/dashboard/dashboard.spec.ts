import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefEquipeDashboardComponent } from './chef-equipe-dashboard.component';

describe('ChefEquipeDashboardComponent', () => {
  let component: ChefEquipeDashboardComponent;
  let fixture: ComponentFixture<ChefEquipeDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChefEquipeDashboardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ChefEquipeDashboardComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
