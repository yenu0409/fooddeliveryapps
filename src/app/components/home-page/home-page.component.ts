import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
  providers:[NgbCarouselConfig]
})
export class HomePageComponent implements OnInit{

  constructor(
    config:NgbCarouselConfig,
    private route:Router
  ){
    config.interval=0;
    config.keyboard=false;
    config.pauseOnHover=false;
  }

  ngOnInit(): void {
    
  }

  gotoLogin():void{
    this.route.navigate(['/customer-login'])
  }
} 
