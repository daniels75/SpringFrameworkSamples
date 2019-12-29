import {Component, OnInit} from '@angular/core';
import {TodoDataService} from "./todo-data.service";
import {Todo} from "./todo";
import {UpdTodo} from "./updtodo";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [TodoDataService]
})
export class AppComponent implements OnInit {

  todos: Todo[] = [];

  constructor( private todoDataService: TodoDataService ) {
  }

  public ngOnInit() {
    this.todoDataService
      .getAllTodos()
      .subscribe(
        (todos) => {
          this.todos = todos;
        }
      );
  }

  onAddTodo(todo: Todo) {

    let maxOrder: number = 0;
    this.todos.forEach((item: Todo) => {
      if (item.priority >= maxOrder) {
        maxOrder = item.priority + 1;
      }
    })
    todo.priority = maxOrder;

    this.todoDataService
      .addTodo(todo)
      .subscribe(
        (newTodo) => {
          this.todos = this.todos.concat(newTodo);
        }
      );
  }

  onToggleTodoComplete(todo) {
    this.todoDataService
      .toggleTodoComplete(todo)
      .subscribe(
        (updatedTodo) => {
          todo = updatedTodo;
        }
      );
  }

  onRemoveTodo(todo) {
    this.todoDataService
      .deleteTodoById(todo.id)
      .subscribe(
        (_) => {
          this.todos = this.todos.filter((t) => t.id !== todo.id);
        }
      );
  }

  onUpdateTodos1(updTodo: UpdTodo) {
    this.todoDataService.updateTodos(updTodo).subscribe(
      (updatedTodos: Todo[]) => {
        updatedTodos.map((obj) => {
          console.log("onUpdateTodos - id: " + obj.id + " priority: " + obj.priority);
        })
      }
    );
  }

  onUpdateTodos(updTodo: UpdTodo) {
    this.todoDataService.updateTodos2(updTodo).subscribe(
      (updatedTodos: Todo[]) => {
        // this.todos = updatedTodos; // this not needed in fact since drag and drop will handle list
        updatedTodos.map((obj) => {
          console.log("onUpdateTodos2 - id: " + obj.id + " priority: " + obj.priority);
        })
      }
    );
  }
  
}

