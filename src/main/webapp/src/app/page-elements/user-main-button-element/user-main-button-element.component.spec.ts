import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMainButtonElementComponent } from './user-main-button-element.component';

describe('UserMainButtonElementComponent', () => {
  let component: UserMainButtonElementComponent;
  let fixture: ComponentFixture<UserMainButtonElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserMainButtonElementComponent]
    });
    fixture = TestBed.createComponent(UserMainButtonElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
