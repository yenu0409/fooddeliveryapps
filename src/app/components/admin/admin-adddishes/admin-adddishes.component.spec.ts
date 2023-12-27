import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdddishesComponent } from './admin-adddishes.component';

describe('AdminAdddishesComponent', () => {
  let component: AdminAdddishesComponent;
  let fixture: ComponentFixture<AdminAdddishesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAdddishesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAdddishesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
