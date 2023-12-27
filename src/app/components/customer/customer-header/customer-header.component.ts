import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-customer-header',
  templateUrl: './customer-header.component.html',
  styleUrls: ['./customer-header.component.scss']
})
export class CustomerHeaderComponent implements OnInit {
url:string="/customer/home";
userName: string = '';
  constructor(
    private fService :FoodorderService,
    private router:Router
  ) {
    if (this.fService.getCustomerName() !== null) {
      this.userName = this.fService.getCustomerName();
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });
  }
  routerToLink(link: string): void {
    if (link === '/customer/logout') {
      this.fService.customerLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
