import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterventionsListComponent } from './interventions-list.component';

describe('InterventionsList', () => {
  let component: InterventionsListComponent;
  let fixture: ComponentFixture<InterventionsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InterventionsListComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InterventionsListComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
