import { Injectable } from '@angular/core';
import {Todo} from "./todo";
import {ApiService} from "./api.service";
import {Observable, of} from "rxjs";
import {UpdTodo} from "./updtodo";

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class TodoDataService {

  constructor( private api: ApiService ) {
  }

  // POST /todos
  addTodo(todo: Todo): Observable<Todo> {
    return this.api.createTodo(todo);
  }

  // DELETE /todos/:id
  deleteTodoById(todoId: number): Observable<Todo> {
    return this.api.deleteTodoById(todoId);
  }

  // PUT /todos/:id
  updateTodo(todo: Todo): Observable<Todo> {
    return this.api.updateTodo(todo);
  }

  // GET /todos
  getAllTodos(): Observable<Todo[]> {
    return this.api.getAllTodos();
  }

  // GET /todos/:id
  getTodoById(todoId: number): Observable<Todo> {
    return this.api.getTodoById(todoId);
  }

  // Toggle complete
  toggleTodoComplete(todo: Todo) {
    todo.complete = !todo.complete;
    return this.api.updateTodo(todo);
  }

  updateTodos(updTodo: UpdTodo): Observable<Todo> {
     let todoList: Todo[] = Object.assign([], updTodo.current);

     /*
     let currentIdx: number = 0;
    todoList.forEach((todo: Todo) => {
      todo.order = currentIdx++;
       }
     )


    todoList.forEach((todo: Todo) => {
        this.api.updateTodo(todo).subscribe((next) => {
          console.log("updated...");
        });
      }
    )
    */

    let todoToUpd: Todo = updTodo.todo;
    todoToUpd.order = 0;
    let obs:Observable<Todo> = this.api.updateTodo(todoToUpd);


    return obs;
  }
}
