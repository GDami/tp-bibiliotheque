import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home-page',
  imports: [],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage implements OnInit {
  private titleService: Title = inject(Title);

  ngOnInit(): void {
    this.titleService.setTitle("Accueil");
  }
}
