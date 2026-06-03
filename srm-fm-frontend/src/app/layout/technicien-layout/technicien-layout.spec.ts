import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienLayoutComponent } from './technicien-layout.component';

describe('TechnicienLayoutComponent', () => {
  let component: TechnicienLayoutComponent;
  let fixture: ComponentFixture<TechnicienLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TechnicienLayoutComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TechnicienLayoutComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
