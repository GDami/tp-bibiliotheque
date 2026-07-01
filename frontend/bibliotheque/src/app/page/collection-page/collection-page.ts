import { Component, inject, OnInit } from '@angular/core';
import { CollectionService } from '../../service/collection-service';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Collection } from '../../model/collection';

@Component({
  selector: 'app-Collection-page',
  imports: [ CommonModule, FormsModule ],
  templateUrl: './Collection-page.html',
  styleUrl: './Collection-page.css',
})
export class CollectionPage implements OnInit {
  private collectionService: CollectionService = inject(CollectionService);
  protected collections$!: Observable<Collection[]>;
  protected form: Collection = { nom: '' };
  protected isEditing: boolean = false;

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.collections$ = this.collectionService.findAll();
  }

  add(): void {
    this.collectionService.add(this.form).subscribe(() => {
      this.form = { nom: '' };
      this.refresh();
    });
  }

  edit(collection: Collection): void {
    this.form = { ...collection };
    this.isEditing = true;
  }

  update(): void {
    this.collectionService.update(this.form).subscribe(() => {
      this.form = { nom: '' };
      this.isEditing = false;
      this.refresh();
    });
  }

  remove(collection: Collection): void {
    this.collectionService.remove(collection).subscribe(() => this.refresh());
  }

  cancel(): void {
    this.form = { nom: '' };
    this.isEditing = false;
  }
}
