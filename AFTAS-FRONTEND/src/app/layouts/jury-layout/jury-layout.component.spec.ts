import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuryLayoutComponent } from './jury-layout.component';

describe('JuryLayoutComponent', () => {
  let component: JuryLayoutComponent;
  let fixture: ComponentFixture<JuryLayoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JuryLayoutComponent]
    });
    fixture = TestBed.createComponent(JuryLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
