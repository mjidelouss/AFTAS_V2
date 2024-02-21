import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JurySidebarComponent } from './jury-sidebar.component';

describe('JurySidebarComponent', () => {
  let component: JurySidebarComponent;
  let fixture: ComponentFixture<JurySidebarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JurySidebarComponent]
    });
    fixture = TestBed.createComponent(JurySidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
