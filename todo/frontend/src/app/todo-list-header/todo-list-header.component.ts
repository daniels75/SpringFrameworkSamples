import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Todo} from "../todo";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-todo-list-header',
  templateUrl: './todo-list-header.component.html',
  styleUrls: ['./todo-list-header.component.css']
})
export class TodoListHeaderComponent implements OnInit {


  newTodo: Todo = new Todo();

  @Output()
  add: EventEmitter<Todo> = new EventEmitter();
  titleInvalid: boolean;

  constructor(){

  }

  ngOnInit() {
    this.titleInvalid = false;
  }

  addTodo() {
    if (this.newTodo.title.length > 2) {
      this.add.emit(this.newTodo);
      this.newTodo = new Todo();
      this.titleInvalid = false;
    } else {
      this.titleInvalid = true;
    }

  }

}
