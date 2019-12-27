import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Todo} from "../todo";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
  // host: {'class': 'ui middle aligned divided  big list'}
})
export class TodoListComponent implements OnInit {

  @Input() todos: Todo[];

  @Output() remove: EventEmitter<Todo> = new EventEmitter();

  @Output()
  toggleComplete: EventEmitter<Todo> = new EventEmitter();

  constructor() { }


  ngOnInit() {
  }

    onToggleTodoComplete(todo: Todo) {
    this.toggleComplete.emit(todo);
  }

  onRemoveTodo(todo: Todo) {
    this.remove.emit(todo);
  }

  itemDropped(event: CdkDragDrop<Todo[]>) {
    moveItemInArray(this.todos, event.previousIndex, event.currentIndex);
  }



}
