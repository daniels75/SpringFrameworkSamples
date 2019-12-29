import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Todo} from "../todo";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {UpdTodo} from "../updtodo";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
  // host: {'class': 'ui middle aligned divided  big list'}
})
export class TodoListComponent implements OnInit {

  @Input() todos: Todo[];

  @Output() remove: EventEmitter<Todo> = new EventEmitter();

  @Output() toggleComplete: EventEmitter<Todo> = new EventEmitter();

  @Output() updateTodos:EventEmitter<UpdTodo> = new EventEmitter();

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

    const originalTodos  = Object.assign([], this.todos);

    const titles1 = this.todos.map(function(item) {
      return item['title'];
    });
    console.info("before: " + titles1);
    moveItemInArray(this.todos, event.previousIndex, event.currentIndex);

    const titles2 = this.todos.map(function(item) {
      return item['title'];
    });

    console.info("after: " + titles2);
    console.info("------------");

    const updTodo: UpdTodo = new UpdTodo();
    updTodo.previous = originalTodos;
    updTodo.current = Object.assign([], this.todos);
    updTodo.currentIdx = event.currentIndex;
    updTodo.previousIdx = event.previousIndex;
    updTodo.todo = updTodo.current[event.currentIndex];

    this.updateTodos.emit(updTodo);
  }

}
