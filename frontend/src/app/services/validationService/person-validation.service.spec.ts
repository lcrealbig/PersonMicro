import { TestBed } from '@angular/core/testing';

import { PersonValidationService } from './person-validation.service';

describe('PersonValidationService', () => {
  let service: PersonValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersonValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
