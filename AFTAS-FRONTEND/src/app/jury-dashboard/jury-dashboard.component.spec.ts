import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuryDashboardComponent } from './jury-dashboard.component';

describe('JuryDashboardComponent', () => {
  let component: JuryDashboardComponent;
  let fixture: ComponentFixture<JuryDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JuryDashboardComponent]
    });
    fixture = TestBed.createComponent(JuryDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
