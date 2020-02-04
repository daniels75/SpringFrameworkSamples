import {Component} from '@angular/core';
import {AppService} from './app.service'
 
@Component({
    selector: 'home-header',
    providers: [AppService],
    template: `<div class="container" >
    <button *ngIf="!isLoggedIn" class="btn btn-primary" (click)="login()" type="submit">Login</button>
    <div *ngIf="isLoggedIn" class="content">
        <span>Angular UI</span>
        <a class="btn btn-default pull-right"(click)="logout()" href="#">Logout</a>
        <br/>
  
    </div>
</div>`
})
 
export class HomeComponent {
     authUrl:string = 'http://localhost:8081/spring-security-oauth-server/oauth/authorize?response_type=code&client_id=';
     public isLoggedIn = false;

    constructor(
        private _service:AppService){}
 
    ngOnInit(){
        this.isLoggedIn = this._service.checkCredentials();    
        let i = window.location.href.indexOf('code');
        if(!this.isLoggedIn && i != -1){
            this._service.retrieveToken(window.location.href.substring(i + 5));
        }
    }

    login() {
        window.location.href = this.authUrl
          + this._service.clientId
          + '&redirect_uri='
          + this._service.redirectUri;
    }
 
    logout() {
        this._service.logout();
    }
}
