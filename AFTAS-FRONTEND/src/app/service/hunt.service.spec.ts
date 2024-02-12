import { TestBed } from '@angular/core/testing';

import { HuntService } from './hunt.service';

describe('HuntService', () => {
  let service: HuntService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HuntService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
