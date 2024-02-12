import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCompetitionComponent } from './register-competition.component';

describe('RegisterCompetitionComponent', () => {
  let component: RegisterCompetitionComponent;
  let fixture: ComponentFixture<RegisterCompetitionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterCompetitionComponent]
    });
    fixture = TestBed.createComponent(RegisterCompetitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
