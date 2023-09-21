import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTodoListElementComponent } from './user-todo-list-element.component';

describe('UserTodoListElementComponent', () => {
  let component: UserTodoListElementComponent;
  let fixture: ComponentFixture<UserTodoListElementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserTodoListElementComponent]
    });
    fixture = TestBed.createComponent(UserTodoListElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
