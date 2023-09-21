import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserEndElementComponent } from './user-end-element.component';

describe('UserEndElementComponent', () => {
  let component: UserEndElementComponent;
  let fixture: ComponentFixture<UserEndElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserEndElementComponent]
    });
    fixture = TestBed.createComponent(UserEndElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
