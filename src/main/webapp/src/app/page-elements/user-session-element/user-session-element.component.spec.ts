import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSessionElementComponent } from './user-session-element.component';

describe('UserSessionElementComponent', () => {
  let component: UserSessionElementComponent;
  let fixture: ComponentFixture<UserSessionElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserSessionElementComponent]
    });
    fixture = TestBed.createComponent(UserSessionElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
