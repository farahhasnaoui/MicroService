import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgrammeActiviteComponent } from './programme-activite.component';

describe('ProgrammeActiviteComponent', () => {
  let component: ProgrammeActiviteComponent;
  let fixture: ComponentFixture<ProgrammeActiviteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProgrammeActiviteComponent]
    });
    fixture = TestBed.createComponent(ProgrammeActiviteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
