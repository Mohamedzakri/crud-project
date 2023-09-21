import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileElementComponent } from './user-profile-element.component';

describe('UserProfileElementComponent', () => {
  let component: UserProfileElementComponent;
  let fixture: ComponentFixture<UserProfileElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserProfileElementComponent]
    });
    fixture = TestBed.createComponent(UserProfileElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
