import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarExamenesComponent } from './asignar-examenes.component';

describe('AsignarExamenesComponent', () => {
  let component: AsignarExamenesComponent;
  let fixture: ComponentFixture<AsignarExamenesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AsignarExamenesComponent]
    });
    fixture = TestBed.createComponent(AsignarExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
