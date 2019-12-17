import { Injectable } from '@angular/core';

import { Todo } from './todo';
import { Observable } from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";

const API_URL = environment.apiUrl;

@Injectable()
export class ApiService {

  constructor(
    private http: HttpClient
  ) {
  }

  // API: GET /todos
  public getAllTodos() {
    // will use this.http.get()
  }

  // API: POST /todos
  public createTodo(todo: Todo) {
    // will use this.http.post()
  }

  // API: GET /todos/:id
  public getTodoById(todoId: number) {
    // will use this.http.get()
  }

  // API: PUT /todos/:id
  public updateTodo(todo: Todo) {
    // will use this.http.put()
  }

  // DELETE /todos/:id
  public deleteTodoById(todoId: number) {
    // will use this.http.delete()
  }
}
