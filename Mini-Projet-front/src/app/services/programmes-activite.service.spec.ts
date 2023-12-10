import { TestBed } from '@angular/core/testing';

import { ProgrammesActiviteService } from './programmes-activite.service';

describe('ProgrammesActiviteService', () => {
  let service: ProgrammesActiviteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgrammesActiviteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
