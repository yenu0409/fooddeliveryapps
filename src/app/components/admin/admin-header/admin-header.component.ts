import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {
  url: string='';
  userName:string='';

  constructor(
    private fService:FoodorderService,
    private router:Router,
    private changeDetector: ChangeDetectorRef
  ){
    if (this.fService.getAdminName() !==null){
      this.userName=this.fService.getAdminName();
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) =>{
      this.url=event?.url;
    });
  }

  routerToLink(link: string):void{
    if(link==='/admin/logout'){
      this.fService.customerLogout();
      return;
    }
    this.router.navigate([link]);
  }
}
