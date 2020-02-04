import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home.component";
import {FooComponent} from "./foo.component";

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' }
];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
