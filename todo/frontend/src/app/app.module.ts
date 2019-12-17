import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodoComponent } from './todo/todo.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import {FormsModule} from "@angular/forms";
import { TodoListHeaderComponent } from './todo-list-header/todo-list-header.component';
import { TodoListItemComponent } from './todo-list-item/todo-list-item.component';
import { TodoListFooterComponent } from './todo-list-footer/todo-list-footer.component';
import {TodoDataService} from "./todo-data.service";
import {HttpClientModule} from "@angular/common/http";
import {ApiService} from "./api.service";

@NgModule({
  declarations: [
    AppComponent,
    TodoComponent,
    TodoListComponent,
    TodoListHeaderComponent,
    TodoListItemComponent,
    TodoListFooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [TodoDataService, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
