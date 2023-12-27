import { Component } from '@angular/core';
import { FoodorderService } from './components/service/foodorder.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Food-Order';
  isLoggedIn: boolean=false;
  isAdminLoggedIn: boolean=false;

  constructor(
    private fService:FoodorderService,
    private router:Router
  ){
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      if (this.fService.getCustomerAuthorization() !== null) {
        setTimeout(() => {
          this.isLoggedIn = true;
          this.isAdminLoggedIn = false;
        }, 100);
      } else {
        if (this.fService.getAdminAuthorization() !== null) {
          setTimeout(() => {
            this.isAdminLoggedIn = true;
            this.isLoggedIn = false;
          }, 100);

        } {
          setTimeout(() => {
            this.isLoggedIn = false;
            this.isAdminLoggedIn = false;
          }, 1);
        }
      }
    });
//line 20 to 41-->check when routing(url) change it will check authorization
  }


  
}
