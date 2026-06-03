import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefEquipeLayoutComponent } from './chef-equipe-layout.component';

describe('ChefEquipeLayoutComponent', () => {
  let component: ChefEquipeLayoutComponent;
  let fixture: ComponentFixture<ChefEquipeLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChefEquipeLayoutComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ChefEquipeLayoutComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
