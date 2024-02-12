import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuntComponent } from './hunt.component';

describe('HuntComponent', () => {
  let component: HuntComponent;
  let fixture: ComponentFixture<HuntComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HuntComponent]
    });
    fixture = TestBed.createComponent(HuntComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
