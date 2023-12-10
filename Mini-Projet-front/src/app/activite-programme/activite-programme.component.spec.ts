import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiviteProgrammeComponent } from './activite-programme.component';

describe('ActiviteProgrammeComponent', () => {
  let component: ActiviteProgrammeComponent;
  let fixture: ComponentFixture<ActiviteProgrammeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActiviteProgrammeComponent]
    });
    fixture = TestBed.createComponent(ActiviteProgrammeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
